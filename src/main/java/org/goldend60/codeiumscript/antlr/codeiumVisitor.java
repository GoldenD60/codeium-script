// Generated from D:/Minecraft Dev Mods/codeium-script/codeium.g4 by ANTLR 4.13.2
package org.goldend60.codeiumscript.antlr;
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
	 * Visit a parse tree produced by {@link codeiumParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(codeiumParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(codeiumParser.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#nsblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNsblock(codeiumParser.NsblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(codeiumParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(codeiumParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(codeiumParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(codeiumParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoadFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadFunction(codeiumParser.LoadFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeFunction}
	 * labeled alternative in {@link codeiumParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeFunction(codeiumParser.TypeFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelectorCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectorCall(codeiumParser.SelectorCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KillCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKillCall(codeiumParser.KillCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TellCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTellCall(codeiumParser.TellCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeCall}
	 * labeled alternative in {@link codeiumParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCall(codeiumParser.TypeCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentExpr(codeiumParser.IdentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLitExpr(codeiumParser.IntLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(codeiumParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(codeiumParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelectorLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectorLitExpr(codeiumParser.SelectorLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrLitExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrLitExpr(codeiumParser.StrLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(codeiumParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(codeiumParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinMaxExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinMaxExpr(codeiumParser.MinMaxExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link codeiumParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(codeiumParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstScope}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstScope(codeiumParser.InstScopeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstDeclaration}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstDeclaration(codeiumParser.InstDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstAssignment}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstAssignment(codeiumParser.InstAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstCall}
	 * labeled alternative in {@link codeiumParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstCall(codeiumParser.InstCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link codeiumParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(codeiumParser.ReturnContext ctx);
}