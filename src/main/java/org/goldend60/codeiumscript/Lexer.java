package org.goldend60.codeiumscript;

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
		while (index < content.length()) {
			char c = consume();
			if (Character.isLetter(c)) {
				buf += c;
				while (index < content.length() && Character.isLetterOrDigit(peek()))
					buf += consume();
				switch (buf) {
					case "func": {
						tokens.add(new Token(TokenType.FUNC_DEF));
						buf = "";
						break;
					}
					default: {
						return syntaxError(buf, this);
					}
				}
			}
			/*else if (Character.isDigit(c)) {
				buf += c;
				while (index < content.length() && Character.isDigit(peek()))
					buf += consume();
				tokens.add(new Token(TokenType.INT, buf));
				buf = "";
			}*/
		}
		info(tokens);
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
		RIGHT_PAREN
	}
}
