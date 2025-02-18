package org.goldend60.codeiumscript;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static org.goldend60.codeiumscript.Logger.exception;
import static org.goldend60.codeiumscript.Logger.info;
import static org.goldend60.codeiumscript.Util.recursivelyListDir;

public class Project {
	public Project(File project) {
		if (!project.exists()) exception("Project directory does not exist!");
		if (project.isDirectory()) {
			try {
				if (!projectDirectory(project))
					exception("Project failed to compile. See above for more details.");
			}
			catch (IOException error) {
				exception("An IOException occured. Details:", error.getLocalizedMessage());
			}
		}
	}

	boolean projectDirectory(File project) throws IOException {
		File[] files = project.listFiles();
		if (files == null) return exception("Failed to list files in project directory!");
		if (files.length == 0) return exception("No files in project directory!");

		Optional<File> fjson = Arrays.stream(files).filter(f -> f.getName().equals("project.json")).findFirst();
		if (fjson.isEmpty()) return exception("project.json is missing!");

		JsonElement json = JsonParser.parseReader(new FileReader(fjson.get()));
		JsonObject root = json.getAsJsonObject();
		if (!root.has("pack")) return exception("project.json is missing \"pack\" key!");
		if (!root.has("targets")) return exception("project.json is missing \"targets\" key!");
		if (!root.has("output")) return exception("project.json is missing \"output\" key!");

		JsonObject pack = root.getAsJsonObject("pack");
		JsonArray targets = root.getAsJsonArray("targets");
		File output = Path.of(project.getPath(), root.get("output").getAsString()).toFile();
		if (output.mkdir()) info("Output directory does not exist, creating directory...");

		JsonWriter writer = new JsonWriter(new FileWriter(Path.of(output.getPath(), "pack.mcmeta").toFile()));
		writer
				.beginObject().name("pack")
				.jsonValue(pack.toString())
				.endObject().close();

		List<File> targetFiles = new LinkedList<>();
		for (JsonElement target : targets) {
			String path = target.getAsString();
			String subpath = path.substring(0, path.length() - 2);
			if (path.endsWith("/**"))
				targetFiles.addAll(recursivelyListDir(Path.of(project.getPath(), subpath).toFile()));
			else if (path.endsWith("/*"))
				targetFiles.addAll(List.of(Objects.requireNonNull(Path.of(project.getPath(), subpath).toFile().listFiles())));
			else if (Path.of(project.getPath(), path).toFile().isDirectory())
				return exception("Directory not allowed in project.json \"targets\": \"" + path + "\". Use \"/*\" or \"/**\" to add a directory.");
			else targetFiles.add(Path.of(project.getPath(), path).toFile());
		}

		for (File file : targetFiles) {
			Lexer lexer = new Lexer(file);
			if (!lexer.tokenize()) return false;
		}

		return true;
	}
}
