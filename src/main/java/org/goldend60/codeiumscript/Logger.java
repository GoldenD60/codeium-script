package org.goldend60.codeiumscript;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Logger {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private static void print(String text, String color, Object... message) {
		System.out.printf("%s[codeium] %s" + text + "%s%n", ANSI_CYAN, color, Arrays.stream(message).map(Object::toString).collect(Collectors.joining(" ")), ANSI_RESET);
	}

	private static int[] getLineAndCharIndex(String text, int index) {
		String[] lines = text.split("\n", -1);
		int charCount = 0;

		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
			int lineLength = lines[lineIndex].length() + 1; // +1 for the newline character

			if (index < charCount + lineLength) {
				return new int[]{lineIndex, index - charCount};
			}

			charCount += lineLength;
		}

		return null; // Index out of bounds
	}

	public static boolean syntaxError(String anomaly, Lexer lexer) {
		List<String> lines = lexer.content.lines().toList();

		int charCount = 0;
		for (int i = 0; i < lines.size(); i++) {
			int lineLength = lines.get(i).length();

			if (lexer.index < charCount + lineLength) {
				int line = i + 1;
				int chr = lexer.index - charCount - anomaly.length();
				exception("SyntaxError: Unexpected \"" + anomaly + "\" at line", line, "char", chr, "in .(" + lexer.file.getName() + ":" + line + ")");
				exception(lines.get(i));
				exception(" ".repeat(chr) + "^".repeat(anomaly.length()));
				return false;
			}

			charCount += lineLength + 2;
		}
		return false;
	}

	public static boolean exception(Object... message) {
		print("ERROR: %s", ANSI_RED, message);
		return false;
	}

	public static void info(Object... message) {
		print("INFO: %s", ANSI_WHITE, message);
	}
}
