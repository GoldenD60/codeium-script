package org.goldend60.codeiumscript;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

class CodeGenerator extends codeiumBaseListener {
	private String namespace = "unknown";
	private final Path root;
	private Path out;
	private File func;
	private FileWriter wfunc;

	public CodeGenerator(Path out) {
		this.root = out;
	}

	private String build(String... command) {
		return String.join(" ", command) + "\n";
	}

	@Override
	public void enterProg(codeiumParser.ProgContext ctx) {
		ctx.namespace().forEach(n -> n.enterRule(this));
		if (ctx.fileNamespace() != null)
			ctx.fileNamespace().enterRule(this);
		ctx.toplevel().forEach(n -> n.enterRule(this));
	}

	private void setNamespace(String namespace) {
		this.namespace = namespace;
		this.out = Path.of(this.root.toString(), "data", this.namespace, "function");
		this.out.toFile().mkdirs();
	}

	@Override
	public void enterFileNamespace(codeiumParser.FileNamespaceContext ctx) {
		this.setNamespace(ctx.IDENT().getText());
	}

	@Override
	public void enterNamespace(codeiumParser.NamespaceContext ctx) {
		this.setNamespace(ctx.IDENT().getText());
		ctx.toplevel().forEach(n -> n.enterRule(this));
	}

	@Override
	public void exitNamespace(codeiumParser.NamespaceContext ctx) {
		this.out = this.root;
		this.namespace = "";
	}

	@Override
	public void enterToplevel(codeiumParser.ToplevelContext ctx) {
		ctx.funcDef().enterRule(this);
	}

	@Override
	public void enterFuncDef(codeiumParser.FuncDefContext ctx) {
		try {
			this.func = Path.of(this.out.toString(), ctx.declaration(0).IDENT(1).getText() + ".mcfunction").toFile();
			this.wfunc = new FileWriter(this.func);
			ctx.instruction().forEach(n -> n.enterRule(this));
			ctx.exitRule(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void enterInstruction(codeiumParser.InstructionContext ctx) {
		try {
			this.wfunc.append(this.build("#", ctx.getText()));
			// TODO: Parse Instruction
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void exitFuncDef(codeiumParser.FuncDefContext ctx) {
		try {
			this.func = null;
			this.wfunc.close();
			this.wfunc = null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}