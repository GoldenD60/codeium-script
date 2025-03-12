package org.goldend60.codeiumscript;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private static void print(String text, String color, Object... message) {
		System.out.printf("%s[codeium] %s" + text + "%s%n", ANSI_CYAN, color, Arrays.stream(message).map(Object::toString).collect(Collectors.joining(" ")), ANSI_RESET);
	}

	private static LineInfo getLineInfo(int index, int offsetLength, String text) {
		List<String> lines = Arrays.stream(text.split("\n", -1)).toList();

		int charCount = 0;
		for (int i = 0; i < lines.size(); i++) {
			int lineLength = lines.get(i).length() + 1;

			if (index < charCount + lineLength) {
				int tabCount = lines.get(i).split("\t").length - 1;
				int chr = index - charCount - (offsetLength > 1 ? offsetLength : 0);
				return new LineInfo(i, chr, tabCount, lines);
			}

			charCount += lineLength;
		}
		return null;
	}

	private record LineInfo(int line, int chr, int tabCount, List<String> lines) {}

	public static boolean exception(Object... message) {
		throw new RuntimeException(ANSI_RED + "ERROR: " + Arrays.stream(message).map(Object::toString).collect(Collectors.joining(" ")));
	}
}
