package org.goldend60.codeiumscript;

import java.util.HashMap;
import java.util.Objects;

public class CodeGenerator {
	public final String namespace;
	public final String name;
	public final String nbt;
	public final String scoreboard;
	public HashMap<String, String> variables = new HashMap<>();

	public CodeGenerator(String namespace, String name) {
		this.namespace = namespace;
		this.name = name;
		this.nbt = namespace + ":" + name;
		this.scoreboard = namespace + "." + name;
	}

	public static String build(String... command) {
		return String.join(" ", command) + "\n";
	}

	public static String generate() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
		StringBuilder generated = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			generated.append(chars.charAt((int) (Math.random() * chars.length())));
		}
		return generated.toString();
	}

	public String generateVariable(String type) {
		String generate = generate();
		variables.put(generate, type);
		return generate;
	}

	public String getType(String variable) {
		return variables.get(variable);
	}

	public String set(String variable, int value) {
		return build("scoreboard", "players", "set", variable, this.scoreboard, "" + value);
	}

	public String add(String variable, int value) {
		return build("scoreboard", "players", "add", variable, this.scoreboard, "" + value);
	}

	public String sub(String variable, int value) {
		return build("scoreboard", "players", "remove", variable, this.scoreboard, "" + value);
	}

	public String op(String lh, String rh, String op) {
		return build("scoreboard", "players", "operation", lh, this.scoreboard, op, rh, this.scoreboard);
	}

	public String setDataToScoreboard(String path, String type, String variable) {
		// execute store result storage a:b c int 1 run scoreboard players get c a.b
		return build("execute", "store", "result", "storage", this.nbt, path, type, "1", "run", "scoreboard", "players", "get", variable, this.scoreboard);
	}

	public String setScoreboardToData(String path, String type, String variable) {
		// execute store result score c a.b run data get storage a:b c
		if (!Objects.equals(type, "int")) {
			throw new RuntimeException("Invalid type");
		}
		return build("execute", "store", "result", "score", variable, this.scoreboard, "run", "data", "get", "storage", this.nbt, path);
	}

	public void declareVariable(String name, String type) {
		if (variables.containsKey(name)) {
			throw new RuntimeException("Variable \"" + name + "\" already exists");
		}
		variables.put(name, type);
	}

	public String create() {
		return build("scoreboard", "objectives", "add", this.scoreboard, "dummy");
	}
}
