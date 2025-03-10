package org.goldend60.codeiumscript.old;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Parser {
	Lexer lexer;
	List<Lexer.Token> tokens;
	File output;
	int index = 0;
	boolean succeeded = true;

	public Parser(Lexer lexer, File output) {
		this.lexer = lexer;
		this.tokens = lexer.tokens;
		this.output = output;
	}

	private Optional<Lexer.Token> peek(int offset) {
		if (this.index + offset >= 0 && this.index + offset < this.tokens.size())
			return Optional.of(this.tokens.get(this.index + offset));
		return Optional.empty();
	}

	private Optional<Lexer.Token> peek() {
		return peek(0);
	}

	private void consume() {
		this.index++;
		peek(-1);
	}

	public Node.Instruction parse_instruction() {
		if (peek().isPresent()) {
			Logger.info("instruction start", peek().get().type);
			switch (peek().get().type) {
				default: {
					consume();
				}
			}
			return new Node.Instruction();
		}
		return null;
	}

	public Node.NamespaceStatement parse_namespace_statement() {
		if (peek().isPresent()) {
			Logger.info("namespace_statement start", peek().get().type);
			switch (peek().get().type) {
				case VOID: {
					Node.FunctionDefinition functionDefinition = new Node.FunctionDefinition();

					consume();
					if (peek().isPresent() && peek().get().type == Lexer.TokenType.IDENT) {
						functionDefinition.name = peek().get().value;

						consume();
						if (peek().isPresent() && peek().get().type == Lexer.TokenType.LEFT_PAREN) {
							consume();
							if (peek().isPresent() && peek().get().type == Lexer.TokenType.RIGHT_PAREN) {
								consume();
								if (peek().isPresent() && peek().get().type == Lexer.TokenType.LEFT_BRACE) {
									consume();

									if (peek().isPresent() && peek().get().type == Lexer.TokenType.RIGHT_BRACE)
									{
										consume();
										return functionDefinition;
									}
									Node.Instruction instruction = parse_instruction();
									if (peek(-1).isPresent())
										Logger.info("instruction end", peek(-1).get().type);
									while (instruction != null) {
										if (peek().isPresent()) {
											functionDefinition.instructions.add(instruction);
											if (peek().get().type == Lexer.TokenType.RIGHT_BRACE) {
												consume();
												return functionDefinition;
											}
											instruction = parse_instruction();
											if (peek(-1).isPresent())
												Logger.info("instruction end", peek(-1).get().type);
										}
										else {
											Logger.exception("Expected RIGHT_BRACE");
										}
									}
									Logger.exception("Expected RIGHT_BRACE");
								}
								Logger.exception("Expected LEFT_BRACE");
							}
							Logger.exception("Expected RIGHT_PAREN");
						}
						Logger.exception("Expected LEFT_PAREN");
					}
					Logger.exception("Expected IDENT");
				}
				default: {
					consume();
				}
			}
		}
		return null;
	}

	public Node.Namespace parse_namespace() {
		consume();
		Node.Namespace namespace = new Node.Namespace();
		if (peek().isPresent() && peek().get().type == Lexer.TokenType.IDENT) {
			namespace.name = peek().get().value;

			consume();
			if (peek().isPresent() && peek().get().type == Lexer.TokenType.LEFT_BRACE) {
				consume();

				if (peek().isPresent() && peek().get().type == Lexer.TokenType.RIGHT_BRACE)
				{
					consume();
					return namespace;
				}
				Node.NamespaceStatement statement = parse_namespace_statement();
				if (peek(-1).isPresent())
					Logger.info("namespace_statement end", peek(-1).get().type);
				while (statement != null) {
					if (peek().isPresent()) {
						namespace.statements.add(statement);
						if (peek().get().type == Lexer.TokenType.RIGHT_BRACE) {
							consume();
							return namespace;
						}
						statement = parse_namespace_statement();
						if (peek(-1).isPresent())
							Logger.info("namespace_statement end", peek(-1).get().type);
					}
					else {
						Logger.exception("Expected RIGHT_BRACE");
					}
				}
			}
			Logger.exception("Expected LEFT_BRACE");
		}
		Logger.exception("Expected IDENT");
		return null;
	}

	public Node.Prog parse_prog() {
		Node.Prog prog = new Node.Prog();
		while (peek().isPresent()) {
			Logger.info("prog start", peek().get().type);
			Lexer.Token token = peek().get();
			switch (token.type) {
				case Lexer.TokenType.NAMESPACE: {
					Node.Namespace ns = parse_namespace();
					if (peek(-1).isPresent())
						Logger.info("prog end", peek(-1).get().type);
					if (ns == null) return null;
					prog.statements.add(ns);
					break;
				}
				default: {
					consume();
					Logger.exception("prog has not yet implemented", token.type);
					//return null;
				}
			}
		}
		return prog;
	}
}
