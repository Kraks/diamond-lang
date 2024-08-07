package diamond.parser;
// Generated from DiamondParser.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DiamondParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AT=1, EXCLA=2, COLON=3, COLONEQ=4, HAT=5, FRESH=6, COMMA=7, SEMI=8, LPAREN=9, 
		RPAREN=10, LCURLY=11, RCURLY=12, LBRACKET=13, RBRACKET=14, RIGHTARROW=15, 
		TRUE=16, FALSE=17, VAL=18, TOPVAL=19, IN=20, DEF=21, REF=22, FORALL=23, 
		SUBTYPE=24, UNIT=25, LAM=26, TYLAM=27, IF=28, ELSE=29, MOVE=30, AND=31, 
		OR=32, NOT=33, EQ=34, EQ2=35, NEQ=36, ADD=37, MINUS=38, MULT=39, DIV=40, 
		INT=41, ID=42, WS=43, COMMENT=44;
	public static final int
		RULE_qualElem = 0, RULE_qualElems = 1, RULE_qual = 2, RULE_idQty = 3, 
		RULE_param = 4, RULE_paramList = 5, RULE_namedParamList = 6, RULE_funTy = 7, 
		RULE_tyParam = 8, RULE_tyParamList = 9, RULE_tyFunTy = 10, RULE_ty = 11, 
		RULE_qty = 12, RULE_eff = 13, RULE_effs = 14, RULE_boolOp2 = 15, RULE_op1 = 16, 
		RULE_value = 17, RULE_args = 18, RULE_tyArgs = 19, RULE_wrapExpr = 20, 
		RULE_expr = 21, RULE_alloc = 22, RULE_deref = 23, RULE_move = 24, RULE_lam = 25, 
		RULE_tyLam = 26, RULE_valDecl = 27, RULE_let = 28, RULE_funDef = 29, RULE_program = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualElem", "qualElems", "qual", "idQty", "param", "paramList", "namedParamList", 
			"funTy", "tyParam", "tyParamList", "tyFunTy", "ty", "qty", "eff", "effs", 
			"boolOp2", "op1", "value", "args", "tyArgs", "wrapExpr", "expr", "alloc", 
			"deref", "move", "lam", "tyLam", "valDecl", "let", "funDef", "program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'!'", "':'", "':='", "'^'", null, "','", "';'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'=>'", "'true'", "'false'", "'val'", 
			"'topval'", "'in'", "'def'", "'Ref'", "'forall'", "'<:'", "'unit'", "'lam'", 
			"'Lam'", "'if'", "'else'", "'move'", "'&&'", "'||'", "'~'", "'='", "'=='", 
			"'!='", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", 
			"TRUE", "FALSE", "VAL", "TOPVAL", "IN", "DEF", "REF", "FORALL", "SUBTYPE", 
			"UNIT", "LAM", "TYLAM", "IF", "ELSE", "MOVE", "AND", "OR", "NOT", "EQ", 
			"EQ2", "NEQ", "ADD", "MINUS", "MULT", "DIV", "INT", "ID", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DiamondParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DiamondParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualElemContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode FRESH() { return getToken(DiamondParser.FRESH, 0); }
		public QualElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualElem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterQualElem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitQualElem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitQualElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualElemContext qualElem() throws RecognitionException {
		QualElemContext _localctx = new QualElemContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_qualElem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==FRESH || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualElemsContext extends ParserRuleContext {
		public List<QualElemContext> qualElem() {
			return getRuleContexts(QualElemContext.class);
		}
		public QualElemContext qualElem(int i) {
			return getRuleContext(QualElemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public QualElemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualElems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterQualElems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitQualElems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitQualElems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualElemsContext qualElems() throws RecognitionException {
		QualElemsContext _localctx = new QualElemsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_qualElems);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			qualElem();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(65);
				match(COMMA);
				setState(66);
				qualElem();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualContext extends ParserRuleContext {
		public TerminalNode FRESH() { return getToken(DiamondParser.FRESH, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public QualElemsContext qualElems() {
			return getRuleContext(QualElemsContext.class,0);
		}
		public QualContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qual; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterQual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitQual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitQual(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualContext qual() throws RecognitionException {
		QualContext _localctx = new QualContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_qual);
		int _la;
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FRESH:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(FRESH);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(ID);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				match(LCURLY);
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FRESH || _la==ID) {
					{
					setState(75);
					qualElems();
					}
				}

				setState(78);
				match(RCURLY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdQtyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public IdQtyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idQty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterIdQty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitIdQty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitIdQty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdQtyContext idQty() throws RecognitionException {
		IdQtyContext _localctx = new IdQtyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_idQty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(ID);
			setState(82);
			match(COLON);
			setState(83);
			qty();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public IdQtyContext idQty() {
			return getRuleContext(IdQtyContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				idQty();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			param();
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(90);
				match(COMMA);
				setState(91);
				param();
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamedParamListContext extends ParserRuleContext {
		public List<IdQtyContext> idQty() {
			return getRuleContexts(IdQtyContext.class);
		}
		public IdQtyContext idQty(int i) {
			return getRuleContext(IdQtyContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public NamedParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedParamList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterNamedParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitNamedParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitNamedParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamedParamListContext namedParamList() throws RecognitionException {
		NamedParamListContext _localctx = new NamedParamListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_namedParamList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			idQty();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(98);
				match(COMMA);
				setState(99);
				idQty();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunTyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode RIGHTARROW() { return getToken(DiamondParser.RIGHTARROW, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public EffsContext effs() {
			return getRuleContext(EffsContext.class,0);
		}
		public FunTyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funTy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterFunTy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitFunTy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitFunTy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunTyContext funTy() throws RecognitionException {
		FunTyContext _localctx = new FunTyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funTy);
		int _la;
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				match(ID);
				setState(106);
				match(LPAREN);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4398059094528L) != 0)) {
					{
					setState(107);
					paramList();
					}
				}

				setState(110);
				match(RPAREN);
				setState(111);
				match(RIGHTARROW);
				setState(112);
				qty();
				setState(114);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(113);
					effs();
					}
					break;
				}
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				match(LPAREN);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4398059094528L) != 0)) {
					{
					setState(117);
					paramList();
					}
				}

				setState(120);
				match(RPAREN);
				setState(121);
				match(RIGHTARROW);
				setState(122);
				qty();
				setState(124);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(123);
					effs();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyParamContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(DiamondParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DiamondParser.ID, i);
		}
		public TerminalNode SUBTYPE() { return getToken(DiamondParser.SUBTYPE, 0); }
		public TyContext ty() {
			return getRuleContext(TyContext.class,0);
		}
		public TerminalNode HAT() { return getToken(DiamondParser.HAT, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TyParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tyParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTyParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTyParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTyParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyParamContext tyParam() throws RecognitionException {
		TyParamContext _localctx = new TyParamContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tyParam);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(ID);
				setState(130);
				match(SUBTYPE);
				setState(131);
				ty();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(ID);
				setState(133);
				match(HAT);
				setState(134);
				match(ID);
				setState(135);
				match(SUBTYPE);
				setState(136);
				qty();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyParamListContext extends ParserRuleContext {
		public List<TyParamContext> tyParam() {
			return getRuleContexts(TyParamContext.class);
		}
		public TyParamContext tyParam(int i) {
			return getRuleContext(TyParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public TyParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tyParamList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTyParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTyParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTyParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyParamListContext tyParamList() throws RecognitionException {
		TyParamListContext _localctx = new TyParamListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tyParamList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			tyParam();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(140);
				match(COMMA);
				setState(141);
				tyParam();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyFunTyContext extends ParserRuleContext {
		public TerminalNode FORALL() { return getToken(DiamondParser.FORALL, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyParamListContext tyParamList() {
			return getRuleContext(TyParamListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public TerminalNode RIGHTARROW() { return getToken(DiamondParser.RIGHTARROW, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TyFunTyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tyFunTy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTyFunTy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTyFunTy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTyFunTy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyFunTyContext tyFunTy() throws RecognitionException {
		TyFunTyContext _localctx = new TyFunTyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tyFunTy);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(FORALL);
				setState(148);
				match(LBRACKET);
				setState(149);
				tyParamList();
				setState(150);
				match(RBRACKET);
				setState(151);
				match(RIGHTARROW);
				setState(152);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(FORALL);
				setState(155);
				match(ID);
				setState(156);
				match(LBRACKET);
				setState(157);
				tyParamList();
				setState(158);
				match(RBRACKET);
				setState(159);
				match(RIGHTARROW);
				setState(160);
				qty();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode REF() { return getToken(DiamondParser.REF, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public FunTyContext funTy() {
			return getRuleContext(FunTyContext.class,0);
		}
		public TyFunTyContext tyFunTy() {
			return getRuleContext(TyFunTyContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TyContext ty() {
			return getRuleContext(TyContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyContext ty() throws RecognitionException {
		TyContext _localctx = new TyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ty);
		try {
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(REF);
				setState(166);
				match(LBRACKET);
				setState(167);
				qty();
				setState(168);
				match(RBRACKET);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				funTy();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				tyFunTy();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(172);
				match(LPAREN);
				setState(173);
				ty();
				setState(174);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QtyContext extends ParserRuleContext {
		public TyContext ty() {
			return getRuleContext(TyContext.class,0);
		}
		public TerminalNode HAT() { return getToken(DiamondParser.HAT, 0); }
		public QualContext qual() {
			return getRuleContext(QualContext.class,0);
		}
		public QtyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterQty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitQty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitQty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QtyContext qty() throws RecognitionException {
		QtyContext _localctx = new QtyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_qty);
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				ty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				ty();
				setState(180);
				match(HAT);
				setState(181);
				qual();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EffContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(DiamondParser.AT, 0); }
		public List<TerminalNode> ID() { return getTokens(DiamondParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DiamondParser.ID, i);
		}
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public EffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterEff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitEff(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitEff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffContext eff() throws RecognitionException {
		EffContext _localctx = new EffContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_eff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(AT);
			setState(186);
			match(ID);
			setState(187);
			match(LPAREN);
			setState(188);
			match(ID);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(189);
				match(COMMA);
				setState(190);
				match(ID);
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(196);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EffsContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(DiamondParser.AT, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public List<EffContext> eff() {
			return getRuleContexts(EffContext.class);
		}
		public EffContext eff(int i) {
			return getRuleContext(EffContext.class,i);
		}
		public EffsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_effs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterEffs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitEffs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitEffs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffsContext effs() throws RecognitionException {
		EffsContext _localctx = new EffsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_effs);
		try {
			int _alt;
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(AT);
				setState(199);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(200);
						eff();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(203); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolOp2Context extends ParserRuleContext {
		public TerminalNode AND() { return getToken(DiamondParser.AND, 0); }
		public TerminalNode OR() { return getToken(DiamondParser.OR, 0); }
		public TerminalNode EQ2() { return getToken(DiamondParser.EQ2, 0); }
		public TerminalNode NEQ() { return getToken(DiamondParser.NEQ, 0); }
		public BoolOp2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterBoolOp2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitBoolOp2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitBoolOp2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOp2Context boolOp2() throws RecognitionException {
		BoolOp2Context _localctx = new BoolOp2Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_boolOp2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 109521666048L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op1Context extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(DiamondParser.NOT, 0); }
		public Op1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterOp1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitOp1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitOp1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op1Context op1() throws RecognitionException {
		Op1Context _localctx = new Op1Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_op1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(NOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(DiamondParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(DiamondParser.FALSE, 0); }
		public TerminalNode UNIT() { return getToken(DiamondParser.UNIT, 0); }
		public TerminalNode INT() { return getToken(DiamondParser.INT, 0); }
		public LamContext lam() {
			return getRuleContext(LamContext.class,0);
		}
		public TyLamContext tyLam() {
			return getRuleContext(TyLamContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_value);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(TRUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(FALSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				match(UNIT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(215);
				lam();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(216);
				tyLam();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			expr(0);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(220);
				match(COMMA);
				setState(221);
				expr(0);
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyArgsContext extends ParserRuleContext {
		public List<QtyContext> qty() {
			return getRuleContexts(QtyContext.class);
		}
		public QtyContext qty(int i) {
			return getRuleContext(QtyContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public TyArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tyArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTyArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTyArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTyArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyArgsContext tyArgs() throws RecognitionException {
		TyArgsContext _localctx = new TyArgsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_tyArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			qty();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(228);
				match(COMMA);
				setState(229);
				qty();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WrapExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public WrapExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wrapExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterWrapExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitWrapExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitWrapExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WrapExprContext wrapExpr() throws RecognitionException {
		WrapExprContext _localctx = new WrapExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_wrapExpr);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(LPAREN);
				setState(236);
				expr(0);
				setState(237);
				match(RPAREN);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(LCURLY);
				setState(240);
				expr(0);
				setState(241);
				match(RCURLY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public AllocContext alloc() {
			return getRuleContext(AllocContext.class,0);
		}
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public MoveContext move() {
			return getRuleContext(MoveContext.class,0);
		}
		public WrapExprContext wrapExpr() {
			return getRuleContext(WrapExprContext.class,0);
		}
		public Op1Context op1() {
			return getRuleContext(Op1Context.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public FunDefContext funDef() {
			return getRuleContext(FunDefContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DiamondParser.SEMI, 0); }
		public TerminalNode IF() { return getToken(DiamondParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode ELSE() { return getToken(DiamondParser.ELSE, 0); }
		public TerminalNode MULT() { return getToken(DiamondParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(DiamondParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(DiamondParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(DiamondParser.MINUS, 0); }
		public BoolOp2Context boolOp2() {
			return getRuleContext(BoolOp2Context.class,0);
		}
		public TerminalNode COLONEQ() { return getToken(DiamondParser.COLONEQ, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode AT() { return getToken(DiamondParser.AT, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyArgsContext tyArgs() {
			return getRuleContext(TyArgsContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(246);
				match(ID);
				}
				break;
			case 2:
				{
				setState(247);
				value();
				}
				break;
			case 3:
				{
				setState(248);
				alloc();
				}
				break;
			case 4:
				{
				setState(249);
				deref();
				}
				break;
			case 5:
				{
				setState(250);
				move();
				}
				break;
			case 6:
				{
				setState(251);
				wrapExpr();
				}
				break;
			case 7:
				{
				setState(252);
				op1();
				setState(253);
				expr(8);
				}
				break;
			case 8:
				{
				setState(255);
				let();
				}
				break;
			case 9:
				{
				setState(256);
				funDef();
				setState(257);
				match(SEMI);
				setState(258);
				expr(3);
				}
				break;
			case 10:
				{
				setState(260);
				match(IF);
				setState(261);
				match(LPAREN);
				setState(262);
				expr(0);
				setState(263);
				match(RPAREN);
				setState(264);
				expr(0);
				setState(265);
				match(ELSE);
				setState(266);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(309);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(307);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(270);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(271);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(272);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(273);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(274);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(275);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(277);
						boolOp2();
						setState(278);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(280);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(281);
						match(COLONEQ);
						setState(282);
						expr(3);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(283);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(284);
						match(LPAREN);
						setState(286);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6607042718212L) != 0)) {
							{
							setState(285);
							args();
							}
						}

						setState(288);
						match(RPAREN);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(289);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(290);
						match(AT);
						setState(291);
						match(LPAREN);
						setState(293);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6607042718212L) != 0)) {
							{
							setState(292);
							args();
							}
						}

						setState(295);
						match(RPAREN);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(296);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(297);
						match(LBRACKET);
						setState(298);
						tyArgs();
						setState(299);
						match(RBRACKET);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(302);
						match(AT);
						setState(303);
						match(LBRACKET);
						setState(304);
						tyArgs();
						setState(305);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllocContext extends ParserRuleContext {
		public TerminalNode REF() { return getToken(DiamondParser.REF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AllocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterAlloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitAlloc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitAlloc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllocContext alloc() throws RecognitionException {
		AllocContext _localctx = new AllocContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_alloc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(REF);
			setState(313);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DerefContext extends ParserRuleContext {
		public TerminalNode EXCLA() { return getToken(DiamondParser.EXCLA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DerefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterDeref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitDeref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitDeref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DerefContext deref() throws RecognitionException {
		DerefContext _localctx = new DerefContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(EXCLA);
			setState(316);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoveContext extends ParserRuleContext {
		public TerminalNode MOVE() { return getToken(DiamondParser.MOVE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_move; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitMove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(MOVE);
			setState(319);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode RIGHTARROW() { return getToken(DiamondParser.RIGHTARROW, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public NamedParamListContext namedParamList() {
			return getRuleContext(NamedParamListContext.class,0);
		}
		public TerminalNode HAT() { return getToken(DiamondParser.HAT, 0); }
		public QualContext qual() {
			return getRuleContext(QualContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public EffsContext effs() {
			return getRuleContext(EffsContext.class,0);
		}
		public LamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterLam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitLam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitLam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LamContext lam() throws RecognitionException {
		LamContext _localctx = new LamContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lam);
		int _la;
		try {
			setState(360);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				match(ID);
				setState(322);
				match(LPAREN);
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(323);
					namedParamList();
					}
				}

				setState(326);
				match(RPAREN);
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==HAT) {
					{
					setState(327);
					match(HAT);
					setState(328);
					qual();
					}
				}

				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(331);
					match(COLON);
					setState(332);
					qty();
					setState(334);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT) {
						{
						setState(333);
						effs();
						}
					}

					}
				}

				setState(338);
				match(RIGHTARROW);
				setState(339);
				match(LCURLY);
				setState(340);
				expr(0);
				setState(341);
				match(RCURLY);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				match(LPAREN);
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(344);
					namedParamList();
					}
				}

				setState(347);
				match(RPAREN);
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(348);
					match(COLON);
					setState(349);
					qty();
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT) {
						{
						setState(350);
						effs();
						}
					}

					}
				}

				setState(355);
				match(RIGHTARROW);
				setState(356);
				match(LCURLY);
				setState(357);
				expr(0);
				setState(358);
				match(RCURLY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TyLamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyParamListContext tyParamList() {
			return getRuleContext(TyParamListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public TerminalNode RIGHTARROW() { return getToken(DiamondParser.RIGHTARROW, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public TerminalNode HAT() { return getToken(DiamondParser.HAT, 0); }
		public QualContext qual() {
			return getRuleContext(QualContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TyLamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tyLam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterTyLam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitTyLam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitTyLam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TyLamContext tyLam() throws RecognitionException {
		TyLamContext _localctx = new TyLamContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_tyLam);
		int _la;
		try {
			setState(391);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(ID);
				setState(363);
				match(LBRACKET);
				setState(364);
				tyParamList();
				setState(365);
				match(RBRACKET);
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==HAT) {
					{
					setState(366);
					match(HAT);
					setState(367);
					qual();
					}
				}

				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(370);
					match(COLON);
					setState(371);
					qty();
					}
				}

				setState(374);
				match(RIGHTARROW);
				setState(375);
				match(LCURLY);
				setState(376);
				expr(0);
				setState(377);
				match(RCURLY);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				match(LBRACKET);
				setState(380);
				tyParamList();
				setState(381);
				match(RBRACKET);
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(382);
					match(COLON);
					setState(383);
					qty();
					}
				}

				setState(386);
				match(RIGHTARROW);
				setState(387);
				match(LCURLY);
				setState(388);
				expr(0);
				setState(389);
				match(RCURLY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValDeclContext extends ParserRuleContext {
		public TerminalNode VAL() { return getToken(DiamondParser.VAL, 0); }
		public TerminalNode TOPVAL() { return getToken(DiamondParser.TOPVAL, 0); }
		public ValDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterValDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitValDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitValDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValDeclContext valDecl() throws RecognitionException {
		ValDeclContext _localctx = new ValDeclContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_valDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			_la = _input.LA(1);
			if ( !(_la==VAL || _la==TOPVAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetContext extends ParserRuleContext {
		public ValDeclContext valDecl() {
			return getRuleContext(ValDeclContext.class,0);
		}
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode EQ() { return getToken(DiamondParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(DiamondParser.SEMI, 0); }
		public IdQtyContext idQty() {
			return getRuleContext(IdQtyContext.class,0);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_let);
		try {
			setState(409);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				valDecl();
				setState(396);
				match(ID);
				setState(397);
				match(EQ);
				setState(398);
				expr(0);
				setState(399);
				match(SEMI);
				setState(400);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				valDecl();
				setState(403);
				idQty();
				setState(404);
				match(EQ);
				setState(405);
				expr(0);
				setState(406);
				match(SEMI);
				setState(407);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunDefContext extends ParserRuleContext {
		public FunDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDef; }
	 
		public FunDefContext() { }
		public void copyFrom(FunDefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PolyFunDefContext extends FunDefContext {
		public TerminalNode DEF() { return getToken(DiamondParser.DEF, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyParamListContext tyParamList() {
			return getRuleContext(TyParamListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode EQ() { return getToken(DiamondParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NamedParamListContext namedParamList() {
			return getRuleContext(NamedParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public PolyFunDefContext(FunDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterPolyFunDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitPolyFunDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitPolyFunDef(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MonoFunDefContext extends FunDefContext {
		public TerminalNode DEF() { return getToken(DiamondParser.DEF, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode EQ() { return getToken(DiamondParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NamedParamListContext namedParamList() {
			return getRuleContext(NamedParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public EffsContext effs() {
			return getRuleContext(EffsContext.class,0);
		}
		public MonoFunDefContext(FunDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterMonoFunDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitMonoFunDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitMonoFunDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDefContext funDef() throws RecognitionException {
		FunDefContext _localctx = new FunDefContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_funDef);
		int _la;
		try {
			setState(444);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				_localctx = new MonoFunDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(411);
				match(DEF);
				setState(412);
				match(ID);
				setState(413);
				match(LPAREN);
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(414);
					namedParamList();
					}
				}

				setState(417);
				match(RPAREN);
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(418);
					match(COLON);
					setState(419);
					qty();
					setState(421);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT) {
						{
						setState(420);
						effs();
						}
					}

					}
				}

				setState(425);
				match(EQ);
				setState(426);
				expr(0);
				}
				break;
			case 2:
				_localctx = new PolyFunDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(427);
				match(DEF);
				setState(428);
				match(ID);
				setState(429);
				match(LBRACKET);
				setState(430);
				tyParamList();
				setState(431);
				match(RBRACKET);
				setState(432);
				match(LPAREN);
				setState(434);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(433);
					namedParamList();
					}
				}

				setState(436);
				match(RPAREN);
				setState(439);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(437);
					match(COLON);
					setState(438);
					qty();
					}
				}

				setState(441);
				match(EQ);
				setState(442);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DiamondParser.EOF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6607042718212L) != 0)) {
				{
				{
				setState(446);
				expr(0);
				}
				}
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(452);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001,\u01c7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"D\b\u0001\n\u0001\f\u0001G\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002M\b\u0002\u0001\u0002\u0003\u0002P\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003"+
		"\u0004X\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005]\b\u0005"+
		"\n\u0005\f\u0005`\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006"+
		"e\b\u0006\n\u0006\f\u0006h\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007m\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007s\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007w\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007}\b\u0007\u0003"+
		"\u0007\u007f\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u008a\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u008f"+
		"\b\t\n\t\f\t\u0092\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00a3\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00b1\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00b8\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u00c0\b\r\n\r\f\r\u00c3\t\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0004\u000e\u00ca\b\u000e\u000b\u000e\f\u000e\u00cb"+
		"\u0003\u000e\u00ce\b\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u00da\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u00df\b\u0012\n\u0012\f\u0012\u00e2\t\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u00e7\b\u0013\n\u0013\f\u0013\u00ea\t\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u00f4\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u010d\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u011f\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0126\b\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015"+
		"\u0134\b\u0015\n\u0015\f\u0015\u0137\t\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0145\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u014a\b\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u014f\b\u0019\u0003\u0019\u0151\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u015a\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u0160\b\u0019\u0003\u0019\u0162\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0169"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0171\b\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0175"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0181"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0188\b\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u019a\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u01a0\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u01a6\b\u001d\u0003\u001d\u01a8\b\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0003\u001d\u01b3\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u01b8\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u01bd\b\u001d\u0001\u001e\u0005\u001e\u01c0\b\u001e\n\u001e\f\u001e\u01c3"+
		"\t\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0000\u0001*\u001f\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<\u0000\u0005\u0002\u0000\u0006\u0006**\u0002\u0000"+
		"\u001f #$\u0001\u0000\'(\u0001\u0000%&\u0001\u0000\u0012\u0013\u01ee\u0000"+
		">\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004O\u0001"+
		"\u0000\u0000\u0000\u0006Q\u0001\u0000\u0000\u0000\bW\u0001\u0000\u0000"+
		"\u0000\nY\u0001\u0000\u0000\u0000\fa\u0001\u0000\u0000\u0000\u000e~\u0001"+
		"\u0000\u0000\u0000\u0010\u0089\u0001\u0000\u0000\u0000\u0012\u008b\u0001"+
		"\u0000\u0000\u0000\u0014\u00a2\u0001\u0000\u0000\u0000\u0016\u00b0\u0001"+
		"\u0000\u0000\u0000\u0018\u00b7\u0001\u0000\u0000\u0000\u001a\u00b9\u0001"+
		"\u0000\u0000\u0000\u001c\u00cd\u0001\u0000\u0000\u0000\u001e\u00cf\u0001"+
		"\u0000\u0000\u0000 \u00d1\u0001\u0000\u0000\u0000\"\u00d9\u0001\u0000"+
		"\u0000\u0000$\u00db\u0001\u0000\u0000\u0000&\u00e3\u0001\u0000\u0000\u0000"+
		"(\u00f3\u0001\u0000\u0000\u0000*\u010c\u0001\u0000\u0000\u0000,\u0138"+
		"\u0001\u0000\u0000\u0000.\u013b\u0001\u0000\u0000\u00000\u013e\u0001\u0000"+
		"\u0000\u00002\u0168\u0001\u0000\u0000\u00004\u0187\u0001\u0000\u0000\u0000"+
		"6\u0189\u0001\u0000\u0000\u00008\u0199\u0001\u0000\u0000\u0000:\u01bc"+
		"\u0001\u0000\u0000\u0000<\u01c1\u0001\u0000\u0000\u0000>?\u0007\u0000"+
		"\u0000\u0000?\u0001\u0001\u0000\u0000\u0000@E\u0003\u0000\u0000\u0000"+
		"AB\u0005\u0007\u0000\u0000BD\u0003\u0000\u0000\u0000CA\u0001\u0000\u0000"+
		"\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000F\u0003\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000"+
		"HP\u0005\u0006\u0000\u0000IP\u0005*\u0000\u0000JL\u0005\u000b\u0000\u0000"+
		"KM\u0003\u0002\u0001\u0000LK\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000"+
		"\u0000MN\u0001\u0000\u0000\u0000NP\u0005\f\u0000\u0000OH\u0001\u0000\u0000"+
		"\u0000OI\u0001\u0000\u0000\u0000OJ\u0001\u0000\u0000\u0000P\u0005\u0001"+
		"\u0000\u0000\u0000QR\u0005*\u0000\u0000RS\u0005\u0003\u0000\u0000ST\u0003"+
		"\u0018\f\u0000T\u0007\u0001\u0000\u0000\u0000UX\u0003\u0018\f\u0000VX"+
		"\u0003\u0006\u0003\u0000WU\u0001\u0000\u0000\u0000WV\u0001\u0000\u0000"+
		"\u0000X\t\u0001\u0000\u0000\u0000Y^\u0003\b\u0004\u0000Z[\u0005\u0007"+
		"\u0000\u0000[]\u0003\b\u0004\u0000\\Z\u0001\u0000\u0000\u0000]`\u0001"+
		"\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000"+
		"_\u000b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000af\u0003\u0006"+
		"\u0003\u0000bc\u0005\u0007\u0000\u0000ce\u0003\u0006\u0003\u0000db\u0001"+
		"\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000g\r\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000"+
		"\u0000ij\u0005*\u0000\u0000jl\u0005\t\u0000\u0000km\u0003\n\u0005\u0000"+
		"lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000"+
		"\u0000no\u0005\n\u0000\u0000op\u0005\u000f\u0000\u0000pr\u0003\u0018\f"+
		"\u0000qs\u0003\u001c\u000e\u0000rq\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000s\u007f\u0001\u0000\u0000\u0000tv\u0005\t\u0000\u0000uw\u0003"+
		"\n\u0005\u0000vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xy\u0005\n\u0000\u0000yz\u0005\u000f\u0000\u0000z|\u0003"+
		"\u0018\f\u0000{}\u0003\u001c\u000e\u0000|{\u0001\u0000\u0000\u0000|}\u0001"+
		"\u0000\u0000\u0000}\u007f\u0001\u0000\u0000\u0000~i\u0001\u0000\u0000"+
		"\u0000~t\u0001\u0000\u0000\u0000\u007f\u000f\u0001\u0000\u0000\u0000\u0080"+
		"\u008a\u0005*\u0000\u0000\u0081\u0082\u0005*\u0000\u0000\u0082\u0083\u0005"+
		"\u0018\u0000\u0000\u0083\u008a\u0003\u0016\u000b\u0000\u0084\u0085\u0005"+
		"*\u0000\u0000\u0085\u0086\u0005\u0005\u0000\u0000\u0086\u0087\u0005*\u0000"+
		"\u0000\u0087\u0088\u0005\u0018\u0000\u0000\u0088\u008a\u0003\u0018\f\u0000"+
		"\u0089\u0080\u0001\u0000\u0000\u0000\u0089\u0081\u0001\u0000\u0000\u0000"+
		"\u0089\u0084\u0001\u0000\u0000\u0000\u008a\u0011\u0001\u0000\u0000\u0000"+
		"\u008b\u0090\u0003\u0010\b\u0000\u008c\u008d\u0005\u0007\u0000\u0000\u008d"+
		"\u008f\u0003\u0010\b\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0092"+
		"\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0001\u0000\u0000\u0000\u0091\u0013\u0001\u0000\u0000\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0005\u0017\u0000\u0000\u0094\u0095"+
		"\u0005\r\u0000\u0000\u0095\u0096\u0003\u0012\t\u0000\u0096\u0097\u0005"+
		"\u000e\u0000\u0000\u0097\u0098\u0005\u000f\u0000\u0000\u0098\u0099\u0003"+
		"\u0018\f\u0000\u0099\u00a3\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u0017"+
		"\u0000\u0000\u009b\u009c\u0005*\u0000\u0000\u009c\u009d\u0005\r\u0000"+
		"\u0000\u009d\u009e\u0003\u0012\t\u0000\u009e\u009f\u0005\u000e\u0000\u0000"+
		"\u009f\u00a0\u0005\u000f\u0000\u0000\u00a0\u00a1\u0003\u0018\f\u0000\u00a1"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a2\u0093\u0001\u0000\u0000\u0000\u00a2"+
		"\u009a\u0001\u0000\u0000\u0000\u00a3\u0015\u0001\u0000\u0000\u0000\u00a4"+
		"\u00b1\u0005*\u0000\u0000\u00a5\u00a6\u0005\u0016\u0000\u0000\u00a6\u00a7"+
		"\u0005\r\u0000\u0000\u00a7\u00a8\u0003\u0018\f\u0000\u00a8\u00a9\u0005"+
		"\u000e\u0000\u0000\u00a9\u00b1\u0001\u0000\u0000\u0000\u00aa\u00b1\u0003"+
		"\u000e\u0007\u0000\u00ab\u00b1\u0003\u0014\n\u0000\u00ac\u00ad\u0005\t"+
		"\u0000\u0000\u00ad\u00ae\u0003\u0016\u000b\u0000\u00ae\u00af\u0005\n\u0000"+
		"\u0000\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00a4\u0001\u0000\u0000"+
		"\u0000\u00b0\u00a5\u0001\u0000\u0000\u0000\u00b0\u00aa\u0001\u0000\u0000"+
		"\u0000\u00b0\u00ab\u0001\u0000\u0000\u0000\u00b0\u00ac\u0001\u0000\u0000"+
		"\u0000\u00b1\u0017\u0001\u0000\u0000\u0000\u00b2\u00b8\u0003\u0016\u000b"+
		"\u0000\u00b3\u00b4\u0003\u0016\u000b\u0000\u00b4\u00b5\u0005\u0005\u0000"+
		"\u0000\u00b5\u00b6\u0003\u0004\u0002\u0000\u00b6\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b2\u0001\u0000\u0000\u0000\u00b7\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b8\u0019\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\u0001\u0000"+
		"\u0000\u00ba\u00bb\u0005*\u0000\u0000\u00bb\u00bc\u0005\t\u0000\u0000"+
		"\u00bc\u00c1\u0005*\u0000\u0000\u00bd\u00be\u0005\u0007\u0000\u0000\u00be"+
		"\u00c0\u0005*\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\n\u0000\u0000\u00c5\u001b\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0005\u0001\u0000\u0000\u00c7\u00ce\u0005"+
		"*\u0000\u0000\u00c8\u00ca\u0003\u001a\r\u0000\u00c9\u00c8\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cd\u00c6\u0001\u0000\u0000\u0000\u00cd\u00c9\u0001\u0000"+
		"\u0000\u0000\u00ce\u001d\u0001\u0000\u0000\u0000\u00cf\u00d0\u0007\u0001"+
		"\u0000\u0000\u00d0\u001f\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005!\u0000"+
		"\u0000\u00d2!\u0001\u0000\u0000\u0000\u00d3\u00da\u0005\u0010\u0000\u0000"+
		"\u00d4\u00da\u0005\u0011\u0000\u0000\u00d5\u00da\u0005\u0019\u0000\u0000"+
		"\u00d6\u00da\u0005)\u0000\u0000\u00d7\u00da\u00032\u0019\u0000\u00d8\u00da"+
		"\u00034\u001a\u0000\u00d9\u00d3\u0001\u0000\u0000\u0000\u00d9\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d5\u0001\u0000\u0000\u0000\u00d9\u00d6\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9\u00d8\u0001"+
		"\u0000\u0000\u0000\u00da#\u0001\u0000\u0000\u0000\u00db\u00e0\u0003*\u0015"+
		"\u0000\u00dc\u00dd\u0005\u0007\u0000\u0000\u00dd\u00df\u0003*\u0015\u0000"+
		"\u00de\u00dc\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e1%\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e8\u0003\u0018\f\u0000\u00e4\u00e5\u0005\u0007\u0000\u0000\u00e5\u00e7"+
		"\u0003\u0018\f\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00ea\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001"+
		"\u0000\u0000\u0000\u00e9\'\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ec\u0005\t\u0000\u0000\u00ec\u00ed\u0003*\u0015"+
		"\u0000\u00ed\u00ee\u0005\n\u0000\u0000\u00ee\u00f4\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f0\u0005\u000b\u0000\u0000\u00f0\u00f1\u0003*\u0015\u0000\u00f1"+
		"\u00f2\u0005\f\u0000\u0000\u00f2\u00f4\u0001\u0000\u0000\u0000\u00f3\u00eb"+
		"\u0001\u0000\u0000\u0000\u00f3\u00ef\u0001\u0000\u0000\u0000\u00f4)\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0006\u0015\uffff\uffff\u0000\u00f6\u010d"+
		"\u0005*\u0000\u0000\u00f7\u010d\u0003\"\u0011\u0000\u00f8\u010d\u0003"+
		",\u0016\u0000\u00f9\u010d\u0003.\u0017\u0000\u00fa\u010d\u00030\u0018"+
		"\u0000\u00fb\u010d\u0003(\u0014\u0000\u00fc\u00fd\u0003 \u0010\u0000\u00fd"+
		"\u00fe\u0003*\u0015\b\u00fe\u010d\u0001\u0000\u0000\u0000\u00ff\u010d"+
		"\u00038\u001c\u0000\u0100\u0101\u0003:\u001d\u0000\u0101\u0102\u0005\b"+
		"\u0000\u0000\u0102\u0103\u0003*\u0015\u0003\u0103\u010d\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0005\u001c\u0000\u0000\u0105\u0106\u0005\t\u0000\u0000"+
		"\u0106\u0107\u0003*\u0015\u0000\u0107\u0108\u0005\n\u0000\u0000\u0108"+
		"\u0109\u0003*\u0015\u0000\u0109\u010a\u0005\u001d\u0000\u0000\u010a\u010b"+
		"\u0003*\u0015\u0001\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u00f5\u0001"+
		"\u0000\u0000\u0000\u010c\u00f7\u0001\u0000\u0000\u0000\u010c\u00f8\u0001"+
		"\u0000\u0000\u0000\u010c\u00f9\u0001\u0000\u0000\u0000\u010c\u00fa\u0001"+
		"\u0000\u0000\u0000\u010c\u00fb\u0001\u0000\u0000\u0000\u010c\u00fc\u0001"+
		"\u0000\u0000\u0000\u010c\u00ff\u0001\u0000\u0000\u0000\u010c\u0100\u0001"+
		"\u0000\u0000\u0000\u010c\u0104\u0001\u0000\u0000\u0000\u010d\u0135\u0001"+
		"\u0000\u0000\u0000\u010e\u010f\n\u0007\u0000\u0000\u010f\u0110\u0007\u0002"+
		"\u0000\u0000\u0110\u0134\u0003*\u0015\b\u0111\u0112\n\u0006\u0000\u0000"+
		"\u0112\u0113\u0007\u0003\u0000\u0000\u0113\u0134\u0003*\u0015\u0007\u0114"+
		"\u0115\n\u0005\u0000\u0000\u0115\u0116\u0003\u001e\u000f\u0000\u0116\u0117"+
		"\u0003*\u0015\u0006\u0117\u0134\u0001\u0000\u0000\u0000\u0118\u0119\n"+
		"\u0002\u0000\u0000\u0119\u011a\u0005\u0004\u0000\u0000\u011a\u0134\u0003"+
		"*\u0015\u0003\u011b\u011c\n\f\u0000\u0000\u011c\u011e\u0005\t\u0000\u0000"+
		"\u011d\u011f\u0003$\u0012\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011e"+
		"\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120"+
		"\u0134\u0005\n\u0000\u0000\u0121\u0122\n\u000b\u0000\u0000\u0122\u0123"+
		"\u0005\u0001\u0000\u0000\u0123\u0125\u0005\t\u0000\u0000\u0124\u0126\u0003"+
		"$\u0012\u0000\u0125\u0124\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000"+
		"\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0134\u0005\n\u0000"+
		"\u0000\u0128\u0129\n\n\u0000\u0000\u0129\u012a\u0005\r\u0000\u0000\u012a"+
		"\u012b\u0003&\u0013\u0000\u012b\u012c\u0005\u000e\u0000\u0000\u012c\u0134"+
		"\u0001\u0000\u0000\u0000\u012d\u012e\n\t\u0000\u0000\u012e\u012f\u0005"+
		"\u0001\u0000\u0000\u012f\u0130\u0005\r\u0000\u0000\u0130\u0131\u0003&"+
		"\u0013\u0000\u0131\u0132\u0005\u000e\u0000\u0000\u0132\u0134\u0001\u0000"+
		"\u0000\u0000\u0133\u010e\u0001\u0000\u0000\u0000\u0133\u0111\u0001\u0000"+
		"\u0000\u0000\u0133\u0114\u0001\u0000\u0000\u0000\u0133\u0118\u0001\u0000"+
		"\u0000\u0000\u0133\u011b\u0001\u0000\u0000\u0000\u0133\u0121\u0001\u0000"+
		"\u0000\u0000\u0133\u0128\u0001\u0000\u0000\u0000\u0133\u012d\u0001\u0000"+
		"\u0000\u0000\u0134\u0137\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136+\u0001\u0000\u0000"+
		"\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0138\u0139\u0005\u0016\u0000"+
		"\u0000\u0139\u013a\u0003*\u0015\u0000\u013a-\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0005\u0002\u0000\u0000\u013c\u013d\u0003*\u0015\u0000\u013d/\u0001"+
		"\u0000\u0000\u0000\u013e\u013f\u0005\u001e\u0000\u0000\u013f\u0140\u0003"+
		"*\u0015\u0000\u01401\u0001\u0000\u0000\u0000\u0141\u0142\u0005*\u0000"+
		"\u0000\u0142\u0144\u0005\t\u0000\u0000\u0143\u0145\u0003\f\u0006\u0000"+
		"\u0144\u0143\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000"+
		"\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0149\u0005\n\u0000\u0000\u0147"+
		"\u0148\u0005\u0005\u0000\u0000\u0148\u014a\u0003\u0004\u0002\u0000\u0149"+
		"\u0147\u0001\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a"+
		"\u0150\u0001\u0000\u0000\u0000\u014b\u014c\u0005\u0003\u0000\u0000\u014c"+
		"\u014e\u0003\u0018\f\u0000\u014d\u014f\u0003\u001c\u000e\u0000\u014e\u014d"+
		"\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0151"+
		"\u0001\u0000\u0000\u0000\u0150\u014b\u0001\u0000\u0000\u0000\u0150\u0151"+
		"\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0005\u000f\u0000\u0000\u0153\u0154\u0005\u000b\u0000\u0000\u0154\u0155"+
		"\u0003*\u0015\u0000\u0155\u0156\u0005\f\u0000\u0000\u0156\u0169\u0001"+
		"\u0000\u0000\u0000\u0157\u0159\u0005\t\u0000\u0000\u0158\u015a\u0003\f"+
		"\u0006\u0000\u0159\u0158\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000"+
		"\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b\u0161\u0005\n\u0000"+
		"\u0000\u015c\u015d\u0005\u0003\u0000\u0000\u015d\u015f\u0003\u0018\f\u0000"+
		"\u015e\u0160\u0003\u001c\u000e\u0000\u015f\u015e\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u0162\u0001\u0000\u0000\u0000"+
		"\u0161\u015c\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000"+
		"\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0005\u000f\u0000\u0000"+
		"\u0164\u0165\u0005\u000b\u0000\u0000\u0165\u0166\u0003*\u0015\u0000\u0166"+
		"\u0167\u0005\f\u0000\u0000\u0167\u0169\u0001\u0000\u0000\u0000\u0168\u0141"+
		"\u0001\u0000\u0000\u0000\u0168\u0157\u0001\u0000\u0000\u0000\u01693\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0005*\u0000\u0000\u016b\u016c\u0005\r"+
		"\u0000\u0000\u016c\u016d\u0003\u0012\t\u0000\u016d\u0170\u0005\u000e\u0000"+
		"\u0000\u016e\u016f\u0005\u0005\u0000\u0000\u016f\u0171\u0003\u0004\u0002"+
		"\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000"+
		"\u0000\u0171\u0174\u0001\u0000\u0000\u0000\u0172\u0173\u0005\u0003\u0000"+
		"\u0000\u0173\u0175\u0003\u0018\f\u0000\u0174\u0172\u0001\u0000\u0000\u0000"+
		"\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000"+
		"\u0176\u0177\u0005\u000f\u0000\u0000\u0177\u0178\u0005\u000b\u0000\u0000"+
		"\u0178\u0179\u0003*\u0015\u0000\u0179\u017a\u0005\f\u0000\u0000\u017a"+
		"\u0188\u0001\u0000\u0000\u0000\u017b\u017c\u0005\r\u0000\u0000\u017c\u017d"+
		"\u0003\u0012\t\u0000\u017d\u0180\u0005\u000e\u0000\u0000\u017e\u017f\u0005"+
		"\u0003\u0000\u0000\u017f\u0181\u0003\u0018\f\u0000\u0180\u017e\u0001\u0000"+
		"\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000"+
		"\u0000\u0000\u0182\u0183\u0005\u000f\u0000\u0000\u0183\u0184\u0005\u000b"+
		"\u0000\u0000\u0184\u0185\u0003*\u0015\u0000\u0185\u0186\u0005\f\u0000"+
		"\u0000\u0186\u0188\u0001\u0000\u0000\u0000\u0187\u016a\u0001\u0000\u0000"+
		"\u0000\u0187\u017b\u0001\u0000\u0000\u0000\u01885\u0001\u0000\u0000\u0000"+
		"\u0189\u018a\u0007\u0004\u0000\u0000\u018a7\u0001\u0000\u0000\u0000\u018b"+
		"\u018c\u00036\u001b\u0000\u018c\u018d\u0005*\u0000\u0000\u018d\u018e\u0005"+
		"\"\u0000\u0000\u018e\u018f\u0003*\u0015\u0000\u018f\u0190\u0005\b\u0000"+
		"\u0000\u0190\u0191\u0003*\u0015\u0000\u0191\u019a\u0001\u0000\u0000\u0000"+
		"\u0192\u0193\u00036\u001b\u0000\u0193\u0194\u0003\u0006\u0003\u0000\u0194"+
		"\u0195\u0005\"\u0000\u0000\u0195\u0196\u0003*\u0015\u0000\u0196\u0197"+
		"\u0005\b\u0000\u0000\u0197\u0198\u0003*\u0015\u0000\u0198\u019a\u0001"+
		"\u0000\u0000\u0000\u0199\u018b\u0001\u0000\u0000\u0000\u0199\u0192\u0001"+
		"\u0000\u0000\u0000\u019a9\u0001\u0000\u0000\u0000\u019b\u019c\u0005\u0015"+
		"\u0000\u0000\u019c\u019d\u0005*\u0000\u0000\u019d\u019f\u0005\t\u0000"+
		"\u0000\u019e\u01a0\u0003\f\u0006\u0000\u019f\u019e\u0001\u0000\u0000\u0000"+
		"\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000"+
		"\u01a1\u01a7\u0005\n\u0000\u0000\u01a2\u01a3\u0005\u0003\u0000\u0000\u01a3"+
		"\u01a5\u0003\u0018\f\u0000\u01a4\u01a6\u0003\u001c\u000e\u0000\u01a5\u01a4"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000\u0000\u0000\u01a6\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a2\u0001\u0000\u0000\u0000\u01a7\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9\u01aa"+
		"\u0005\"\u0000\u0000\u01aa\u01bd\u0003*\u0015\u0000\u01ab\u01ac\u0005"+
		"\u0015\u0000\u0000\u01ac\u01ad\u0005*\u0000\u0000\u01ad\u01ae\u0005\r"+
		"\u0000\u0000\u01ae\u01af\u0003\u0012\t\u0000\u01af\u01b0\u0005\u000e\u0000"+
		"\u0000\u01b0\u01b2\u0005\t\u0000\u0000\u01b1\u01b3\u0003\f\u0006\u0000"+
		"\u01b2\u01b1\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b4\u0001\u0000\u0000\u0000\u01b4\u01b7\u0005\n\u0000\u0000\u01b5"+
		"\u01b6\u0005\u0003\u0000\u0000\u01b6\u01b8\u0003\u0018\f\u0000\u01b7\u01b5"+
		"\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000\u0000\u0000\u01b8\u01b9"+
		"\u0001\u0000\u0000\u0000\u01b9\u01ba\u0005\"\u0000\u0000\u01ba\u01bb\u0003"+
		"*\u0015\u0000\u01bb\u01bd\u0001\u0000\u0000\u0000\u01bc\u019b\u0001\u0000"+
		"\u0000\u0000\u01bc\u01ab\u0001\u0000\u0000\u0000\u01bd;\u0001\u0000\u0000"+
		"\u0000\u01be\u01c0\u0003*\u0015\u0000\u01bf\u01be\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c3\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000"+
		"\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c4\u01c5\u0005\u0000\u0000\u0001"+
		"\u01c5=\u0001\u0000\u0000\u00000ELOW^flrv|~\u0089\u0090\u00a2\u00b0\u00b7"+
		"\u00c1\u00cb\u00cd\u00d9\u00e0\u00e8\u00f3\u010c\u011e\u0125\u0133\u0135"+
		"\u0144\u0149\u014e\u0150\u0159\u015f\u0161\u0168\u0170\u0174\u0180\u0187"+
		"\u0199\u019f\u01a5\u01a7\u01b2\u01b7\u01bc\u01c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}