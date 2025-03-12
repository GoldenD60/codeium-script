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
		return "scoreboard players set %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String add(String variable, int value) {
		return "scoreboard players add %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String sub(String variable, int value) {
		return "scoreboard players remove %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String op(String lh, String rh, String op) {
		return "scoreboard players operation %s %s %s %s %s\n".formatted(lh, this.scoreboard, op, rh, this.scoreboard);
	}

	public String returnVariable(String variable) {
		return "return run scoreboard players get %s %s\n".formatted(variable, this.scoreboard);
	}

	public String setDataToScoreboard(String nbt, String type, String variable) {
		if (!this.variables.containsKey(nbt)) {
			throw new RuntimeException("Variable \"" + nbt + "\" does not exist");
		}
		if (!Objects.equals(type, "int")) {
			throw new RuntimeException("Invalid type");
		}
		return "execute store result storage %s %s %s 1 run scoreboard players get %s %s\n".formatted(this.nbt, nbt, type, variable, this.scoreboard);
	}

	public String setScoreboardToData(String nbt, String type, String variable) {
		if (!this.variables.containsKey(nbt)) {
			throw new RuntimeException("Variable \"" + nbt + "\" does not exist");
		}
		if (!Objects.equals(type, "int")) {
			throw new RuntimeException("Invalid type");
		}
		return "execute store result score %s %s run data get storage %s %s\n".formatted(variable, this.scoreboard, this.nbt, nbt);
	}

	public void declareVariable(String name, String type) {
		if (variables.containsKey(name)) {
			throw new RuntimeException("Variable \"" + name + "\" already exists");
		}
		variables.put(name, type);
	}

	public String create() {
		return "scoreboard objectives add %s dummy\n".formatted(this.scoreboard);
	}
}
