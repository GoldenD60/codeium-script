package org.goldend60.codeiumscript;


import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Project project = new Project(new File(args[0]));
	}
}