package org.goldend60.codeiumscript;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.goldend60.codeiumscript.Logger.info;
import static org.goldend60.codeiumscript.Logger.parserError;

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

	private Lexer.Token peek(int offset) {
		return this.tokens.get(index + offset);
	}

	private Lexer.Token peek() {
		return peek(0);
	}

	private Lexer.Token consume() {
		index++;
		return peek(-1);
	}

	public List<Node> parseAll() {
		info(tokens);
		List<Node> nodes = new LinkedList<>();
		nodes.add(parse());
		return nodes;
	}

	public Node parse() {
		Node node = null;
		Lexer.Token token = peek();
		int start = index;
		switch (token.type) {
			case DOUBLE_QUOT: {
				Node.LiteralString n = new Node.LiteralString();
				n.value = token.value;
				node = n;
				break;
			}
			case IDENT: {
				consume();
				if (peek().type == Lexer.TokenType.MEMBER_ACCESS) {
					consume();
					Node n = parse();
					index--; // TODO: May break something!
					if (!(n instanceof Node.MemberAccessor)) {
						Node.MemberAccessor m = new Node.MemberAccessor();
						m.value = (Node.Value) n;
						n = m;
					}
					((Node.MemberAccessor)n).memberAccesses.addFirst(token.value);
					node = n;
				} else if (peek().type == Lexer.TokenType.LEFT_PAREN) {
					consume();
					List<Node.Value> args = new LinkedList<>();
					while (index < tokens.size() && peek().type != Lexer.TokenType.RIGHT_PAREN) {
						Node n = parse();
						if (n == null) break;
						if (n instanceof Node.Value)
							args.add((Node.Value)n);
						else succeeded = parserError(peek(), "an instruction", this.lexer);
					}
					if (peek().type != Lexer.TokenType.RIGHT_PAREN)
						succeeded = parserError(peek(), "\"}\"", this.lexer);
					else {
						consume();
						if (peek().type != Lexer.TokenType.SEMI)
							succeeded = parserError(peek(), "\";\"", this.lexer);
					}
					Node.Function n = new Node.Function();
					n.name = token.value;
					n.arguments = args;
					node = n;
				}
				break;
			}
			case FUNC_DEF: {
				consume();
				if (peek().type == Lexer.TokenType.IDENT) {
					String name = consume().text;
					if (peek().type == Lexer.TokenType.LEFT_PAREN) {
						consume();
						if (peek().type == Lexer.TokenType.RIGHT_PAREN) {
							consume();
							if (peek().type == Lexer.TokenType.LEFT_BRACE) {
								consume();
								List<Node.Instruction> instructions = new LinkedList<>();
								while (index < tokens.size() && peek().type != Lexer.TokenType.RIGHT_BRACE) {
									Node n = parse();
									if (n == null) break;
									if (n instanceof Node.Instruction)
										instructions.add((Node.Instruction)n);
									else succeeded = parserError(peek(), "an instruction", this.lexer);
								}
								if (peek().type != Lexer.TokenType.RIGHT_BRACE)
									succeeded = parserError(peek(), "\"}\"", this.lexer);
								Node.FunctionDefinition n = new Node.FunctionDefinition();
								n.name = name;
								n.instructions = instructions;
								node = n;
								break;
							}
							succeeded = parserError(peek(), "\"{\"", this.lexer);
							break;
						}
						succeeded = parserError(peek(), "\")\"", this.lexer);
						break;
					}
					succeeded = parserError(peek(), "\"(\"", this.lexer);
					break;
				}
				succeeded = parserError(peek(), "an identifier", this.lexer);
				break;
			}
			case NAMESPACE: {
				consume();
				if (peek().type == Lexer.TokenType.IDENT) {
					String name = consume().text;
					if (peek().type == Lexer.TokenType.LEFT_BRACE) {
						consume();
						List<Node.TopLevelStatement> statements = new LinkedList<>();
						while (index < tokens.size() && peek().type != Lexer.TokenType.RIGHT_BRACE) {
							Node n = parse();
							if (n == null) break;
							if (n instanceof Node.TopLevelStatement)
								statements.add((Node.TopLevelStatement)n);
							else succeeded = parserError(peek(), "a top level statement", this.lexer);
						}
						if (peek().type != Lexer.TokenType.RIGHT_BRACE)
							succeeded = parserError(peek(), "\"}\"", this.lexer);
						Node.Namespace n = new Node.Namespace();
						n.name = name;
						n.statements = statements;
						node = n;
						break;
					}
					succeeded = parserError(peek(), "\"{\"", this.lexer);
					break;
				}
				succeeded = parserError(peek(), "an identifier", this.lexer);
				break;
			}
			default: {
				info("Unknown Token:", token);
				break;
			}
		}
		info("Token starts at index:", start, "as", token, "and ends with index:", index, "as", peek());
		consume();
		return node;
	}
}
