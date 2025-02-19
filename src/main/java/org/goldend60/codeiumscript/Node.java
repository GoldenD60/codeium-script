package org.goldend60.codeiumscript;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public static class TopLevelStatement extends Node {}
	public static class Instruction extends Node {}
	public static class Value extends Node {}
	public static class LiteralValue extends Value {}

	public static class Namespace extends Node {
		public String name;
		public List<TopLevelStatement> statements = new LinkedList<>();

		@Override
		public String toString() {
			return name + ": " + statements;
		}
	}

	public static class FunctionDefinition extends TopLevelStatement {
		public String name;
		public List<Instruction> instructions = new LinkedList<>();

		@Override
		public String toString() {
			return name + ": " + instructions;
		}
	}

	public static class LiteralString extends LiteralValue {
		public String value;

		@Override
		public String toString() {
			return "\"" + value + "\"";
		}
	}

	public static class MemberAccessor extends Instruction {
		List<String> memberAccesses = new LinkedList<>();
		Value value;

		@Override
		public String toString() {
			return memberAccesses.toString() + ": " + value;
		}
	}

	public static class Function extends Value {
		public String name;
		public List<Value> arguments = new LinkedList<>();

		@Override
		public String toString() {
			return name + arguments;
		}
	}
}
