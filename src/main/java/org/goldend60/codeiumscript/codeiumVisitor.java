// Generated from D:/Minecraft Dev Mods/codeium-script/codeium.g4 by ANTLR 4.13.2
package org.goldend60.codeiumscript;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link codeiumParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface codeiumVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link codeiumParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(codeiumParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(codeiumParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTR(codeiumParser.STRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IDENT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIDENT(codeiumParser.IDENTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SELECTOR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSELECTOR(codeiumParser.SELECTORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ADDSUB}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitADDSUB(codeiumParser.ADDSUBContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MULDIV}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMULDIV(codeiumParser.MULDIVContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PAREN}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPAREN(codeiumParser.PARENContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT(codeiumParser.INTContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#fileNamespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileNamespace(codeiumParser.FileNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(codeiumParser.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#toplevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToplevel(codeiumParser.ToplevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(codeiumParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(codeiumParser.InstructionContext ctx);
}