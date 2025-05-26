package org.goldend60.codeiumscript;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Objects;

public class CodeGenerator {
	public final static String ROOT = "root";
	public final Path out;
	public final String namespace;
	public final String name;
	public final String nbt;
	public final String scoreboard;
	public HashMap<String, String> variables = new HashMap<>();
	public int genCount = 0;

	public CodeGenerator(String namespace, String name, Path out) {
		this.namespace = namespace;
		this.name = name;
		this.nbt = namespace + ":" + name;
		this.scoreboard = namespace + "." + name;
		this.out = out;
	}

	public String generate() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
		StringBuilder identifier = new StringBuilder();

		// Convert genCount to base 64 representation
		int count = this.genCount++ + 1;
		while (count > 0) {
			count--; // Adjust for zero-based index
			identifier.insert(0, chars.charAt(count % chars.length()));
			count /= chars.length();
		}

		return identifier.toString();
	}

	public String generateVariable(String type) {
		String generate = this.generate();
		variables.put(generate, type);
		return generate;
	}

	public String getType(String variable) {
		return variables.get(variable);
	}

	public String setData(String nbt, String value) {
		if (!this.variables.containsKey(nbt)) {
			throw new RuntimeException("Variable \"" + nbt + "\" does not exist");
		}
		return "data modify storage %s %s set value %s\n".formatted(this.nbt, ROOT + "." + nbt, value);
	}

	public String setScoreboard(String variable, int value) {
		return "scoreboard players set %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String addScoreboard(String variable, int value) {
		return "scoreboard players add %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String subScoreboard(String variable, int value) {
		return "scoreboard players remove %s %s %d\n".formatted(variable, this.scoreboard, value);
	}

	public String opScoreboard(String lh, String rh, String op) {
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
		return "execute store result storage %s %s %s 1 run scoreboard players get %s %s\n".formatted(this.nbt, ROOT + "." + nbt, type, variable, this.scoreboard);
	}

	public String setScoreboardToData(String nbt, String type, String variable) {
		if (!this.variables.containsKey(nbt)) {
			throw new RuntimeException("Variable \"" + nbt + "\" does not exist");
		}
		if (!Objects.equals(type, "int")) {
			throw new RuntimeException("Invalid type");
		}
		return "execute store result score %s %s run data get storage %s %s\n".formatted(variable, this.scoreboard, this.nbt, ROOT + "." + nbt);
	}

	public String setDataToData(String nbt, String nbt2) {
		if (!this.variables.containsKey(nbt)) {
			throw new RuntimeException("Variable \"" + nbt + "\" does not exist");
		}
		if (!this.variables.containsKey(nbt2)) {
			throw new RuntimeException("Variable \"" + nbt2 + "\" does not exist");
		}
		return "data modify storage %s %s set from storage %s %s\n".formatted(this.nbt, ROOT + "." + nbt, this.nbt, ROOT + "." + nbt2);
	}

	public void require(String resource) throws IOException {
		Path path = Path.of(this.out.toString(), "data", resource);
		path.getParent().toFile().mkdirs();
		if (path.toFile().createNewFile()) {
			Files.copy(
					Objects.requireNonNull(this.getClass().getResourceAsStream(resource)),
					path,
					StandardCopyOption.REPLACE_EXISTING
			);
		}
	}

	public String concatenate(String lh, String rh) {
		try {
			this.require("/codeium/function/str_concat.mcfunction");
			this.require("/codeium/function/str_concat2.mcfunction");
			this.require("/codeium/loot_table/concat.json");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return "data modify storage codeium:str_concat root.in1 set from storage %s %s.%s\ndata modify storage codeium:str_concat root.in2 set from storage %s %s.%s\nfunction codeium:str_concat\ndata modify storage %s %s.%s set from storage codeium:str_concat root.out\n".formatted(this.nbt, ROOT, lh, this.nbt, ROOT, rh, this.nbt, ROOT, lh);
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
