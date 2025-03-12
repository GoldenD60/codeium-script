package org.goldend60.codeiumscript;

import org.goldend60.codeiumscript.antlr.codeiumBaseVisitor;
import org.goldend60.codeiumscript.antlr.codeiumParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

public class FunctionParser extends codeiumBaseVisitor<String> {
	private final Path out;
	private CodeGenerator gen;
	private String exprSubject = "";
	private String exprType;

	public FunctionParser(String namespace, Path out) {
		this.out = out;
		this.gen = new CodeGenerator(namespace, "runtime");
	}

	@Override
	public String visitTypeFunction(codeiumParser.TypeFunctionContext ctx) {
		return this.gen.create() + super.visitTypeFunction(ctx);
	}

	@Override
	public String visitLoadFunction(codeiumParser.LoadFunctionContext ctx) {
		String text = this.gen.create() + super.visitLoadFunction(ctx);
		try {
			File file = Path.of(this.out.toString(), "load.mcfunction").toFile();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return text;
	}

	@Override
	public String visitScope(codeiumParser.ScopeContext ctx) {
		HashMap<String, String> before = new HashMap<>(this.gen.variables);
		String text = super.visitScope(ctx);
		this.gen.variables = before;
		return text;
	}

	@Override
	public String visitInstDeclaration(codeiumParser.InstDeclarationContext ctx) {
		this.exprType = ctx.decl().type().getText();
		String name = ctx.decl().Ident().getText();
		this.gen.declareVariable(name, this.exprType);

		String text = ctx.expr().accept(this);
		return text + this.gen.setDataToScoreboard(name, this.exprType, this.exprSubject);
	}

	@Override
	public String visitIntLitExpr(codeiumParser.IntLitExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			this.exprSubject = this.gen.generateVariable("int");
			return this.gen.set(this.exprSubject, Integer.parseInt(ctx.getText()));
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitMinMaxExpr(codeiumParser.MinMaxExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprSubject;
			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprSubject;

			String operation = "<";
			if (Objects.equals(ctx.op.getText(), ">>"))
				operation = ">";
			this.exprSubject = lhVar;
			return lh + rh + this.gen.op(lhVar, rhVar, operation);
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitMultExpr(codeiumParser.MultExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprSubject;
			if (ctx.expr(0) instanceof codeiumParser.IntLitExprContext
			&&  ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int lhv = Integer.parseInt(ctx.expr(0).getText());
				int rhv = Integer.parseInt(ctx.expr(1).getText());
				int calc = lhv / rhv;
				if (Objects.equals(ctx.op.getText(), "*"))
					calc = lhv * rhv;
				else if (Objects.equals(ctx.op.getText(), "%"))
					calc = lhv % rhv;
				this.exprSubject = lhVar;
				return this.gen.set(lhVar, calc);
			}

			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprSubject;

			if (!Objects.equals(this.gen.getType(lhVar), this.gen.getType(rhVar))) {
				throw new RuntimeException("Cannot operate with types " + this.gen.getType(lhVar) + " and " + this.gen.getType(rhVar));
			}

			this.exprSubject = lhVar;
			return lh + rh + this.gen.op(lhVar, rhVar, ctx.op.getText() + "=");
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitAddSubExpr(codeiumParser.AddSubExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprSubject;
			if (ctx.expr(0) instanceof codeiumParser.IntLitExprContext
			&&  ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int lhv = Integer.parseInt(ctx.expr(0).getText());
				int rhv = Integer.parseInt(ctx.expr(1).getText());
				int calc = lhv - rhv;
				if (Objects.equals(ctx.op.getText(), "+"))
					calc = lhv + rhv;
				this.exprSubject = lhVar;
				return this.gen.set(lhVar, calc);
			}
			if (ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int rh = Integer.parseInt(ctx.expr(1).getText());

				String operation = this.gen.sub(lhVar, rh);
				if (Objects.equals(ctx.op.getText(), "+"))
					operation = this.gen.add(lhVar, rh);
				this.exprSubject = lhVar;
				return lh + operation;
			}
			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprSubject;

			if (!Objects.equals(this.gen.getType(lhVar), this.gen.getType(rhVar))) {
				throw new RuntimeException("Cannot operate with types " + this.gen.getType(lhVar) + " and " + this.gen.getType(rhVar));
			}

			this.exprSubject = lhVar;
			return lh + rh + this.gen.op(lhVar, rhVar, ctx.op.getText() + "=");
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	protected String aggregateResult(String aggregate, String nextResult) {
		if (aggregate == null) aggregate = "";
		if (nextResult == null) nextResult = "";
		return aggregate + nextResult;
	}
}