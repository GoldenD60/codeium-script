package org.goldend60.codeiumscript;

import java.util.List;

import static org.goldend60.codeiumscript.Logger.*;

public class Parser {
	Lexer lexer;
	List<Lexer.Token> tokens;
	int index = 0;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		this.tokens = lexer.tokens;
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

	public boolean parse() {
		info(tokens);
		String functionName = "";
		boolean verdict = true;
		while (index < tokens.size()) {
			Lexer.Token token = peek();
			switch (token.type) {
				case FUNC_DEF: {
					consume();
					if (peek().type == Lexer.TokenType.IDENT) {
						functionName = consume().value;
						info("Code creates function:", functionName);
					}
					else {
						verdict = parserError(peek(), "an identifier", this.lexer);
					}
					break;
				}
				default: {
					break;
				}
			}
			consume();
		}
		return verdict;
	}
}
