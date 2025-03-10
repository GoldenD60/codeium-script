// Generated from D:/Minecraft Dev Mods/codeium-script/codeium.g4 by ANTLR 4.13.2
package org.goldend60.codeiumscript;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link codeiumParser}.
 */
public interface codeiumListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link codeiumParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(codeiumParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(codeiumParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(codeiumParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(codeiumParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code STR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSTR(codeiumParser.STRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code STR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSTR(codeiumParser.STRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDENT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIDENT(codeiumParser.IDENTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDENT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIDENT(codeiumParser.IDENTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SELECTOR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSELECTOR(codeiumParser.SELECTORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SELECTOR}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSELECTOR(codeiumParser.SELECTORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ADDSUB}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterADDSUB(codeiumParser.ADDSUBContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ADDSUB}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitADDSUB(codeiumParser.ADDSUBContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MULDIV}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMULDIV(codeiumParser.MULDIVContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MULDIV}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMULDIV(codeiumParser.MULDIVContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PAREN}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPAREN(codeiumParser.PARENContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PAREN}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPAREN(codeiumParser.PARENContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterINT(codeiumParser.INTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitINT(codeiumParser.INTContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#fileNamespace}.
	 * @param ctx the parse tree
	 */
	void enterFileNamespace(codeiumParser.FileNamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#fileNamespace}.
	 * @param ctx the parse tree
	 */
	void exitFileNamespace(codeiumParser.FileNamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(codeiumParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(codeiumParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#toplevel}.
	 * @param ctx the parse tree
	 */
	void enterToplevel(codeiumParser.ToplevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#toplevel}.
	 * @param ctx the parse tree
	 */
	void exitToplevel(codeiumParser.ToplevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(codeiumParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(codeiumParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(codeiumParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(codeiumParser.InstructionContext ctx);
}