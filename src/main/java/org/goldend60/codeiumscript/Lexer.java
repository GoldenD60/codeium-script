package org.goldend60.codeiumscript;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import static org.goldend60.codeiumscript.Logger.*;

public class Lexer {
	public final File file;
	public String content;
	public int index = 0;
	public List<Token> tokens = new LinkedList<>();

	public Lexer(File file) throws IOException {
		this.file = file;
		this.content = Files.readString(file.toPath());
	}

	private char peek(int offset) {
		return this.content.charAt(index + offset);
	}

	private char peek() {
		return peek(0);
	}

	private char consume() {
		index++;
		return peek(-1);
	}

	public boolean tokenize() {
		String buf = "";
		String identifier = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
		while (index < content.length()) {
			char c = peek();
			if (("" + c).isBlank()) {
				consume();
				continue;
			}
			if (Character.isDigit(c)) {
				// TODO: Integers
			} else {
				if (identifier.contains(""+c)) {
					while (index < content.length() && identifier.contains("" + peek())) {
						buf += consume();
					}
					switch (buf) {
						case "func": {
							tokens.add(new Token(TokenType.FUNC_DEF));
							break;
						}
						default: {
							tokens.add(new Token(TokenType.IDENT, buf));
							break;
						}
					}
					buf = "";
					index--;
				} else {
					switch (c) {
						case '"': {
							consume();
							boolean escaped = false;
							while (index < content.length())
							{
								if (peek() == '"')
								{
									if (!escaped) break;
									escaped = false;
								}
								if (peek() == '\\')
									escaped = !escaped;
								buf += consume();
							}
							if (index == content.length()) unclosedQuote(index - buf.length() - 1, this);
							tokens.add(new Token(TokenType.DOUBLE_QUOT, buf));
							break;
						}
						case ';': {
							tokens.add(new Token(TokenType.SEMI));
							break;
						}
 						case '(': {
							tokens.add(new Token(TokenType.LEFT_PAREN));
							break;
						}
						case ')': {
							tokens.add(new Token(TokenType.RIGHT_PAREN));
							break;
						}
						case '{': {
							tokens.add(new Token(TokenType.LEFT_BRACE));
							break;
						}
						case '}': {
							tokens.add(new Token(TokenType.RIGHT_BRACE));
							break;
						}
						default:
							return syntaxError("" + c, this);
					}
					buf = "";
				}
			}
			index++;
		}
		info("Tokens:", tokens);
		return true;
	}

	public static class Token {
		TokenType type;
		String value = null;

		public Token(TokenType type, String value) {
			this.type = type;
			this.value = value;
		}

		public Token(TokenType type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type.name() + (value == null ? "" : ": " + value);
		}
	}

	public enum TokenType {
		FUNC_DEF,
		IDENT,
		LEFT_PAREN,
		RIGHT_PAREN,
		LEFT_BRACE,
		RIGHT_BRACE,
		SEMI,
		DOUBLE_QUOT
	}
}
