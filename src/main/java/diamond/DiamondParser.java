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
		SUBTYPE=24, UNIT=25, LAM=26, TYLAM=27, AND=28, OR=29, NOT=30, EQ=31, EQ2=32, 
		NEQ=33, ADD=34, MINUS=35, MULT=36, DIV=37, INT=38, ID=39, WS=40, COMMENT=41;
	public static final int
		RULE_qualElem = 0, RULE_qualElems = 1, RULE_qual = 2, RULE_idQty = 3, 
		RULE_param = 4, RULE_paramList = 5, RULE_namedParamList = 6, RULE_funTy = 7, 
		RULE_tyParam = 8, RULE_tyParamList = 9, RULE_tyFunTy = 10, RULE_ty = 11, 
		RULE_qty = 12, RULE_boolOp2 = 13, RULE_op1 = 14, RULE_value = 15, RULE_args = 16, 
		RULE_tyArgs = 17, RULE_wrapExpr = 18, RULE_expr = 19, RULE_alloc = 20, 
		RULE_deref = 21, RULE_lam = 22, RULE_tyLam = 23, RULE_valDecl = 24, RULE_let = 25, 
		RULE_funDef = 26, RULE_program = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualElem", "qualElems", "qual", "idQty", "param", "paramList", "namedParamList", 
			"funTy", "tyParam", "tyParamList", "tyFunTy", "ty", "qty", "boolOp2", 
			"op1", "value", "args", "tyArgs", "wrapExpr", "expr", "alloc", "deref", 
			"lam", "tyLam", "valDecl", "let", "funDef", "program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'!'", "':'", "':='", "'^'", null, "','", "';'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'=>'", "'true'", "'false'", "'val'", 
			"'topval'", "'in'", "'def'", "'Ref'", "'forall'", "'<:'", "'unit'", "'lam'", 
			"'Lam'", "'&&'", "'||'", "'~'", "'='", "'=='", "'!='", "'+'", "'-'", 
			"'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", 
			"TRUE", "FALSE", "VAL", "TOPVAL", "IN", "DEF", "REF", "FORALL", "SUBTYPE", 
			"UNIT", "LAM", "TYLAM", "AND", "OR", "NOT", "EQ", "EQ2", "NEQ", "ADD", 
			"MINUS", "MULT", "DIV", "INT", "ID", "WS", "COMMENT"
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
			setState(56);
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
			setState(58);
			qualElem();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(59);
				match(COMMA);
				setState(60);
				qualElem();
				}
				}
				setState(65);
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
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FRESH:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(FRESH);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(ID);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(LCURLY);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FRESH || _la==ID) {
					{
					setState(69);
					qualElems();
					}
				}

				setState(72);
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
			setState(75);
			match(ID);
			setState(76);
			match(COLON);
			setState(77);
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
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
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
			setState(83);
			param();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				param();
				}
				}
				setState(90);
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
			setState(91);
			idQty();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(92);
				match(COMMA);
				setState(93);
				idQty();
				}
				}
				setState(98);
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
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(ID);
				setState(100);
				match(LPAREN);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 549768397312L) != 0)) {
					{
					setState(101);
					paramList();
					}
				}

				setState(104);
				match(RPAREN);
				setState(105);
				match(RIGHTARROW);
				setState(106);
				qty();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(LPAREN);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 549768397312L) != 0)) {
					{
					setState(108);
					paramList();
					}
				}

				setState(111);
				match(RPAREN);
				setState(112);
				match(RIGHTARROW);
				setState(113);
				qty();
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
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(ID);
				setState(118);
				match(SUBTYPE);
				setState(119);
				ty();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				match(ID);
				setState(121);
				match(HAT);
				setState(122);
				match(ID);
				setState(123);
				match(SUBTYPE);
				setState(124);
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
			setState(127);
			tyParam();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(128);
				match(COMMA);
				setState(129);
				tyParam();
				}
				}
				setState(134);
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
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				match(FORALL);
				setState(136);
				match(LBRACKET);
				setState(137);
				tyParamList();
				setState(138);
				match(RBRACKET);
				setState(139);
				match(RIGHTARROW);
				setState(140);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(FORALL);
				setState(143);
				match(ID);
				setState(144);
				match(LBRACKET);
				setState(145);
				tyParamList();
				setState(146);
				match(RBRACKET);
				setState(147);
				match(RIGHTARROW);
				setState(148);
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
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(REF);
				setState(154);
				match(LBRACKET);
				setState(155);
				qty();
				setState(156);
				match(RBRACKET);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				funTy();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
				tyFunTy();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(160);
				match(LPAREN);
				setState(161);
				ty();
				setState(162);
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
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				ty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				ty();
				setState(168);
				match(HAT);
				setState(169);
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
		enterRule(_localctx, 26, RULE_boolOp2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 13690208256L) != 0)) ) {
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
		enterRule(_localctx, 28, RULE_op1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
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
		enterRule(_localctx, 30, RULE_value);
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				match(TRUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(FALSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(179);
				match(UNIT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(180);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(181);
				lam();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(182);
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
		enterRule(_localctx, 32, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			expr(0);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(186);
				match(COMMA);
				setState(187);
				expr(0);
				}
				}
				setState(192);
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
		enterRule(_localctx, 34, RULE_tyArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			qty();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(194);
				match(COMMA);
				setState(195);
				qty();
				}
				}
				setState(200);
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
		enterRule(_localctx, 36, RULE_wrapExpr);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(LPAREN);
				setState(202);
				expr(0);
				setState(203);
				match(RPAREN);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(LCURLY);
				setState(206);
				expr(0);
				setState(207);
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
		public TerminalNode MULT() { return getToken(DiamondParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(DiamondParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(DiamondParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(DiamondParser.MINUS, 0); }
		public BoolOp2Context boolOp2() {
			return getRuleContext(BoolOp2Context.class,0);
		}
		public TerminalNode COLONEQ() { return getToken(DiamondParser.COLONEQ, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(212);
				match(ID);
				}
				break;
			case 2:
				{
				setState(213);
				value();
				}
				break;
			case 3:
				{
				setState(214);
				alloc();
				}
				break;
			case 4:
				{
				setState(215);
				deref();
				}
				break;
			case 5:
				{
				setState(216);
				wrapExpr();
				}
				break;
			case 6:
				{
				setState(217);
				op1();
				setState(218);
				expr(7);
				}
				break;
			case 7:
				{
				setState(220);
				let();
				}
				break;
			case 8:
				{
				setState(221);
				funDef();
				setState(222);
				match(SEMI);
				setState(223);
				expr(2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(264);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(227);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(228);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(229);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(230);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(231);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(232);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(233);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(234);
						boolOp2();
						setState(235);
						expr(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(237);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(238);
						match(COLONEQ);
						setState(239);
						expr(2);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(240);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(241);
						match(LPAREN);
						setState(243);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 825748302340L) != 0)) {
							{
							setState(242);
							args();
							}
						}

						setState(245);
						match(RPAREN);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(247);
						match(AT);
						setState(248);
						match(LPAREN);
						setState(250);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 825748302340L) != 0)) {
							{
							setState(249);
							args();
							}
						}

						setState(252);
						match(RPAREN);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(253);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(254);
						match(LBRACKET);
						setState(255);
						tyArgs();
						setState(256);
						match(RBRACKET);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(259);
						match(AT);
						setState(260);
						match(LBRACKET);
						setState(261);
						tyArgs();
						setState(262);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 40, RULE_alloc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(REF);
			setState(270);
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
		enterRule(_localctx, 42, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(EXCLA);
			setState(273);
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
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
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
		enterRule(_localctx, 44, RULE_lam);
		int _la;
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				match(ID);
				setState(276);
				match(LPAREN);
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(277);
					namedParamList();
					}
				}

				setState(280);
				match(RPAREN);
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(281);
					match(COLON);
					setState(282);
					qty();
					}
				}

				setState(285);
				match(RIGHTARROW);
				setState(286);
				match(LCURLY);
				setState(287);
				expr(0);
				setState(288);
				match(RCURLY);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(LPAREN);
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(291);
					namedParamList();
					}
				}

				setState(294);
				match(RPAREN);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(295);
					match(COLON);
					setState(296);
					qty();
					}
				}

				setState(299);
				match(RIGHTARROW);
				setState(300);
				match(LCURLY);
				setState(301);
				expr(0);
				setState(302);
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
		enterRule(_localctx, 46, RULE_tyLam);
		int _la;
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				match(ID);
				setState(307);
				match(LBRACKET);
				setState(308);
				tyParamList();
				setState(309);
				match(RBRACKET);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(310);
					match(COLON);
					setState(311);
					qty();
					}
				}

				setState(314);
				match(RIGHTARROW);
				setState(315);
				match(LCURLY);
				setState(316);
				expr(0);
				setState(317);
				match(RCURLY);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				match(LBRACKET);
				setState(320);
				tyParamList();
				setState(321);
				match(RBRACKET);
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(322);
					match(COLON);
					setState(323);
					qty();
					}
				}

				setState(326);
				match(RIGHTARROW);
				setState(327);
				match(LCURLY);
				setState(328);
				expr(0);
				setState(329);
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
		enterRule(_localctx, 48, RULE_valDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
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
		enterRule(_localctx, 50, RULE_let);
		try {
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(335);
				valDecl();
				setState(336);
				match(ID);
				setState(337);
				match(EQ);
				setState(338);
				expr(0);
				setState(339);
				match(SEMI);
				setState(340);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				valDecl();
				setState(343);
				idQty();
				setState(344);
				match(EQ);
				setState(345);
				expr(0);
				setState(346);
				match(SEMI);
				setState(347);
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
		enterRule(_localctx, 52, RULE_funDef);
		int _la;
		try {
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new MonoFunDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				match(DEF);
				setState(352);
				match(ID);
				setState(353);
				match(LPAREN);
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(354);
					namedParamList();
					}
				}

				setState(357);
				match(RPAREN);
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(358);
					match(COLON);
					setState(359);
					qty();
					}
				}

				setState(362);
				match(EQ);
				setState(363);
				expr(0);
				}
				break;
			case 2:
				_localctx = new PolyFunDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(364);
				match(DEF);
				setState(365);
				match(ID);
				setState(366);
				match(LBRACKET);
				setState(367);
				tyParamList();
				setState(368);
				match(RBRACKET);
				setState(369);
				match(LPAREN);
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(370);
					namedParamList();
					}
				}

				setState(373);
				match(RPAREN);
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(374);
					match(COLON);
					setState(375);
					qty();
					}
				}

				setState(378);
				match(EQ);
				setState(379);
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
		enterRule(_localctx, 54, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 825748302340L) != 0)) {
				{
				{
				setState(383);
				expr(0);
				}
				}
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(389);
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
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 1);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u0188\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		">\b\u0001\n\u0001\f\u0001A\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002G\b\u0002\u0001\u0002\u0003\u0002J\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003"+
		"\u0004R\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005W\b\u0005"+
		"\n\u0005\f\u0005Z\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006"+
		"_\b\u0006\n\u0006\f\u0006b\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007g\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007n\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007s\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0003\b~\b\b\u0001\t\u0001\t\u0001\t\u0005\t"+
		"\u0083\b\t\n\t\f\t\u0086\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u0097\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00a5\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00ac\b\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00b8\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00bd"+
		"\b\u0010\n\u0010\f\u0010\u00c0\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u00c5\b\u0011\n\u0011\f\u0011\u00c8\t\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u00d2\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00e2"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00f4"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u00fb\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0005\u0013\u0109\b\u0013\n\u0013\f\u0013\u010c\t\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0117\b\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u011c\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0125\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u012a\b"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u0131\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u0139\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u0145\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u014c\b\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u015e\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0164\b\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0003\u001a\u0169\b\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0174\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0179\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u017e"+
		"\b\u001a\u0001\u001b\u0005\u001b\u0181\b\u001b\n\u001b\f\u001b\u0184\t"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0000\u0001&\u001c\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.0246\u0000\u0005\u0002\u0000\u0006\u0006\'\'\u0002\u0000\u001c"+
		"\u001d !\u0001\u0000$%\u0001\u0000\"#\u0001\u0000\u0012\u0013\u01a6\u0000"+
		"8\u0001\u0000\u0000\u0000\u0002:\u0001\u0000\u0000\u0000\u0004I\u0001"+
		"\u0000\u0000\u0000\u0006K\u0001\u0000\u0000\u0000\bQ\u0001\u0000\u0000"+
		"\u0000\nS\u0001\u0000\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000er\u0001"+
		"\u0000\u0000\u0000\u0010}\u0001\u0000\u0000\u0000\u0012\u007f\u0001\u0000"+
		"\u0000\u0000\u0014\u0096\u0001\u0000\u0000\u0000\u0016\u00a4\u0001\u0000"+
		"\u0000\u0000\u0018\u00ab\u0001\u0000\u0000\u0000\u001a\u00ad\u0001\u0000"+
		"\u0000\u0000\u001c\u00af\u0001\u0000\u0000\u0000\u001e\u00b7\u0001\u0000"+
		"\u0000\u0000 \u00b9\u0001\u0000\u0000\u0000\"\u00c1\u0001\u0000\u0000"+
		"\u0000$\u00d1\u0001\u0000\u0000\u0000&\u00e1\u0001\u0000\u0000\u0000("+
		"\u010d\u0001\u0000\u0000\u0000*\u0110\u0001\u0000\u0000\u0000,\u0130\u0001"+
		"\u0000\u0000\u0000.\u014b\u0001\u0000\u0000\u00000\u014d\u0001\u0000\u0000"+
		"\u00002\u015d\u0001\u0000\u0000\u00004\u017d\u0001\u0000\u0000\u00006"+
		"\u0182\u0001\u0000\u0000\u000089\u0007\u0000\u0000\u00009\u0001\u0001"+
		"\u0000\u0000\u0000:?\u0003\u0000\u0000\u0000;<\u0005\u0007\u0000\u0000"+
		"<>\u0003\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>A\u0001\u0000\u0000"+
		"\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@\u0003\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BJ\u0005\u0006\u0000\u0000"+
		"CJ\u0005\'\u0000\u0000DF\u0005\u000b\u0000\u0000EG\u0003\u0002\u0001\u0000"+
		"FE\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000HJ\u0005\f\u0000\u0000IB\u0001\u0000\u0000\u0000IC\u0001\u0000\u0000"+
		"\u0000ID\u0001\u0000\u0000\u0000J\u0005\u0001\u0000\u0000\u0000KL\u0005"+
		"\'\u0000\u0000LM\u0005\u0003\u0000\u0000MN\u0003\u0018\f\u0000N\u0007"+
		"\u0001\u0000\u0000\u0000OR\u0003\u0018\f\u0000PR\u0003\u0006\u0003\u0000"+
		"QO\u0001\u0000\u0000\u0000QP\u0001\u0000\u0000\u0000R\t\u0001\u0000\u0000"+
		"\u0000SX\u0003\b\u0004\u0000TU\u0005\u0007\u0000\u0000UW\u0003\b\u0004"+
		"\u0000VT\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000Y\u000b\u0001\u0000\u0000\u0000"+
		"ZX\u0001\u0000\u0000\u0000[`\u0003\u0006\u0003\u0000\\]\u0005\u0007\u0000"+
		"\u0000]_\u0003\u0006\u0003\u0000^\\\u0001\u0000\u0000\u0000_b\u0001\u0000"+
		"\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a\r\u0001"+
		"\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005\'\u0000\u0000df\u0005"+
		"\t\u0000\u0000eg\u0003\n\u0005\u0000fe\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0005\n\u0000\u0000ij\u0005"+
		"\u000f\u0000\u0000js\u0003\u0018\f\u0000km\u0005\t\u0000\u0000ln\u0003"+
		"\n\u0005\u0000ml\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000no\u0001"+
		"\u0000\u0000\u0000op\u0005\n\u0000\u0000pq\u0005\u000f\u0000\u0000qs\u0003"+
		"\u0018\f\u0000rc\u0001\u0000\u0000\u0000rk\u0001\u0000\u0000\u0000s\u000f"+
		"\u0001\u0000\u0000\u0000t~\u0005\'\u0000\u0000uv\u0005\'\u0000\u0000v"+
		"w\u0005\u0018\u0000\u0000w~\u0003\u0016\u000b\u0000xy\u0005\'\u0000\u0000"+
		"yz\u0005\u0005\u0000\u0000z{\u0005\'\u0000\u0000{|\u0005\u0018\u0000\u0000"+
		"|~\u0003\u0018\f\u0000}t\u0001\u0000\u0000\u0000}u\u0001\u0000\u0000\u0000"+
		"}x\u0001\u0000\u0000\u0000~\u0011\u0001\u0000\u0000\u0000\u007f\u0084"+
		"\u0003\u0010\b\u0000\u0080\u0081\u0005\u0007\u0000\u0000\u0081\u0083\u0003"+
		"\u0010\b\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0013\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0005\u0017\u0000\u0000\u0088\u0089\u0005\r\u0000"+
		"\u0000\u0089\u008a\u0003\u0012\t\u0000\u008a\u008b\u0005\u000e\u0000\u0000"+
		"\u008b\u008c\u0005\u000f\u0000\u0000\u008c\u008d\u0003\u0018\f\u0000\u008d"+
		"\u0097\u0001\u0000\u0000\u0000\u008e\u008f\u0005\u0017\u0000\u0000\u008f"+
		"\u0090\u0005\'\u0000\u0000\u0090\u0091\u0005\r\u0000\u0000\u0091\u0092"+
		"\u0003\u0012\t\u0000\u0092\u0093\u0005\u000e\u0000\u0000\u0093\u0094\u0005"+
		"\u000f\u0000\u0000\u0094\u0095\u0003\u0018\f\u0000\u0095\u0097\u0001\u0000"+
		"\u0000\u0000\u0096\u0087\u0001\u0000\u0000\u0000\u0096\u008e\u0001\u0000"+
		"\u0000\u0000\u0097\u0015\u0001\u0000\u0000\u0000\u0098\u00a5\u0005\'\u0000"+
		"\u0000\u0099\u009a\u0005\u0016\u0000\u0000\u009a\u009b\u0005\r\u0000\u0000"+
		"\u009b\u009c\u0003\u0018\f\u0000\u009c\u009d\u0005\u000e\u0000\u0000\u009d"+
		"\u00a5\u0001\u0000\u0000\u0000\u009e\u00a5\u0003\u000e\u0007\u0000\u009f"+
		"\u00a5\u0003\u0014\n\u0000\u00a0\u00a1\u0005\t\u0000\u0000\u00a1\u00a2"+
		"\u0003\u0016\u000b\u0000\u00a2\u00a3\u0005\n\u0000\u0000\u00a3\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a4\u0098\u0001\u0000\u0000\u0000\u00a4\u0099\u0001"+
		"\u0000\u0000\u0000\u00a4\u009e\u0001\u0000\u0000\u0000\u00a4\u009f\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a5\u0017\u0001"+
		"\u0000\u0000\u0000\u00a6\u00ac\u0003\u0016\u000b\u0000\u00a7\u00a8\u0003"+
		"\u0016\u000b\u0000\u00a8\u00a9\u0005\u0005\u0000\u0000\u00a9\u00aa\u0003"+
		"\u0004\u0002\u0000\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a6\u0001"+
		"\u0000\u0000\u0000\u00ab\u00a7\u0001\u0000\u0000\u0000\u00ac\u0019\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0007\u0001\u0000\u0000\u00ae\u001b\u0001"+
		"\u0000\u0000\u0000\u00af\u00b0\u0005\u001e\u0000\u0000\u00b0\u001d\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b8\u0005\u0010\u0000\u0000\u00b2\u00b8\u0005"+
		"\u0011\u0000\u0000\u00b3\u00b8\u0005\u0019\u0000\u0000\u00b4\u00b8\u0005"+
		"&\u0000\u0000\u00b5\u00b8\u0003,\u0016\u0000\u00b6\u00b8\u0003.\u0017"+
		"\u0000\u00b7\u00b1\u0001\u0000\u0000\u0000\u00b7\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b3\u0001\u0000\u0000\u0000\u00b7\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b8\u001f\u0001\u0000\u0000\u0000\u00b9\u00be\u0003&\u0013\u0000"+
		"\u00ba\u00bb\u0005\u0007\u0000\u0000\u00bb\u00bd\u0003&\u0013\u0000\u00bc"+
		"\u00ba\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be"+
		"\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf"+
		"!\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1\u00c6"+
		"\u0003\u0018\f\u0000\u00c2\u00c3\u0005\u0007\u0000\u0000\u00c3\u00c5\u0003"+
		"\u0018\f\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c7#\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0005\t\u0000\u0000\u00ca\u00cb\u0003&\u0013\u0000"+
		"\u00cb\u00cc\u0005\n\u0000\u0000\u00cc\u00d2\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0005\u000b\u0000\u0000\u00ce\u00cf\u0003&\u0013\u0000\u00cf\u00d0"+
		"\u0005\f\u0000\u0000\u00d0\u00d2\u0001\u0000\u0000\u0000\u00d1\u00c9\u0001"+
		"\u0000\u0000\u0000\u00d1\u00cd\u0001\u0000\u0000\u0000\u00d2%\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0006\u0013\uffff\uffff\u0000\u00d4\u00e2\u0005"+
		"\'\u0000\u0000\u00d5\u00e2\u0003\u001e\u000f\u0000\u00d6\u00e2\u0003("+
		"\u0014\u0000\u00d7\u00e2\u0003*\u0015\u0000\u00d8\u00e2\u0003$\u0012\u0000"+
		"\u00d9\u00da\u0003\u001c\u000e\u0000\u00da\u00db\u0003&\u0013\u0007\u00db"+
		"\u00e2\u0001\u0000\u0000\u0000\u00dc\u00e2\u00032\u0019\u0000\u00dd\u00de"+
		"\u00034\u001a\u0000\u00de\u00df\u0005\b\u0000\u0000\u00df\u00e0\u0003"+
		"&\u0013\u0002\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00d3\u0001\u0000"+
		"\u0000\u0000\u00e1\u00d5\u0001\u0000\u0000\u0000\u00e1\u00d6\u0001\u0000"+
		"\u0000\u0000\u00e1\u00d7\u0001\u0000\u0000\u0000\u00e1\u00d8\u0001\u0000"+
		"\u0000\u0000\u00e1\u00d9\u0001\u0000\u0000\u0000\u00e1\u00dc\u0001\u0000"+
		"\u0000\u0000\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e2\u010a\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\n\u0006\u0000\u0000\u00e4\u00e5\u0007\u0002\u0000"+
		"\u0000\u00e5\u0109\u0003&\u0013\u0007\u00e6\u00e7\n\u0005\u0000\u0000"+
		"\u00e7\u00e8\u0007\u0003\u0000\u0000\u00e8\u0109\u0003&\u0013\u0006\u00e9"+
		"\u00ea\n\u0004\u0000\u0000\u00ea\u00eb\u0003\u001a\r\u0000\u00eb\u00ec"+
		"\u0003&\u0013\u0005\u00ec\u0109\u0001\u0000\u0000\u0000\u00ed\u00ee\n"+
		"\u0001\u0000\u0000\u00ee\u00ef\u0005\u0004\u0000\u0000\u00ef\u0109\u0003"+
		"&\u0013\u0002\u00f0\u00f1\n\u000b\u0000\u0000\u00f1\u00f3\u0005\t\u0000"+
		"\u0000\u00f2\u00f4\u0003 \u0010\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000"+
		"\u00f5\u0109\u0005\n\u0000\u0000\u00f6\u00f7\n\n\u0000\u0000\u00f7\u00f8"+
		"\u0005\u0001\u0000\u0000\u00f8\u00fa\u0005\t\u0000\u0000\u00f9\u00fb\u0003"+
		" \u0010\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u0109\u0005\n\u0000"+
		"\u0000\u00fd\u00fe\n\t\u0000\u0000\u00fe\u00ff\u0005\r\u0000\u0000\u00ff"+
		"\u0100\u0003\"\u0011\u0000\u0100\u0101\u0005\u000e\u0000\u0000\u0101\u0109"+
		"\u0001\u0000\u0000\u0000\u0102\u0103\n\b\u0000\u0000\u0103\u0104\u0005"+
		"\u0001\u0000\u0000\u0104\u0105\u0005\r\u0000\u0000\u0105\u0106\u0003\""+
		"\u0011\u0000\u0106\u0107\u0005\u000e\u0000\u0000\u0107\u0109\u0001\u0000"+
		"\u0000\u0000\u0108\u00e3\u0001\u0000\u0000\u0000\u0108\u00e6\u0001\u0000"+
		"\u0000\u0000\u0108\u00e9\u0001\u0000\u0000\u0000\u0108\u00ed\u0001\u0000"+
		"\u0000\u0000\u0108\u00f0\u0001\u0000\u0000\u0000\u0108\u00f6\u0001\u0000"+
		"\u0000\u0000\u0108\u00fd\u0001\u0000\u0000\u0000\u0108\u0102\u0001\u0000"+
		"\u0000\u0000\u0109\u010c\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000"+
		"\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\'\u0001\u0000\u0000"+
		"\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010d\u010e\u0005\u0016\u0000"+
		"\u0000\u010e\u010f\u0003&\u0013\u0000\u010f)\u0001\u0000\u0000\u0000\u0110"+
		"\u0111\u0005\u0002\u0000\u0000\u0111\u0112\u0003&\u0013\u0000\u0112+\u0001"+
		"\u0000\u0000\u0000\u0113\u0114\u0005\'\u0000\u0000\u0114\u0116\u0005\t"+
		"\u0000\u0000\u0115\u0117\u0003\f\u0006\u0000\u0116\u0115\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000"+
		"\u0000\u0118\u011b\u0005\n\u0000\u0000\u0119\u011a\u0005\u0003\u0000\u0000"+
		"\u011a\u011c\u0003\u0018\f\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011b"+
		"\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0005\u000f\u0000\u0000\u011e\u011f\u0005\u000b\u0000\u0000\u011f"+
		"\u0120\u0003&\u0013\u0000\u0120\u0121\u0005\f\u0000\u0000\u0121\u0131"+
		"\u0001\u0000\u0000\u0000\u0122\u0124\u0005\t\u0000\u0000\u0123\u0125\u0003"+
		"\f\u0006\u0000\u0124\u0123\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0129\u0005\n\u0000"+
		"\u0000\u0127\u0128\u0005\u0003\u0000\u0000\u0128\u012a\u0003\u0018\f\u0000"+
		"\u0129\u0127\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012c\u0005\u000f\u0000\u0000"+
		"\u012c\u012d\u0005\u000b\u0000\u0000\u012d\u012e\u0003&\u0013\u0000\u012e"+
		"\u012f\u0005\f\u0000\u0000\u012f\u0131\u0001\u0000\u0000\u0000\u0130\u0113"+
		"\u0001\u0000\u0000\u0000\u0130\u0122\u0001\u0000\u0000\u0000\u0131-\u0001"+
		"\u0000\u0000\u0000\u0132\u0133\u0005\'\u0000\u0000\u0133\u0134\u0005\r"+
		"\u0000\u0000\u0134\u0135\u0003\u0012\t\u0000\u0135\u0138\u0005\u000e\u0000"+
		"\u0000\u0136\u0137\u0005\u0003\u0000\u0000\u0137\u0139\u0003\u0018\f\u0000"+
		"\u0138\u0136\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000"+
		"\u0139\u013a\u0001\u0000\u0000\u0000\u013a\u013b\u0005\u000f\u0000\u0000"+
		"\u013b\u013c\u0005\u000b\u0000\u0000\u013c\u013d\u0003&\u0013\u0000\u013d"+
		"\u013e\u0005\f\u0000\u0000\u013e\u014c\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0005\r\u0000\u0000\u0140\u0141\u0003\u0012\t\u0000\u0141\u0144\u0005"+
		"\u000e\u0000\u0000\u0142\u0143\u0005\u0003\u0000\u0000\u0143\u0145\u0003"+
		"\u0018\f\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000"+
		"\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0147\u0005\u000f"+
		"\u0000\u0000\u0147\u0148\u0005\u000b\u0000\u0000\u0148\u0149\u0003&\u0013"+
		"\u0000\u0149\u014a\u0005\f\u0000\u0000\u014a\u014c\u0001\u0000\u0000\u0000"+
		"\u014b\u0132\u0001\u0000\u0000\u0000\u014b\u013f\u0001\u0000\u0000\u0000"+
		"\u014c/\u0001\u0000\u0000\u0000\u014d\u014e\u0007\u0004\u0000\u0000\u014e"+
		"1\u0001\u0000\u0000\u0000\u014f\u0150\u00030\u0018\u0000\u0150\u0151\u0005"+
		"\'\u0000\u0000\u0151\u0152\u0005\u001f\u0000\u0000\u0152\u0153\u0003&"+
		"\u0013\u0000\u0153\u0154\u0005\b\u0000\u0000\u0154\u0155\u0003&\u0013"+
		"\u0000\u0155\u015e\u0001\u0000\u0000\u0000\u0156\u0157\u00030\u0018\u0000"+
		"\u0157\u0158\u0003\u0006\u0003\u0000\u0158\u0159\u0005\u001f\u0000\u0000"+
		"\u0159\u015a\u0003&\u0013\u0000\u015a\u015b\u0005\b\u0000\u0000\u015b"+
		"\u015c\u0003&\u0013\u0000\u015c\u015e\u0001\u0000\u0000\u0000\u015d\u014f"+
		"\u0001\u0000\u0000\u0000\u015d\u0156\u0001\u0000\u0000\u0000\u015e3\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0005\u0015\u0000\u0000\u0160\u0161\u0005"+
		"\'\u0000\u0000\u0161\u0163\u0005\t\u0000\u0000\u0162\u0164\u0003\f\u0006"+
		"\u0000\u0163\u0162\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000"+
		"\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u0168\u0005\n\u0000\u0000"+
		"\u0166\u0167\u0005\u0003\u0000\u0000\u0167\u0169\u0003\u0018\f\u0000\u0168"+
		"\u0166\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169"+
		"\u016a\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u001f\u0000\u0000\u016b"+
		"\u017e\u0003&\u0013\u0000\u016c\u016d\u0005\u0015\u0000\u0000\u016d\u016e"+
		"\u0005\'\u0000\u0000\u016e\u016f\u0005\r\u0000\u0000\u016f\u0170\u0003"+
		"\u0012\t\u0000\u0170\u0171\u0005\u000e\u0000\u0000\u0171\u0173\u0005\t"+
		"\u0000\u0000\u0172\u0174\u0003\f\u0006\u0000\u0173\u0172\u0001\u0000\u0000"+
		"\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000"+
		"\u0000\u0175\u0178\u0005\n\u0000\u0000\u0176\u0177\u0005\u0003\u0000\u0000"+
		"\u0177\u0179\u0003\u0018\f\u0000\u0178\u0176\u0001\u0000\u0000\u0000\u0178"+
		"\u0179\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a"+
		"\u017b\u0005\u001f\u0000\u0000\u017b\u017c\u0003&\u0013\u0000\u017c\u017e"+
		"\u0001\u0000\u0000\u0000\u017d\u015f\u0001\u0000\u0000\u0000\u017d\u016c"+
		"\u0001\u0000\u0000\u0000\u017e5\u0001\u0000\u0000\u0000\u017f\u0181\u0003"+
		"&\u0013\u0000\u0180\u017f\u0001\u0000\u0000\u0000\u0181\u0184\u0001\u0000"+
		"\u0000\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000"+
		"\u0000\u0000\u0183\u0185\u0001\u0000\u0000\u0000\u0184\u0182\u0001\u0000"+
		"\u0000\u0000\u0185\u0186\u0005\u0000\u0000\u0001\u01867\u0001\u0000\u0000"+
		"\u0000&?FIQX`fmr}\u0084\u0096\u00a4\u00ab\u00b7\u00be\u00c6\u00d1\u00e1"+
		"\u00f3\u00fa\u0108\u010a\u0116\u011b\u0124\u0129\u0130\u0138\u0144\u014b"+
		"\u015d\u0163\u0168\u0173\u0178\u017d\u0182";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}