package org.goldend60.codeiumscript;

import java.util.Arrays;
import java.util.stream.Collectors;

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

	static void print(String text, String color, Object... message) {
		System.out.printf("%s[codeium] %s" + text + "%s%n", ANSI_CYAN, color, Arrays.stream(message).map(Object::toString).collect(Collectors.joining(" ")), ANSI_RESET);
	}

	public static boolean exception(Object... message) {
		print("ERROR: %s", ANSI_RED, message);
		return false;
	}

	public static void info(Object... message) {
		print("INFO: %s", ANSI_WHITE, message);
	}
}
