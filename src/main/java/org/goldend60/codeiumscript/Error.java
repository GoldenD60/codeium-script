package org.goldend60.codeiumscript;

public class Error {
	public static void throwException(String message) {
		throw new RuntimeException("codeium: " + message);
	}
}
