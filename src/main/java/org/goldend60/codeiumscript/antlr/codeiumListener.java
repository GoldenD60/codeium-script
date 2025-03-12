// Generated from D:/Minecraft Dev Mods/codeium-script/codeium.g4 by ANTLR 4.13.2
package org.goldend60.codeiumscript.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link codeiumParser}.
 */
public interface codeiumListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link codeiumParser#nsblock}.
	 * @param ctx the parse tree
	 */
	void enterNsblock(codeiumParser.NsblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#nsblock}.
	 * @param ctx the parse tree
	 */
	void exitNsblock(codeiumParser.NsblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(codeiumParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(codeiumParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(codeiumParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(codeiumParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(codeiumParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(codeiumParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(codeiumParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(codeiumParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LoadFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterLoadFunction(codeiumParser.LoadFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LoadFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitLoadFunction(codeiumParser.LoadFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeFunction(codeiumParser.TypeFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeFunction(codeiumParser.TypeFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelectorCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterSelectorCall(codeiumParser.SelectorCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelectorCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitSelectorCall(codeiumParser.SelectorCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KillCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterKillCall(codeiumParser.KillCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KillCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitKillCall(codeiumParser.KillCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TellCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterTellCall(codeiumParser.TellCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TellCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitTellCall(codeiumParser.TellCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterTypeCall(codeiumParser.TypeCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitTypeCall(codeiumParser.TypeCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentExpr(codeiumParser.IdentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentExpr(codeiumParser.IdentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntLitExpr(codeiumParser.IntLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntLitExpr(codeiumParser.IntLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(codeiumParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(codeiumParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(codeiumParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(codeiumParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelectorLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSelectorLitExpr(codeiumParser.SelectorLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelectorLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSelectorLitExpr(codeiumParser.SelectorLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStrLitExpr(codeiumParser.StrLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStrLitExpr(codeiumParser.StrLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(codeiumParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(codeiumParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(codeiumParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(codeiumParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinMaxExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinMaxExpr(codeiumParser.MinMaxExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinMaxExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinMaxExpr(codeiumParser.MinMaxExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(codeiumParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(codeiumParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstScope(codeiumParser.InstScopeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstScope(codeiumParser.InstScopeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstDeclaration}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstDeclaration(codeiumParser.InstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstDeclaration}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstDeclaration(codeiumParser.InstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstAssignment}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstAssignment(codeiumParser.InstAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstAssignment}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstAssignment(codeiumParser.InstAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstCall}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstCall(codeiumParser.InstCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstCall}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstCall(codeiumParser.InstCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link codeiumParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(codeiumParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link codeiumParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(codeiumParser.ReturnContext ctx);
}