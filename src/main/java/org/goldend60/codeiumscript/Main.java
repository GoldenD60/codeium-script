package org.goldend60.codeiumscript;


import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static int findStringEnd(String input) {
		if (input == null || input.length() < 2 || input.charAt(0) != '"') {
			return -1; // Invalid input, should start with a quote
		}

		boolean escaped = false;
		for (int i = 1; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == '"' && !escaped) {
				return i; // Found the end of the string
			}

			// Handle escape sequences
			escaped = c == '\\' && !escaped;
		}

		return -1; // No valid string end found
	}

	public static void main(String[] args) {
		String test = "\"Hello there, \\\"David\\\"!\\\"); return 1;";
		System.out.println(findStringEnd(test));
		Project project = new Project(new File(args[0]));
	}
}