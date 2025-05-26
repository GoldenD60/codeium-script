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
	private final String namespace;
	private final Path root;
	private final Path out;
	private CodeGenerator gen;
	private String exprVar;
	private String exprType;

	public FunctionParser(String namespace, Path root) {
		this.namespace = namespace;
		this.root = root;
		this.out = Path.of(this.root.toString(), "data", this.namespace, "function");
		this.out.toFile().mkdirs();
	}

	@Override
	public String visitTypeFunction(codeiumParser.TypeFunctionContext ctx) {
		String name = ctx.decl(0).Ident().getText();
		this.gen = new CodeGenerator(namespace, name, this.root);
		String text = this.gen.create() + super.visitTypeFunction(ctx);
		try {
			File file = Path.of(this.out.toString(), name + ".mcfunction").toFile();
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
	public String visitLoadFunction(codeiumParser.LoadFunctionContext ctx) {
		this.gen = new CodeGenerator(namespace, "load", this.root);
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
	public String visitReturn(codeiumParser.ReturnContext ctx) {
		this.exprType = "int";
		return ctx.expr().accept(this) + this.gen.returnVariable(this.exprVar);
	}

	@Override
	public String visitInstAssignment(codeiumParser.InstAssignmentContext ctx) {
		String name = ctx.Ident().getText();
		String type = this.gen.getType(name);
		String var = this.gen.generateVariable(type);
		String op = ctx.op.getText();
		if (Objects.equals(op, ">>"))
			op = ">";
		else if (Objects.equals(op, "<<"))
			op = "<";
		return	ctx.expr().accept(this) + this.gen.setScoreboardToData(name, type, var) +
				this.gen.opScoreboard(var, this.exprVar, op) +
				this.gen.setDataToScoreboard(name, type, var);
	}

	@Override
	public String visitInstDeclaration(codeiumParser.InstDeclarationContext ctx) {
		this.exprType = ctx.decl().type().getText();
		String name = ctx.decl().Ident().getText();
		this.gen.declareVariable(name, this.exprType);

		String text = ctx.expr().accept(this);
		if (Objects.equals(this.exprType, "int")) {
			return text + this.gen.setDataToScoreboard(name, this.exprType, this.exprVar);
		} else if (Objects.equals(this.exprType, "string")) {
			return text + this.gen.setDataToData(name, this.exprVar);
		}
		return text;
	}

	@Override
	public String visitStrLitExpr(codeiumParser.StrLitExprContext ctx) {
		if (Objects.equals(this.exprType, "string")) {
			this.exprVar = this.gen.generateVariable("string");
			return this.gen.setData(this.exprVar, ctx.getText());
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitIdentExpr(codeiumParser.IdentExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String name = ctx.Ident().getText();
			this.exprVar = this.gen.generateVariable("int");
			return this.gen.setScoreboardToData(name, "int", this.exprVar);
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitIntLitExpr(codeiumParser.IntLitExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			this.exprVar = this.gen.generateVariable("int");
			return this.gen.setScoreboard(this.exprVar, Integer.parseInt(ctx.getText()));
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitMinMaxExpr(codeiumParser.MinMaxExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprVar;
			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprVar;

			String operation = "<";
			if (Objects.equals(ctx.op.getText(), "max"))
				operation = ">";
			this.exprVar = lhVar;
			return lh + rh + this.gen.opScoreboard(lhVar, rhVar, operation);
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitMultExpr(codeiumParser.MultExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprVar;
			if (ctx.expr(0) instanceof codeiumParser.IntLitExprContext
			&&  ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int lhv = Integer.parseInt(ctx.expr(0).getText());
				int rhv = Integer.parseInt(ctx.expr(1).getText());
				int calc = lhv / rhv;
				if (Objects.equals(ctx.op.getText(), "*"))
					calc = lhv * rhv;
				else if (Objects.equals(ctx.op.getText(), "%"))
					calc = lhv % rhv;
				this.exprVar = lhVar;
				return this.gen.setScoreboard(lhVar, calc);
			}

			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprVar;

			if (!Objects.equals(this.gen.getType(lhVar), this.gen.getType(rhVar))) {
				throw new RuntimeException("Cannot operate with types " + this.gen.getType(lhVar) + " and " + this.gen.getType(rhVar));
			}

			this.exprVar = lhVar;
			return lh + rh + this.gen.opScoreboard(lhVar, rhVar, ctx.op.getText() + "=");
		}
		throw new RuntimeException("Invalid type");
	}

	@Override
	public String visitAddSubExpr(codeiumParser.AddSubExprContext ctx) {
		if (Objects.equals(this.exprType, "int")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprVar;
			if (ctx.expr(0) instanceof codeiumParser.IntLitExprContext
			&&  ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int lhv = Integer.parseInt(ctx.expr(0).getText());
				int rhv = Integer.parseInt(ctx.expr(1).getText());
				int calc = lhv - rhv;
				if (Objects.equals(ctx.op.getText(), "+"))
					calc = lhv + rhv;
				this.exprVar = lhVar;
				return this.gen.setScoreboard(lhVar, calc);
			}
			if (ctx.expr(1) instanceof codeiumParser.IntLitExprContext) {
				int rh = Integer.parseInt(ctx.expr(1).getText());

				String operation = this.gen.subScoreboard(lhVar, rh);
				if (Objects.equals(ctx.op.getText(), "+"))
					operation = this.gen.addScoreboard(lhVar, rh);
				this.exprVar = lhVar;
				return lh + operation;
			}
			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprVar;

			if (!Objects.equals(this.gen.getType(lhVar), this.gen.getType(rhVar))) {
				throw new RuntimeException("Cannot operate with types " + this.gen.getType(lhVar) + " and " + this.gen.getType(rhVar));
			}

			this.exprVar = lhVar;
			return lh + rh + this.gen.opScoreboard(lhVar, rhVar, ctx.op.getText() + "=");
		}
		else if (Objects.equals(this.exprType, "string")) {
			String lh = ctx.expr(0).accept(this);
			String lhVar = this.exprVar;
			String rh = ctx.expr(1).accept(this);
			String rhVar = this.exprVar;

			if (!Objects.equals(this.gen.getType(lhVar), this.gen.getType(rhVar))) {
				throw new RuntimeException("Cannot operate with types " + this.gen.getType(lhVar) + " and " + this.gen.getType(rhVar));
			}

			this.exprVar = lhVar;
			return lh + rh + this.gen.concatenate(lhVar, rhVar);
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