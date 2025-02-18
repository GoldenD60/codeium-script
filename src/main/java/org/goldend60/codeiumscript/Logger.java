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

	private static LineInfo getLineInfo(int index, int offset, String text) {
		List<String> lines = Arrays.stream(text.split("\n", -1)).toList();

		int charCount = 0;
		for (int i = 0; i < lines.size(); i++) {
			int lineLength = lines.get(i).length() + 1;

			if (index < charCount + lineLength) {
				int chr = index - charCount - offset;
				int tabCount = lines.get(i).split("\t").length - 1;
				return new LineInfo(i, chr, tabCount, lines);
			}

			charCount += lineLength;
		}
		return null;
	}

	private record LineInfo(int line, int chr, int tabCount, List<String> lines) {}

	public static boolean unclosedQuote(int index, Lexer lexer) {
		LineInfo inf = getLineInfo(index, 0, lexer.content);
		assert inf != null;
		exception("SyntaxError: Unclosed quote at line", inf.line + 1, "char", inf.chr, "in .(" + lexer.file.getName() + ":" + (inf.line + 1) + ")");
		exception(inf.lines.get(inf.line));
		exception("\t".repeat(inf.tabCount) + " ".repeat(inf.chr - inf.tabCount) + "^");
		return false;
	}

	public static boolean syntaxError(String anomaly, Lexer lexer) {
		LineInfo inf = getLineInfo(lexer.index, -anomaly.length(), lexer.content);
		assert inf != null;
		exception("SyntaxError: Unexpected \"" + anomaly + "\" at line", inf.line + 1, "char", inf.chr, "in .(" + lexer.file.getName() + ":" + (inf.line + 1) + ")");
		exception(inf.lines.get(inf.chr));
		exception("\t".repeat(inf.tabCount) + " ".repeat(inf.chr - inf.tabCount) + "^".repeat(anomaly.length()));
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
