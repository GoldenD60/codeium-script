package org.goldend60.codeiumscript;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Util {
	public static List<File> recursivelyListDir(File directory) {
		List<File> files = new LinkedList<>();
		for (File file : Objects.requireNonNull(directory.listFiles())) {
			if (file.isDirectory())
				files.addAll(recursivelyListDir(file));
			else files.add(file);
		}
		return files;
	}
}
