package org.goldend60.codeiumscript.old;


import org.goldend60.codeiumscript.Project;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		Project project = new Project(new File(args[0]));
	}
}