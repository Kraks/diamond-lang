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
		SUBTYPE=24, UNIT=25, LAM=26, TYLAM=27, IF=28, ELSE=29, AND=30, OR=31, 
		NOT=32, EQ=33, EQ2=34, NEQ=35, ADD=36, MINUS=37, MULT=38, DIV=39, INT=40, 
		ID=41, WS=42, COMMENT=43;
	public static final int
		RULE_qualElem = 0, RULE_qualElems = 1, RULE_qual = 2, RULE_idQty = 3, 
		RULE_param = 4, RULE_paramList = 5, RULE_namedParamList = 6, RULE_funTy = 7, 
		RULE_tyParam = 8, RULE_tyParamList = 9, RULE_tyFunTy = 10, RULE_ty = 11, 
		RULE_qty = 12, RULE_eff = 13, RULE_effs = 14, RULE_boolOp2 = 15, RULE_op1 = 16, 
		RULE_value = 17, RULE_args = 18, RULE_tyArgs = 19, RULE_wrapExpr = 20, 
		RULE_expr = 21, RULE_alloc = 22, RULE_deref = 23, RULE_lam = 24, RULE_tyLam = 25, 
		RULE_valDecl = 26, RULE_let = 27, RULE_funDef = 28, RULE_program = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualElem", "qualElems", "qual", "idQty", "param", "paramList", "namedParamList", 
			"funTy", "tyParam", "tyParamList", "tyFunTy", "ty", "qty", "eff", "effs", 
			"boolOp2", "op1", "value", "args", "tyArgs", "wrapExpr", "expr", "alloc", 
			"deref", "lam", "tyLam", "valDecl", "let", "funDef", "program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'!'", "':'", "':='", "'^'", null, "','", "';'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'=>'", "'true'", "'false'", "'val'", 
			"'topval'", "'in'", "'def'", "'Ref'", "'forall'", "'<:'", "'unit'", "'lam'", 
			"'Lam'", "'if'", "'else'", "'&&'", "'||'", "'~'", "'='", "'=='", "'!='", 
			"'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", 
			"TRUE", "FALSE", "VAL", "TOPVAL", "IN", "DEF", "REF", "FORALL", "SUBTYPE", 
			"UNIT", "LAM", "TYLAM", "IF", "ELSE", "AND", "OR", "NOT", "EQ", "EQ2", 
			"NEQ", "ADD", "MINUS", "MULT", "DIV", "INT", "ID", "WS", "COMMENT"
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
			setState(60);
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
			setState(62);
			qualElem();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(63);
				match(COMMA);
				setState(64);
				qualElem();
				}
				}
				setState(69);
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
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FRESH:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(FRESH);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				match(ID);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(LCURLY);
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FRESH || _la==ID) {
					{
					setState(73);
					qualElems();
					}
				}

				setState(76);
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
			setState(79);
			match(ID);
			setState(80);
			match(COLON);
			setState(81);
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
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
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
			setState(87);
			param();
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(88);
				match(COMMA);
				setState(89);
				param();
				}
				}
				setState(94);
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
			setState(95);
			idQty();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(96);
				match(COMMA);
				setState(97);
				idQty();
				}
				}
				setState(102);
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
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				match(ID);
				setState(104);
				match(LPAREN);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2199035838976L) != 0)) {
					{
					setState(105);
					paramList();
					}
				}

				setState(108);
				match(RPAREN);
				setState(109);
				match(RIGHTARROW);
				setState(110);
				qty();
				setState(112);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(111);
					effs();
					}
					break;
				}
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(LPAREN);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2199035838976L) != 0)) {
					{
					setState(115);
					paramList();
					}
				}

				setState(118);
				match(RPAREN);
				setState(119);
				match(RIGHTARROW);
				setState(120);
				qty();
				setState(122);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(121);
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
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(ID);
				setState(128);
				match(SUBTYPE);
				setState(129);
				ty();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				match(ID);
				setState(131);
				match(HAT);
				setState(132);
				match(ID);
				setState(133);
				match(SUBTYPE);
				setState(134);
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
			setState(137);
			tyParam();
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(138);
				match(COMMA);
				setState(139);
				tyParam();
				}
				}
				setState(144);
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
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(FORALL);
				setState(146);
				match(LBRACKET);
				setState(147);
				tyParamList();
				setState(148);
				match(RBRACKET);
				setState(149);
				match(RIGHTARROW);
				setState(150);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(FORALL);
				setState(153);
				match(ID);
				setState(154);
				match(LBRACKET);
				setState(155);
				tyParamList();
				setState(156);
				match(RBRACKET);
				setState(157);
				match(RIGHTARROW);
				setState(158);
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
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(REF);
				setState(164);
				match(LBRACKET);
				setState(165);
				qty();
				setState(166);
				match(RBRACKET);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				funTy();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(169);
				tyFunTy();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(170);
				match(LPAREN);
				setState(171);
				ty();
				setState(172);
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
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				ty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				ty();
				setState(178);
				match(HAT);
				setState(179);
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
			setState(183);
			match(AT);
			setState(184);
			match(ID);
			setState(185);
			match(LPAREN);
			setState(186);
			match(ID);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(187);
				match(COMMA);
				setState(188);
				match(ID);
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(194);
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
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				match(ID);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(197);
						eff();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(200); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
			setState(204);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 54760833024L) != 0)) ) {
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
			setState(206);
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
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(TRUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(FALSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				match(UNIT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(211);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(212);
				lam();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(213);
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
			setState(216);
			expr(0);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(217);
				match(COMMA);
				setState(218);
				expr(0);
				}
				}
				setState(223);
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
			setState(224);
			qty();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(225);
				match(COMMA);
				setState(226);
				qty();
				}
				}
				setState(231);
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
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				match(LPAREN);
				setState(233);
				expr(0);
				setState(234);
				match(RPAREN);
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				match(LCURLY);
				setState(237);
				expr(0);
				setState(238);
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
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(243);
				match(ID);
				}
				break;
			case 2:
				{
				setState(244);
				value();
				}
				break;
			case 3:
				{
				setState(245);
				alloc();
				}
				break;
			case 4:
				{
				setState(246);
				deref();
				}
				break;
			case 5:
				{
				setState(247);
				wrapExpr();
				}
				break;
			case 6:
				{
				setState(248);
				op1();
				setState(249);
				expr(8);
				}
				break;
			case 7:
				{
				setState(251);
				let();
				}
				break;
			case 8:
				{
				setState(252);
				funDef();
				setState(253);
				match(SEMI);
				setState(254);
				expr(3);
				}
				break;
			case 9:
				{
				setState(256);
				match(IF);
				setState(257);
				match(LPAREN);
				setState(258);
				expr(0);
				setState(259);
				match(RPAREN);
				setState(260);
				expr(0);
				setState(261);
				match(ELSE);
				setState(262);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(305);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(303);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(266);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(267);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(268);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(269);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(270);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(271);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(273);
						boolOp2();
						setState(274);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(277);
						match(COLONEQ);
						setState(278);
						expr(3);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(279);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(280);
						match(LPAREN);
						setState(282);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3303139125764L) != 0)) {
							{
							setState(281);
							args();
							}
						}

						setState(284);
						match(RPAREN);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(285);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(286);
						match(AT);
						setState(287);
						match(LPAREN);
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3303139125764L) != 0)) {
							{
							setState(288);
							args();
							}
						}

						setState(291);
						match(RPAREN);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(292);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(293);
						match(LBRACKET);
						setState(294);
						tyArgs();
						setState(295);
						match(RBRACKET);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(297);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(298);
						match(AT);
						setState(299);
						match(LBRACKET);
						setState(300);
						tyArgs();
						setState(301);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(307);
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
			setState(308);
			match(REF);
			setState(309);
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
			setState(311);
			match(EXCLA);
			setState(312);
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
		enterRule(_localctx, 48, RULE_lam);
		int _la;
		try {
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				match(ID);
				setState(315);
				match(LPAREN);
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(316);
					namedParamList();
					}
				}

				setState(319);
				match(RPAREN);
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(320);
					match(COLON);
					setState(321);
					qty();
					setState(323);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT || _la==ID) {
						{
						setState(322);
						effs();
						}
					}

					}
				}

				setState(327);
				match(RIGHTARROW);
				setState(328);
				match(LCURLY);
				setState(329);
				expr(0);
				setState(330);
				match(RCURLY);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(LPAREN);
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(333);
					namedParamList();
					}
				}

				setState(336);
				match(RPAREN);
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(337);
					match(COLON);
					setState(338);
					qty();
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT || _la==ID) {
						{
						setState(339);
						effs();
						}
					}

					}
				}

				setState(344);
				match(RIGHTARROW);
				setState(345);
				match(LCURLY);
				setState(346);
				expr(0);
				setState(347);
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
		enterRule(_localctx, 50, RULE_tyLam);
		int _la;
		try {
			setState(376);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				match(ID);
				setState(352);
				match(LBRACKET);
				setState(353);
				tyParamList();
				setState(354);
				match(RBRACKET);
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(355);
					match(COLON);
					setState(356);
					qty();
					}
				}

				setState(359);
				match(RIGHTARROW);
				setState(360);
				match(LCURLY);
				setState(361);
				expr(0);
				setState(362);
				match(RCURLY);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(364);
				match(LBRACKET);
				setState(365);
				tyParamList();
				setState(366);
				match(RBRACKET);
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(367);
					match(COLON);
					setState(368);
					qty();
					}
				}

				setState(371);
				match(RIGHTARROW);
				setState(372);
				match(LCURLY);
				setState(373);
				expr(0);
				setState(374);
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
		enterRule(_localctx, 52, RULE_valDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
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
		enterRule(_localctx, 54, RULE_let);
		try {
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				valDecl();
				setState(381);
				match(ID);
				setState(382);
				match(EQ);
				setState(383);
				expr(0);
				setState(384);
				match(SEMI);
				setState(385);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				valDecl();
				setState(388);
				idQty();
				setState(389);
				match(EQ);
				setState(390);
				expr(0);
				setState(391);
				match(SEMI);
				setState(392);
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
		enterRule(_localctx, 56, RULE_funDef);
		int _la;
		try {
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				_localctx = new MonoFunDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
				match(DEF);
				setState(397);
				match(ID);
				setState(398);
				match(LPAREN);
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(399);
					namedParamList();
					}
				}

				setState(402);
				match(RPAREN);
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(403);
					match(COLON);
					setState(404);
					qty();
					setState(406);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AT || _la==ID) {
						{
						setState(405);
						effs();
						}
					}

					}
				}

				setState(410);
				match(EQ);
				setState(411);
				expr(0);
				}
				break;
			case 2:
				_localctx = new PolyFunDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				match(DEF);
				setState(413);
				match(ID);
				setState(414);
				match(LBRACKET);
				setState(415);
				tyParamList();
				setState(416);
				match(RBRACKET);
				setState(417);
				match(LPAREN);
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(418);
					namedParamList();
					}
				}

				setState(421);
				match(RPAREN);
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(422);
					match(COLON);
					setState(423);
					qty();
					}
				}

				setState(426);
				match(EQ);
				setState(427);
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
		enterRule(_localctx, 58, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3303139125764L) != 0)) {
				{
				{
				setState(431);
				expr(0);
				}
				}
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(437);
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
		"\u0004\u0001+\u01b8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001B\b\u0001\n\u0001\f\u0001"+
		"E\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"K\b\u0002\u0001\u0002\u0003\u0002N\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003\u0004V\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005[\b\u0005\n\u0005\f\u0005^\t"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006c\b\u0006\n\u0006"+
		"\f\u0006f\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007k\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007q\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007u\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007{\b\u0007\u0003\u0007}\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0088\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u008d\b\t\n\t\f\t\u0090\t"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00a1\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00af\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00b6"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00be\b\r"+
		"\n\r\f\r\u00c1\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0004\u000e"+
		"\u00c7\b\u000e\u000b\u000e\f\u000e\u00c8\u0003\u000e\u00cb\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00d7\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00dc\b\u0012\n\u0012\f\u0012"+
		"\u00df\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00e4\b"+
		"\u0013\n\u0013\f\u0013\u00e7\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u00f1\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u0109\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u011b\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u0122\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u0130\b\u0015\n\u0015\f\u0015\u0133"+
		"\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u013e\b\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0144\b\u0018\u0003"+
		"\u0018\u0146\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u014f\b\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0155\b\u0018\u0003\u0018\u0157"+
		"\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u015e\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0166\b\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0172\b\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0179\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u018b\b\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0191\b\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0197\b\u001c\u0003\u001c\u0199"+
		"\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01a4\b\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01a9\b\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u01ae\b\u001c\u0001\u001d\u0005\u001d\u01b1"+
		"\b\u001d\n\u001d\f\u001d\u01b4\t\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0000\u0001*\u001e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:\u0000\u0005\u0002\u0000"+
		"\u0006\u0006))\u0002\u0000\u001e\u001f\"#\u0001\u0000&\'\u0001\u0000$"+
		"%\u0001\u0000\u0012\u0013\u01dd\u0000<\u0001\u0000\u0000\u0000\u0002>"+
		"\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000\u0000\u0006O\u0001\u0000"+
		"\u0000\u0000\bU\u0001\u0000\u0000\u0000\nW\u0001\u0000\u0000\u0000\f_"+
		"\u0001\u0000\u0000\u0000\u000e|\u0001\u0000\u0000\u0000\u0010\u0087\u0001"+
		"\u0000\u0000\u0000\u0012\u0089\u0001\u0000\u0000\u0000\u0014\u00a0\u0001"+
		"\u0000\u0000\u0000\u0016\u00ae\u0001\u0000\u0000\u0000\u0018\u00b5\u0001"+
		"\u0000\u0000\u0000\u001a\u00b7\u0001\u0000\u0000\u0000\u001c\u00ca\u0001"+
		"\u0000\u0000\u0000\u001e\u00cc\u0001\u0000\u0000\u0000 \u00ce\u0001\u0000"+
		"\u0000\u0000\"\u00d6\u0001\u0000\u0000\u0000$\u00d8\u0001\u0000\u0000"+
		"\u0000&\u00e0\u0001\u0000\u0000\u0000(\u00f0\u0001\u0000\u0000\u0000*"+
		"\u0108\u0001\u0000\u0000\u0000,\u0134\u0001\u0000\u0000\u0000.\u0137\u0001"+
		"\u0000\u0000\u00000\u015d\u0001\u0000\u0000\u00002\u0178\u0001\u0000\u0000"+
		"\u00004\u017a\u0001\u0000\u0000\u00006\u018a\u0001\u0000\u0000\u00008"+
		"\u01ad\u0001\u0000\u0000\u0000:\u01b2\u0001\u0000\u0000\u0000<=\u0007"+
		"\u0000\u0000\u0000=\u0001\u0001\u0000\u0000\u0000>C\u0003\u0000\u0000"+
		"\u0000?@\u0005\u0007\u0000\u0000@B\u0003\u0000\u0000\u0000A?\u0001\u0000"+
		"\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001"+
		"\u0000\u0000\u0000D\u0003\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000"+
		"\u0000FN\u0005\u0006\u0000\u0000GN\u0005)\u0000\u0000HJ\u0005\u000b\u0000"+
		"\u0000IK\u0003\u0002\u0001\u0000JI\u0001\u0000\u0000\u0000JK\u0001\u0000"+
		"\u0000\u0000KL\u0001\u0000\u0000\u0000LN\u0005\f\u0000\u0000MF\u0001\u0000"+
		"\u0000\u0000MG\u0001\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000N\u0005"+
		"\u0001\u0000\u0000\u0000OP\u0005)\u0000\u0000PQ\u0005\u0003\u0000\u0000"+
		"QR\u0003\u0018\f\u0000R\u0007\u0001\u0000\u0000\u0000SV\u0003\u0018\f"+
		"\u0000TV\u0003\u0006\u0003\u0000US\u0001\u0000\u0000\u0000UT\u0001\u0000"+
		"\u0000\u0000V\t\u0001\u0000\u0000\u0000W\\\u0003\b\u0004\u0000XY\u0005"+
		"\u0007\u0000\u0000Y[\u0003\b\u0004\u0000ZX\u0001\u0000\u0000\u0000[^\u0001"+
		"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000"+
		"]\u000b\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_d\u0003\u0006"+
		"\u0003\u0000`a\u0005\u0007\u0000\u0000ac\u0003\u0006\u0003\u0000b`\u0001"+
		"\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000e\r\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000"+
		"\u0000gh\u0005)\u0000\u0000hj\u0005\t\u0000\u0000ik\u0003\n\u0005\u0000"+
		"ji\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000"+
		"\u0000lm\u0005\n\u0000\u0000mn\u0005\u000f\u0000\u0000np\u0003\u0018\f"+
		"\u0000oq\u0003\u001c\u000e\u0000po\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000q}\u0001\u0000\u0000\u0000rt\u0005\t\u0000\u0000su\u0003\n"+
		"\u0005\u0000ts\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000vw\u0005\n\u0000\u0000wx\u0005\u000f\u0000\u0000xz\u0003"+
		"\u0018\f\u0000y{\u0003\u001c\u000e\u0000zy\u0001\u0000\u0000\u0000z{\u0001"+
		"\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000|g\u0001\u0000\u0000\u0000"+
		"|r\u0001\u0000\u0000\u0000}\u000f\u0001\u0000\u0000\u0000~\u0088\u0005"+
		")\u0000\u0000\u007f\u0080\u0005)\u0000\u0000\u0080\u0081\u0005\u0018\u0000"+
		"\u0000\u0081\u0088\u0003\u0016\u000b\u0000\u0082\u0083\u0005)\u0000\u0000"+
		"\u0083\u0084\u0005\u0005\u0000\u0000\u0084\u0085\u0005)\u0000\u0000\u0085"+
		"\u0086\u0005\u0018\u0000\u0000\u0086\u0088\u0003\u0018\f\u0000\u0087~"+
		"\u0001\u0000\u0000\u0000\u0087\u007f\u0001\u0000\u0000\u0000\u0087\u0082"+
		"\u0001\u0000\u0000\u0000\u0088\u0011\u0001\u0000\u0000\u0000\u0089\u008e"+
		"\u0003\u0010\b\u0000\u008a\u008b\u0005\u0007\u0000\u0000\u008b\u008d\u0003"+
		"\u0010\b\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000"+
		"\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000"+
		"\u0000\u0000\u008f\u0013\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0005\u0017\u0000\u0000\u0092\u0093\u0005\r\u0000"+
		"\u0000\u0093\u0094\u0003\u0012\t\u0000\u0094\u0095\u0005\u000e\u0000\u0000"+
		"\u0095\u0096\u0005\u000f\u0000\u0000\u0096\u0097\u0003\u0018\f\u0000\u0097"+
		"\u00a1\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0017\u0000\u0000\u0099"+
		"\u009a\u0005)\u0000\u0000\u009a\u009b\u0005\r\u0000\u0000\u009b\u009c"+
		"\u0003\u0012\t\u0000\u009c\u009d\u0005\u000e\u0000\u0000\u009d\u009e\u0005"+
		"\u000f\u0000\u0000\u009e\u009f\u0003\u0018\f\u0000\u009f\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a0\u0091\u0001\u0000\u0000\u0000\u00a0\u0098\u0001\u0000"+
		"\u0000\u0000\u00a1\u0015\u0001\u0000\u0000\u0000\u00a2\u00af\u0005)\u0000"+
		"\u0000\u00a3\u00a4\u0005\u0016\u0000\u0000\u00a4\u00a5\u0005\r\u0000\u0000"+
		"\u00a5\u00a6\u0003\u0018\f\u0000\u00a6\u00a7\u0005\u000e\u0000\u0000\u00a7"+
		"\u00af\u0001\u0000\u0000\u0000\u00a8\u00af\u0003\u000e\u0007\u0000\u00a9"+
		"\u00af\u0003\u0014\n\u0000\u00aa\u00ab\u0005\t\u0000\u0000\u00ab\u00ac"+
		"\u0003\u0016\u000b\u0000\u00ac\u00ad\u0005\n\u0000\u0000\u00ad\u00af\u0001"+
		"\u0000\u0000\u0000\u00ae\u00a2\u0001\u0000\u0000\u0000\u00ae\u00a3\u0001"+
		"\u0000\u0000\u0000\u00ae\u00a8\u0001\u0000\u0000\u0000\u00ae\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ae\u00aa\u0001\u0000\u0000\u0000\u00af\u0017\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b6\u0003\u0016\u000b\u0000\u00b1\u00b2\u0003"+
		"\u0016\u000b\u0000\u00b2\u00b3\u0005\u0005\u0000\u0000\u00b3\u00b4\u0003"+
		"\u0004\u0002\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b1\u0001\u0000\u0000\u0000\u00b6\u0019\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005\u0001\u0000\u0000\u00b8\u00b9\u0005"+
		")\u0000\u0000\u00b9\u00ba\u0005\t\u0000\u0000\u00ba\u00bf\u0005)\u0000"+
		"\u0000\u00bb\u00bc\u0005\u0007\u0000\u0000\u00bc\u00be\u0005)\u0000\u0000"+
		"\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c3\u0005\n\u0000\u0000\u00c3\u001b\u0001\u0000\u0000\u0000\u00c4"+
		"\u00cb\u0005)\u0000\u0000\u00c5\u00c7\u0003\u001a\r\u0000\u00c6\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00cb"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c4\u0001\u0000\u0000\u0000\u00ca\u00c6"+
		"\u0001\u0000\u0000\u0000\u00cb\u001d\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0007\u0001\u0000\u0000\u00cd\u001f\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0005 \u0000\u0000\u00cf!\u0001\u0000\u0000\u0000\u00d0\u00d7\u0005\u0010"+
		"\u0000\u0000\u00d1\u00d7\u0005\u0011\u0000\u0000\u00d2\u00d7\u0005\u0019"+
		"\u0000\u0000\u00d3\u00d7\u0005(\u0000\u0000\u00d4\u00d7\u00030\u0018\u0000"+
		"\u00d5\u00d7\u00032\u0019\u0000\u00d6\u00d0\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d6\u00d2\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d7#\u0001\u0000\u0000\u0000\u00d8\u00dd"+
		"\u0003*\u0015\u0000\u00d9\u00da\u0005\u0007\u0000\u0000\u00da\u00dc\u0003"+
		"*\u0015\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000"+
		"\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de%\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e5\u0003\u0018\f\u0000\u00e1\u00e2\u0005\u0007\u0000\u0000"+
		"\u00e2\u00e4\u0003\u0018\f\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5"+
		"\u00e6\u0001\u0000\u0000\u0000\u00e6\'\u0001\u0000\u0000\u0000\u00e7\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005\t\u0000\u0000\u00e9\u00ea\u0003"+
		"*\u0015\u0000\u00ea\u00eb\u0005\n\u0000\u0000\u00eb\u00f1\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ed\u0005\u000b\u0000\u0000\u00ed\u00ee\u0003*\u0015"+
		"\u0000\u00ee\u00ef\u0005\f\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f0\u00e8\u0001\u0000\u0000\u0000\u00f0\u00ec\u0001\u0000\u0000\u0000"+
		"\u00f1)\u0001\u0000\u0000\u0000\u00f2\u00f3\u0006\u0015\uffff\uffff\u0000"+
		"\u00f3\u0109\u0005)\u0000\u0000\u00f4\u0109\u0003\"\u0011\u0000\u00f5"+
		"\u0109\u0003,\u0016\u0000\u00f6\u0109\u0003.\u0017\u0000\u00f7\u0109\u0003"+
		"(\u0014\u0000\u00f8\u00f9\u0003 \u0010\u0000\u00f9\u00fa\u0003*\u0015"+
		"\b\u00fa\u0109\u0001\u0000\u0000\u0000\u00fb\u0109\u00036\u001b\u0000"+
		"\u00fc\u00fd\u00038\u001c\u0000\u00fd\u00fe\u0005\b\u0000\u0000\u00fe"+
		"\u00ff\u0003*\u0015\u0003\u00ff\u0109\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0005\u001c\u0000\u0000\u0101\u0102\u0005\t\u0000\u0000\u0102\u0103\u0003"+
		"*\u0015\u0000\u0103\u0104\u0005\n\u0000\u0000\u0104\u0105\u0003*\u0015"+
		"\u0000\u0105\u0106\u0005\u001d\u0000\u0000\u0106\u0107\u0003*\u0015\u0001"+
		"\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u00f2\u0001\u0000\u0000\u0000"+
		"\u0108\u00f4\u0001\u0000\u0000\u0000\u0108\u00f5\u0001\u0000\u0000\u0000"+
		"\u0108\u00f6\u0001\u0000\u0000\u0000\u0108\u00f7\u0001\u0000\u0000\u0000"+
		"\u0108\u00f8\u0001\u0000\u0000\u0000\u0108\u00fb\u0001\u0000\u0000\u0000"+
		"\u0108\u00fc\u0001\u0000\u0000\u0000\u0108\u0100\u0001\u0000\u0000\u0000"+
		"\u0109\u0131\u0001\u0000\u0000\u0000\u010a\u010b\n\u0007\u0000\u0000\u010b"+
		"\u010c\u0007\u0002\u0000\u0000\u010c\u0130\u0003*\u0015\b\u010d\u010e"+
		"\n\u0006\u0000\u0000\u010e\u010f\u0007\u0003\u0000\u0000\u010f\u0130\u0003"+
		"*\u0015\u0007\u0110\u0111\n\u0005\u0000\u0000\u0111\u0112\u0003\u001e"+
		"\u000f\u0000\u0112\u0113\u0003*\u0015\u0006\u0113\u0130\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\n\u0002\u0000\u0000\u0115\u0116\u0005\u0004\u0000\u0000"+
		"\u0116\u0130\u0003*\u0015\u0003\u0117\u0118\n\f\u0000\u0000\u0118\u011a"+
		"\u0005\t\u0000\u0000\u0119\u011b\u0003$\u0012\u0000\u011a\u0119\u0001"+
		"\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b\u011c\u0001"+
		"\u0000\u0000\u0000\u011c\u0130\u0005\n\u0000\u0000\u011d\u011e\n\u000b"+
		"\u0000\u0000\u011e\u011f\u0005\u0001\u0000\u0000\u011f\u0121\u0005\t\u0000"+
		"\u0000\u0120\u0122\u0003$\u0012\u0000\u0121\u0120\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000"+
		"\u0123\u0130\u0005\n\u0000\u0000\u0124\u0125\n\n\u0000\u0000\u0125\u0126"+
		"\u0005\r\u0000\u0000\u0126\u0127\u0003&\u0013\u0000\u0127\u0128\u0005"+
		"\u000e\u0000\u0000\u0128\u0130\u0001\u0000\u0000\u0000\u0129\u012a\n\t"+
		"\u0000\u0000\u012a\u012b\u0005\u0001\u0000\u0000\u012b\u012c\u0005\r\u0000"+
		"\u0000\u012c\u012d\u0003&\u0013\u0000\u012d\u012e\u0005\u000e\u0000\u0000"+
		"\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u010a\u0001\u0000\u0000\u0000"+
		"\u012f\u010d\u0001\u0000\u0000\u0000\u012f\u0110\u0001\u0000\u0000\u0000"+
		"\u012f\u0114\u0001\u0000\u0000\u0000\u012f\u0117\u0001\u0000\u0000\u0000"+
		"\u012f\u011d\u0001\u0000\u0000\u0000\u012f\u0124\u0001\u0000\u0000\u0000"+
		"\u012f\u0129\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000"+
		"\u0132+\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0005\u0016\u0000\u0000\u0135\u0136\u0003*\u0015\u0000\u0136-\u0001"+
		"\u0000\u0000\u0000\u0137\u0138\u0005\u0002\u0000\u0000\u0138\u0139\u0003"+
		"*\u0015\u0000\u0139/\u0001\u0000\u0000\u0000\u013a\u013b\u0005)\u0000"+
		"\u0000\u013b\u013d\u0005\t\u0000\u0000\u013c\u013e\u0003\f\u0006\u0000"+
		"\u013d\u013c\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000"+
		"\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0145\u0005\n\u0000\u0000\u0140"+
		"\u0141\u0005\u0003\u0000\u0000\u0141\u0143\u0003\u0018\f\u0000\u0142\u0144"+
		"\u0003\u001c\u000e\u0000\u0143\u0142\u0001\u0000\u0000\u0000\u0143\u0144"+
		"\u0001\u0000\u0000\u0000\u0144\u0146\u0001\u0000\u0000\u0000\u0145\u0140"+
		"\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0147"+
		"\u0001\u0000\u0000\u0000\u0147\u0148\u0005\u000f\u0000\u0000\u0148\u0149"+
		"\u0005\u000b\u0000\u0000\u0149\u014a\u0003*\u0015\u0000\u014a\u014b\u0005"+
		"\f\u0000\u0000\u014b\u015e\u0001\u0000\u0000\u0000\u014c\u014e\u0005\t"+
		"\u0000\u0000\u014d\u014f\u0003\f\u0006\u0000\u014e\u014d\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000"+
		"\u0000\u0150\u0156\u0005\n\u0000\u0000\u0151\u0152\u0005\u0003\u0000\u0000"+
		"\u0152\u0154\u0003\u0018\f\u0000\u0153\u0155\u0003\u001c\u000e\u0000\u0154"+
		"\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155"+
		"\u0157\u0001\u0000\u0000\u0000\u0156\u0151\u0001\u0000\u0000\u0000\u0156"+
		"\u0157\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0005\u000f\u0000\u0000\u0159\u015a\u0005\u000b\u0000\u0000\u015a"+
		"\u015b\u0003*\u0015\u0000\u015b\u015c\u0005\f\u0000\u0000\u015c\u015e"+
		"\u0001\u0000\u0000\u0000\u015d\u013a\u0001\u0000\u0000\u0000\u015d\u014c"+
		"\u0001\u0000\u0000\u0000\u015e1\u0001\u0000\u0000\u0000\u015f\u0160\u0005"+
		")\u0000\u0000\u0160\u0161\u0005\r\u0000\u0000\u0161\u0162\u0003\u0012"+
		"\t\u0000\u0162\u0165\u0005\u000e\u0000\u0000\u0163\u0164\u0005\u0003\u0000"+
		"\u0000\u0164\u0166\u0003\u0018\f\u0000\u0165\u0163\u0001\u0000\u0000\u0000"+
		"\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000"+
		"\u0167\u0168\u0005\u000f\u0000\u0000\u0168\u0169\u0005\u000b\u0000\u0000"+
		"\u0169\u016a\u0003*\u0015\u0000\u016a\u016b\u0005\f\u0000\u0000\u016b"+
		"\u0179\u0001\u0000\u0000\u0000\u016c\u016d\u0005\r\u0000\u0000\u016d\u016e"+
		"\u0003\u0012\t\u0000\u016e\u0171\u0005\u000e\u0000\u0000\u016f\u0170\u0005"+
		"\u0003\u0000\u0000\u0170\u0172\u0003\u0018\f\u0000\u0171\u016f\u0001\u0000"+
		"\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000"+
		"\u0000\u0000\u0173\u0174\u0005\u000f\u0000\u0000\u0174\u0175\u0005\u000b"+
		"\u0000\u0000\u0175\u0176\u0003*\u0015\u0000\u0176\u0177\u0005\f\u0000"+
		"\u0000\u0177\u0179\u0001\u0000\u0000\u0000\u0178\u015f\u0001\u0000\u0000"+
		"\u0000\u0178\u016c\u0001\u0000\u0000\u0000\u01793\u0001\u0000\u0000\u0000"+
		"\u017a\u017b\u0007\u0004\u0000\u0000\u017b5\u0001\u0000\u0000\u0000\u017c"+
		"\u017d\u00034\u001a\u0000\u017d\u017e\u0005)\u0000\u0000\u017e\u017f\u0005"+
		"!\u0000\u0000\u017f\u0180\u0003*\u0015\u0000\u0180\u0181\u0005\b\u0000"+
		"\u0000\u0181\u0182\u0003*\u0015\u0000\u0182\u018b\u0001\u0000\u0000\u0000"+
		"\u0183\u0184\u00034\u001a\u0000\u0184\u0185\u0003\u0006\u0003\u0000\u0185"+
		"\u0186\u0005!\u0000\u0000\u0186\u0187\u0003*\u0015\u0000\u0187\u0188\u0005"+
		"\b\u0000\u0000\u0188\u0189\u0003*\u0015\u0000\u0189\u018b\u0001\u0000"+
		"\u0000\u0000\u018a\u017c\u0001\u0000\u0000\u0000\u018a\u0183\u0001\u0000"+
		"\u0000\u0000\u018b7\u0001\u0000\u0000\u0000\u018c\u018d\u0005\u0015\u0000"+
		"\u0000\u018d\u018e\u0005)\u0000\u0000\u018e\u0190\u0005\t\u0000\u0000"+
		"\u018f\u0191\u0003\f\u0006\u0000\u0190\u018f\u0001\u0000\u0000\u0000\u0190"+
		"\u0191\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192"+
		"\u0198\u0005\n\u0000\u0000\u0193\u0194\u0005\u0003\u0000\u0000\u0194\u0196"+
		"\u0003\u0018\f\u0000\u0195\u0197\u0003\u001c\u000e\u0000\u0196\u0195\u0001"+
		"\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u0199\u0001"+
		"\u0000\u0000\u0000\u0198\u0193\u0001\u0000\u0000\u0000\u0198\u0199\u0001"+
		"\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0005"+
		"!\u0000\u0000\u019b\u01ae\u0003*\u0015\u0000\u019c\u019d\u0005\u0015\u0000"+
		"\u0000\u019d\u019e\u0005)\u0000\u0000\u019e\u019f\u0005\r\u0000\u0000"+
		"\u019f\u01a0\u0003\u0012\t\u0000\u01a0\u01a1\u0005\u000e\u0000\u0000\u01a1"+
		"\u01a3\u0005\t\u0000\u0000\u01a2\u01a4\u0003\f\u0006\u0000\u01a3\u01a2"+
		"\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01a5"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a8\u0005\n\u0000\u0000\u01a6\u01a7\u0005"+
		"\u0003\u0000\u0000\u01a7\u01a9\u0003\u0018\f\u0000\u01a8\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000"+
		"\u0000\u0000\u01aa\u01ab\u0005!\u0000\u0000\u01ab\u01ac\u0003*\u0015\u0000"+
		"\u01ac\u01ae\u0001\u0000\u0000\u0000\u01ad\u018c\u0001\u0000\u0000\u0000"+
		"\u01ad\u019c\u0001\u0000\u0000\u0000\u01ae9\u0001\u0000\u0000\u0000\u01af"+
		"\u01b1\u0003*\u0015\u0000\u01b0\u01af\u0001\u0000\u0000\u0000\u01b1\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000\u01b2\u01b3"+
		"\u0001\u0000\u0000\u0000\u01b3\u01b5\u0001\u0000\u0000\u0000\u01b4\u01b2"+
		"\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005\u0000\u0000\u0001\u01b6;\u0001"+
		"\u0000\u0000\u0000.CJMU\\djptz|\u0087\u008e\u00a0\u00ae\u00b5\u00bf\u00c8"+
		"\u00ca\u00d6\u00dd\u00e5\u00f0\u0108\u011a\u0121\u012f\u0131\u013d\u0143"+
		"\u0145\u014e\u0154\u0156\u015d\u0165\u0171\u0178\u018a\u0190\u0196\u0198"+
		"\u01a3\u01a8\u01ad\u01b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}