package diamond.parser;
// Generated from DiamondParser.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DiamondParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DiamondParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DiamondParser#qualElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualElem(DiamondParser.QualElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#qualElems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualElems(DiamondParser.QualElemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#qual}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQual(DiamondParser.QualContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#idQty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdQty(DiamondParser.IdQtyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(DiamondParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(DiamondParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#namedParamList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedParamList(DiamondParser.NamedParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#funTy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunTy(DiamondParser.FunTyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#tyParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyParam(DiamondParser.TyParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#tyParamList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyParamList(DiamondParser.TyParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#tyFunTy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyFunTy(DiamondParser.TyFunTyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#ty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTy(DiamondParser.TyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#qty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQty(DiamondParser.QtyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(DiamondParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(DiamondParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(DiamondParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(DiamondParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#tyArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyArgs(DiamondParser.TyArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DiamondParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#alloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlloc(DiamondParser.AllocContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#deref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeref(DiamondParser.DerefContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#lam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLam(DiamondParser.LamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#tyLam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyLam(DiamondParser.TyLamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#valDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValDecl(DiamondParser.ValDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(DiamondParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MonoFunDef}
	 * labeled alternative in {@link DiamondParser#funDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonoFunDef(DiamondParser.MonoFunDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PolyFunDef}
	 * labeled alternative in {@link DiamondParser#funDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolyFunDef(DiamondParser.PolyFunDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link DiamondParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DiamondParser.ProgramContext ctx);
}