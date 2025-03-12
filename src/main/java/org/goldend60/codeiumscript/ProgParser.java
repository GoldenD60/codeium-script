package org.goldend60.codeiumscript;

import org.goldend60.codeiumscript.antlr.codeiumBaseListener;
import org.goldend60.codeiumscript.antlr.codeiumParser;

import java.nio.file.Path;

public class ProgParser extends codeiumBaseListener {
	private String namespace = "unknown";
	private Path out;
	private final Path root;

	public ProgParser(Path output) {
		this.root = output;
	}

	@Override
	public void enterProg(codeiumParser.ProgContext ctx) {
		ctx.namespace().forEach(n -> n.enterRule(this));
	}

	private void setNamespace(String namespace) {
		this.namespace = namespace;
		this.out = Path.of(this.root.toString(), "data", this.namespace, "function");
		this.out.toFile().mkdirs();
	}

	@Override
	public void enterNamespace(codeiumParser.NamespaceContext ctx) {
		this.setNamespace(ctx.Ident().getText());
		ctx.nsblock().forEach(n -> n.enterRule(this));
	}

	@Override
	public void enterNsblock(codeiumParser.NsblockContext ctx) {
		this.generate((codeiumParser.FuncDefContext) ctx.getChild(0));
	}

	@Override
	public void exitNamespace(codeiumParser.NamespaceContext ctx) {
		this.out = this.root;
		this.namespace = "";
	}

	private void generate(codeiumParser.FuncDefContext ctx) {
		FunctionParser gen = new FunctionParser(this.namespace, this.out);
		String content = ctx.accept(gen);
		System.out.println(content);
		ctx.exitRule(this);
	}
}
