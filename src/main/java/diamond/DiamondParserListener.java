package diamond.parser;
// Generated from DiamondParser.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DiamondParser}.
 */
public interface DiamondParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DiamondParser#qualElem}.
	 * @param ctx the parse tree
	 */
	void enterQualElem(DiamondParser.QualElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#qualElem}.
	 * @param ctx the parse tree
	 */
	void exitQualElem(DiamondParser.QualElemContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#qualElems}.
	 * @param ctx the parse tree
	 */
	void enterQualElems(DiamondParser.QualElemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#qualElems}.
	 * @param ctx the parse tree
	 */
	void exitQualElems(DiamondParser.QualElemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#qual}.
	 * @param ctx the parse tree
	 */
	void enterQual(DiamondParser.QualContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#qual}.
	 * @param ctx the parse tree
	 */
	void exitQual(DiamondParser.QualContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#idQty}.
	 * @param ctx the parse tree
	 */
	void enterIdQty(DiamondParser.IdQtyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#idQty}.
	 * @param ctx the parse tree
	 */
	void exitIdQty(DiamondParser.IdQtyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(DiamondParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(DiamondParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(DiamondParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(DiamondParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#namedArgList}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgList(DiamondParser.NamedArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#namedArgList}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgList(DiamondParser.NamedArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#funTy}.
	 * @param ctx the parse tree
	 */
	void enterFunTy(DiamondParser.FunTyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#funTy}.
	 * @param ctx the parse tree
	 */
	void exitFunTy(DiamondParser.FunTyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#ty}.
	 * @param ctx the parse tree
	 */
	void enterTy(DiamondParser.TyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#ty}.
	 * @param ctx the parse tree
	 */
	void exitTy(DiamondParser.TyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#qty}.
	 * @param ctx the parse tree
	 */
	void enterQty(DiamondParser.QtyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#qty}.
	 * @param ctx the parse tree
	 */
	void exitQty(DiamondParser.QtyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(DiamondParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(DiamondParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(DiamondParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(DiamondParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(DiamondParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(DiamondParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DiamondParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DiamondParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#alloc}.
	 * @param ctx the parse tree
	 */
	void enterAlloc(DiamondParser.AllocContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#alloc}.
	 * @param ctx the parse tree
	 */
	void exitAlloc(DiamondParser.AllocContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(DiamondParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(DiamondParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#deref}.
	 * @param ctx the parse tree
	 */
	void enterDeref(DiamondParser.DerefContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#deref}.
	 * @param ctx the parse tree
	 */
	void exitDeref(DiamondParser.DerefContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#lam}.
	 * @param ctx the parse tree
	 */
	void enterLam(DiamondParser.LamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#lam}.
	 * @param ctx the parse tree
	 */
	void exitLam(DiamondParser.LamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#tyParam}.
	 * @param ctx the parse tree
	 */
	void enterTyParam(DiamondParser.TyParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#tyParam}.
	 * @param ctx the parse tree
	 */
	void exitTyParam(DiamondParser.TyParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#tyParamList}.
	 * @param ctx the parse tree
	 */
	void enterTyParamList(DiamondParser.TyParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#tyParamList}.
	 * @param ctx the parse tree
	 */
	void exitTyParamList(DiamondParser.TyParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#tyLam}.
	 * @param ctx the parse tree
	 */
	void enterTyLam(DiamondParser.TyLamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#tyLam}.
	 * @param ctx the parse tree
	 */
	void exitTyLam(DiamondParser.TyLamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(DiamondParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(DiamondParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(DiamondParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(DiamondParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiamondParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DiamondParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiamondParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DiamondParser.ProgramContext ctx);
}