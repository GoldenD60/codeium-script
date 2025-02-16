package org.goldend60.codeiumscript;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

import static org.goldend60.codeiumscript.Error.throwException;

public class Project {
	public Project(File project) throws FileNotFoundException {
		if (!project.exists()) throwException("Project directory does not exist.");
		if (project.isDirectory()) {
			projectDirectory(project);
		}
	}

	void projectDirectory(File project) throws FileNotFoundException {
		File[] files = project.listFiles();
		if (files == null) throwException("Failed to list files in project directory.");
		if (files.length == 0) throwException("No files in project directory.");

		Optional<File> fjson = Arrays.stream(files).filter(f -> f.getName().equals("project.json")).findFirst();
		if (fjson.isEmpty()) throwException("project.json is missing.");

		JsonElement json = JsonParser.parseReader(new FileReader(fjson.get()));
		JsonObject root = json.getAsJsonObject();
		JsonObject pack = root.getAsJsonObject("pack");
		System.out.println(pack);
	}
}
