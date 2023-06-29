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
		TRUE=16, FALSE=17, VAL=18, IN=19, DEF=20, REF=21, FORALL=22, SUBTYPE=23, 
		UNIT=24, LAM=25, TYLAM=26, AND=27, OR=28, NOT=29, EQ=30, EQ2=31, NEQ=32, 
		ADD=33, MINUS=34, MULT=35, DIV=36, INT=37, ID=38, WS=39, COMMENT=40;
	public static final int
		RULE_qualElem = 0, RULE_qualElems = 1, RULE_qual = 2, RULE_idQty = 3, 
		RULE_param = 4, RULE_paramList = 5, RULE_namedParamList = 6, RULE_funTy = 7, 
		RULE_tyParam = 8, RULE_tyParamList = 9, RULE_tyFunTy = 10, RULE_ty = 11, 
		RULE_qty = 12, RULE_op2 = 13, RULE_op1 = 14, RULE_value = 15, RULE_args = 16, 
		RULE_tyArgs = 17, RULE_expr = 18, RULE_alloc = 19, RULE_deref = 20, RULE_lam = 21, 
		RULE_tyLam = 22, RULE_let = 23, RULE_funDef = 24, RULE_funDefOrExpr = 25, 
		RULE_program = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualElem", "qualElems", "qual", "idQty", "param", "paramList", "namedParamList", 
			"funTy", "tyParam", "tyParamList", "tyFunTy", "ty", "qty", "op2", "op1", 
			"value", "args", "tyArgs", "expr", "alloc", "deref", "lam", "tyLam", 
			"let", "funDef", "funDefOrExpr", "program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'!'", "':'", "':='", "'^'", null, "','", "';'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'=>'", "'true'", "'false'", "'val'", 
			"'in'", "'def'", "'Ref'", "'forall'", "'<:'", "'unit'", "'lam'", "'Lam'", 
			"'&&'", "'||'", "'~'", "'='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", 
			"TRUE", "FALSE", "VAL", "IN", "DEF", "REF", "FORALL", "SUBTYPE", "UNIT", 
			"LAM", "TYLAM", "AND", "OR", "NOT", "EQ", "EQ2", "NEQ", "ADD", "MINUS", 
			"MULT", "DIV", "INT", "ID", "WS", "COMMENT"
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
			setState(54);
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
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public List<QualElemContext> qualElem() {
			return getRuleContexts(QualElemContext.class);
		}
		public QualElemContext qualElem(int i) {
			return getRuleContext(QualElemContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
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
			setState(56);
			match(LCURLY);
			setState(57);
			qualElem();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(58);
				match(COMMA);
				setState(59);
				qualElem();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(RCURLY);
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
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FRESH:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				match(FRESH);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(ID);
				}
				break;
			case HAT:
			case COMMA:
			case RPAREN:
			case LCURLY:
			case RBRACKET:
			case EQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(69);
					qualElems();
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
			setState(74);
			match(ID);
			setState(75);
			match(COLON);
			setState(76);
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
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
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
			setState(82);
			param();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(83);
				match(COMMA);
				setState(84);
				param();
				}
				}
				setState(89);
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
			setState(90);
			idQty();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(91);
				match(COMMA);
				setState(92);
				idQty();
				}
				}
				setState(97);
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
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(ID);
				setState(99);
				match(LPAREN);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274884198912L) != 0)) {
					{
					setState(100);
					paramList();
					}
				}

				setState(103);
				match(RPAREN);
				setState(104);
				match(RIGHTARROW);
				setState(105);
				qty();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				match(LPAREN);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274884198912L) != 0)) {
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
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				match(ID);
				setState(117);
				match(SUBTYPE);
				setState(118);
				ty();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				match(ID);
				setState(120);
				match(HAT);
				setState(121);
				match(ID);
				setState(122);
				match(SUBTYPE);
				setState(123);
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
			setState(126);
			tyParam();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(127);
				match(COMMA);
				setState(128);
				tyParam();
				}
				}
				setState(133);
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
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				match(FORALL);
				setState(135);
				match(LBRACKET);
				setState(136);
				tyParamList();
				setState(137);
				match(RBRACKET);
				setState(138);
				match(RIGHTARROW);
				setState(139);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(FORALL);
				setState(142);
				match(ID);
				setState(143);
				match(LBRACKET);
				setState(144);
				tyParamList();
				setState(145);
				match(RBRACKET);
				setState(146);
				match(RIGHTARROW);
				setState(147);
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
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public FunTyContext funTy() {
			return getRuleContext(FunTyContext.class,0);
		}
		public TyFunTyContext tyFunTy() {
			return getRuleContext(TyFunTyContext.class,0);
		}
		public TyContext ty() {
			return getRuleContext(TyContext.class,0);
		}
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
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(REF);
				setState(153);
				match(LPAREN);
				setState(154);
				qty();
				setState(155);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				funTy();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				tyFunTy();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(159);
				match(LPAREN);
				setState(160);
				ty();
				setState(161);
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
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				ty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				ty();
				setState(167);
				match(HAT);
				setState(168);
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
	public static class Op2Context extends ParserRuleContext {
		public TerminalNode AND() { return getToken(DiamondParser.AND, 0); }
		public TerminalNode OR() { return getToken(DiamondParser.OR, 0); }
		public TerminalNode EQ2() { return getToken(DiamondParser.EQ2, 0); }
		public TerminalNode NEQ() { return getToken(DiamondParser.NEQ, 0); }
		public TerminalNode ADD() { return getToken(DiamondParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(DiamondParser.MINUS, 0); }
		public TerminalNode MULT() { return getToken(DiamondParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(DiamondParser.DIV, 0); }
		public Op2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterOp2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitOp2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitOp2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op2Context op2() throws RecognitionException {
		Op2Context _localctx = new Op2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_op2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 135694123008L) != 0)) ) {
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
			setState(174);
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
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(FALSE);
				}
				break;
			case UNIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(UNIT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(179);
				match(INT);
				}
				break;
			case LAM:
				enterOuterAlt(_localctx, 5);
				{
				setState(180);
				lam();
				}
				break;
			case TYLAM:
				enterOuterAlt(_localctx, 6);
				{
				setState(181);
				tyLam();
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
			setState(184);
			expr(0);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(185);
				match(COMMA);
				setState(186);
				expr(0);
				}
				}
				setState(191);
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
			setState(192);
			qty();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(193);
				match(COMMA);
				setState(194);
				qty();
				}
				}
				setState(199);
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
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		public AllocContext alloc() {
			return getRuleContext(AllocContext.class,0);
		}
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public FunDefContext funDef() {
			return getRuleContext(FunDefContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DiamondParser.SEMI, 0); }
		public Op2Context op2() {
			return getRuleContext(Op2Context.class,0);
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
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(201);
				match(ID);
				}
				break;
			case TRUE:
			case FALSE:
			case UNIT:
			case LAM:
			case TYLAM:
			case INT:
				{
				setState(202);
				value();
				}
				break;
			case NOT:
				{
				setState(203);
				op1();
				setState(204);
				expr(13);
				}
				break;
			case REF:
				{
				setState(206);
				alloc();
				}
				break;
			case EXCLA:
				{
				setState(207);
				deref();
				}
				break;
			case LPAREN:
				{
				setState(208);
				match(LPAREN);
				setState(209);
				expr(0);
				setState(210);
				match(RPAREN);
				}
				break;
			case LCURLY:
				{
				setState(212);
				match(LCURLY);
				setState(213);
				expr(0);
				setState(214);
				match(RCURLY);
				}
				break;
			case VAL:
				{
				setState(216);
				let();
				}
				break;
			case DEF:
				{
				setState(217);
				funDef();
				setState(218);
				match(SEMI);
				setState(219);
				expr(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(256);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(254);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(223);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(224);
						op2();
						setState(225);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(227);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(228);
						match(COLONEQ);
						setState(229);
						expr(2);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(230);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(231);
						match(LPAREN);
						setState(233);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 412974778884L) != 0)) {
							{
							setState(232);
							args();
							}
						}

						setState(235);
						match(RPAREN);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(236);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(237);
						match(AT);
						setState(238);
						match(LPAREN);
						setState(240);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 412974778884L) != 0)) {
							{
							setState(239);
							args();
							}
						}

						setState(242);
						match(RPAREN);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(244);
						match(LBRACKET);
						setState(245);
						tyArgs();
						setState(246);
						match(RBRACKET);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(248);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(249);
						match(AT);
						setState(250);
						match(LBRACKET);
						setState(251);
						tyArgs();
						setState(252);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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
		enterRule(_localctx, 38, RULE_alloc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(REF);
			setState(260);
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
		enterRule(_localctx, 40, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(EXCLA);
			setState(263);
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
		public TerminalNode LAM() { return getToken(DiamondParser.LAM, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
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
		enterRule(_localctx, 42, RULE_lam);
		int _la;
		try {
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				match(LAM);
				setState(266);
				match(ID);
				setState(267);
				match(LPAREN);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(268);
					namedParamList();
					}
				}

				setState(271);
				match(RPAREN);
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(272);
					match(COLON);
					setState(273);
					qty();
					}
				}

				setState(276);
				match(LCURLY);
				setState(277);
				expr(0);
				setState(278);
				match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				match(LAM);
				setState(281);
				match(LPAREN);
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(282);
					namedParamList();
					}
				}

				setState(285);
				match(RPAREN);
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(286);
					match(COLON);
					setState(287);
					qty();
					}
				}

				setState(290);
				match(LCURLY);
				setState(291);
				expr(0);
				setState(292);
				match(RCURLY);
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
	public static class TyLamContext extends ParserRuleContext {
		public TerminalNode TYLAM() { return getToken(DiamondParser.TYLAM, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyParamListContext tyParamList() {
			return getRuleContext(TyParamListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
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
		enterRule(_localctx, 44, RULE_tyLam);
		int _la;
		try {
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				match(TYLAM);
				setState(297);
				match(ID);
				setState(298);
				match(LBRACKET);
				setState(299);
				tyParamList();
				setState(300);
				match(RBRACKET);
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(301);
					match(COLON);
					setState(302);
					qty();
					}
				}

				setState(305);
				match(LCURLY);
				setState(306);
				expr(0);
				setState(307);
				match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				match(TYLAM);
				setState(310);
				match(LBRACKET);
				setState(311);
				tyParamList();
				setState(312);
				match(RBRACKET);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(313);
					match(COLON);
					setState(314);
					qty();
					}
				}

				setState(317);
				match(LCURLY);
				setState(318);
				expr(0);
				setState(319);
				match(RCURLY);
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
	public static class LetContext extends ParserRuleContext {
		public TerminalNode VAL() { return getToken(DiamondParser.VAL, 0); }
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
		enterRule(_localctx, 46, RULE_let);
		try {
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				match(VAL);
				setState(324);
				match(ID);
				setState(325);
				match(EQ);
				setState(326);
				expr(0);
				setState(327);
				match(SEMI);
				setState(328);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				match(VAL);
				setState(331);
				idQty();
				setState(332);
				match(EQ);
				setState(333);
				expr(0);
				setState(334);
				match(SEMI);
				setState(335);
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
		enterRule(_localctx, 48, RULE_funDef);
		int _la;
		try {
			setState(369);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new MonoFunDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				match(DEF);
				setState(340);
				match(ID);
				setState(341);
				match(LPAREN);
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(342);
					namedParamList();
					}
				}

				setState(345);
				match(RPAREN);
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(346);
					match(COLON);
					setState(347);
					qty();
					}
				}

				setState(350);
				match(EQ);
				setState(351);
				expr(0);
				}
				break;
			case 2:
				_localctx = new PolyFunDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(352);
				match(DEF);
				setState(353);
				match(ID);
				setState(354);
				match(LBRACKET);
				setState(355);
				tyParamList();
				setState(356);
				match(RBRACKET);
				setState(357);
				match(LPAREN);
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(358);
					namedParamList();
					}
				}

				setState(361);
				match(RPAREN);
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(362);
					match(COLON);
					setState(363);
					qty();
					}
				}

				setState(366);
				match(EQ);
				setState(367);
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
	public static class FunDefOrExprContext extends ParserRuleContext {
		public FunDefContext funDef() {
			return getRuleContext(FunDefContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunDefOrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDefOrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterFunDefOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitFunDefOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitFunDefOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDefOrExprContext funDefOrExpr() throws RecognitionException {
		FunDefOrExprContext _localctx = new FunDefOrExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_funDefOrExpr);
		try {
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				funDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
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
		public List<FunDefOrExprContext> funDefOrExpr() {
			return getRuleContexts(FunDefOrExprContext.class);
		}
		public FunDefOrExprContext funDefOrExpr(int i) {
			return getRuleContext(FunDefOrExprContext.class,i);
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
		enterRule(_localctx, 52, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 412974778884L) != 0)) {
				{
				{
				setState(375);
				funDefOrExpr();
				}
				}
				setState(380);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(381);
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
		case 18:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 1);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001(\u0180\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001=\b\u0001"+
		"\n\u0001\f\u0001@\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002G\b\u0002\u0003\u0002I\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003\u0004Q\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005V\b\u0005\n\u0005"+
		"\f\u0005Y\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006^\b\u0006"+
		"\n\u0006\f\u0006a\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"f\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007m\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"r\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b}\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u0082\b\t\n\t"+
		"\f\t\u0085\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u0096\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00a4\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0003\f\u00ab\b\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00b7\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00bc"+
		"\b\u0010\n\u0010\f\u0010\u00bf\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u00c4\b\u0011\n\u0011\f\u0011\u00c7\t\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u00de\b\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u00ea\b\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00f1\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00ff"+
		"\b\u0012\n\u0012\f\u0012\u0102\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u010e\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u0113\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u011c\b\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0121\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0127\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0130\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u013c\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0142\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0152\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0158\b\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u015d\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u0168\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u016d\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018"+
		"\u0172\b\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u0176\b\u0019\u0001"+
		"\u001a\u0005\u001a\u0179\b\u001a\n\u001a\f\u001a\u017c\t\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0000\u0001$\u001b\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"4\u0000\u0002\u0002\u0000\u0006\u0006&&\u0002\u0000\u001b\u001c\u001f"+
		"$\u019e\u00006\u0001\u0000\u0000\u0000\u00028\u0001\u0000\u0000\u0000"+
		"\u0004H\u0001\u0000\u0000\u0000\u0006J\u0001\u0000\u0000\u0000\bP\u0001"+
		"\u0000\u0000\u0000\nR\u0001\u0000\u0000\u0000\fZ\u0001\u0000\u0000\u0000"+
		"\u000eq\u0001\u0000\u0000\u0000\u0010|\u0001\u0000\u0000\u0000\u0012~"+
		"\u0001\u0000\u0000\u0000\u0014\u0095\u0001\u0000\u0000\u0000\u0016\u00a3"+
		"\u0001\u0000\u0000\u0000\u0018\u00aa\u0001\u0000\u0000\u0000\u001a\u00ac"+
		"\u0001\u0000\u0000\u0000\u001c\u00ae\u0001\u0000\u0000\u0000\u001e\u00b6"+
		"\u0001\u0000\u0000\u0000 \u00b8\u0001\u0000\u0000\u0000\"\u00c0\u0001"+
		"\u0000\u0000\u0000$\u00dd\u0001\u0000\u0000\u0000&\u0103\u0001\u0000\u0000"+
		"\u0000(\u0106\u0001\u0000\u0000\u0000*\u0126\u0001\u0000\u0000\u0000,"+
		"\u0141\u0001\u0000\u0000\u0000.\u0151\u0001\u0000\u0000\u00000\u0171\u0001"+
		"\u0000\u0000\u00002\u0175\u0001\u0000\u0000\u00004\u017a\u0001\u0000\u0000"+
		"\u000067\u0007\u0000\u0000\u00007\u0001\u0001\u0000\u0000\u000089\u0005"+
		"\u000b\u0000\u00009>\u0003\u0000\u0000\u0000:;\u0005\u0007\u0000\u0000"+
		";=\u0003\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000AB\u0005\f\u0000\u0000B\u0003\u0001"+
		"\u0000\u0000\u0000CI\u0005\u0006\u0000\u0000DI\u0005&\u0000\u0000EG\u0003"+
		"\u0002\u0001\u0000FE\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000"+
		"GI\u0001\u0000\u0000\u0000HC\u0001\u0000\u0000\u0000HD\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000I\u0005\u0001\u0000\u0000\u0000JK\u0005"+
		"&\u0000\u0000KL\u0005\u0003\u0000\u0000LM\u0003\u0018\f\u0000M\u0007\u0001"+
		"\u0000\u0000\u0000NQ\u0003\u0018\f\u0000OQ\u0003\u0006\u0003\u0000PN\u0001"+
		"\u0000\u0000\u0000PO\u0001\u0000\u0000\u0000Q\t\u0001\u0000\u0000\u0000"+
		"RW\u0003\b\u0004\u0000ST\u0005\u0007\u0000\u0000TV\u0003\b\u0004\u0000"+
		"US\u0001\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000"+
		"\u0000WX\u0001\u0000\u0000\u0000X\u000b\u0001\u0000\u0000\u0000YW\u0001"+
		"\u0000\u0000\u0000Z_\u0003\u0006\u0003\u0000[\\\u0005\u0007\u0000\u0000"+
		"\\^\u0003\u0006\u0003\u0000][\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000"+
		"\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\r\u0001\u0000"+
		"\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0005&\u0000\u0000ce\u0005\t"+
		"\u0000\u0000df\u0003\n\u0005\u0000ed\u0001\u0000\u0000\u0000ef\u0001\u0000"+
		"\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005\n\u0000\u0000hi\u0005\u000f"+
		"\u0000\u0000ir\u0003\u0018\f\u0000jl\u0005\t\u0000\u0000km\u0003\n\u0005"+
		"\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000no\u0005\n\u0000\u0000op\u0005\u000f\u0000\u0000pr\u0003\u0018"+
		"\f\u0000qb\u0001\u0000\u0000\u0000qj\u0001\u0000\u0000\u0000r\u000f\u0001"+
		"\u0000\u0000\u0000s}\u0005&\u0000\u0000tu\u0005&\u0000\u0000uv\u0005\u0017"+
		"\u0000\u0000v}\u0003\u0016\u000b\u0000wx\u0005&\u0000\u0000xy\u0005\u0005"+
		"\u0000\u0000yz\u0005&\u0000\u0000z{\u0005\u0017\u0000\u0000{}\u0003\u0018"+
		"\f\u0000|s\u0001\u0000\u0000\u0000|t\u0001\u0000\u0000\u0000|w\u0001\u0000"+
		"\u0000\u0000}\u0011\u0001\u0000\u0000\u0000~\u0083\u0003\u0010\b\u0000"+
		"\u007f\u0080\u0005\u0007\u0000\u0000\u0080\u0082\u0003\u0010\b\u0000\u0081"+
		"\u007f\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084"+
		"\u0013\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\u0016\u0000\u0000\u0087\u0088\u0005\r\u0000\u0000\u0088\u0089"+
		"\u0003\u0012\t\u0000\u0089\u008a\u0005\u000e\u0000\u0000\u008a\u008b\u0005"+
		"\u000f\u0000\u0000\u008b\u008c\u0003\u0018\f\u0000\u008c\u0096\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0005\u0016\u0000\u0000\u008e\u008f\u0005&\u0000"+
		"\u0000\u008f\u0090\u0005\r\u0000\u0000\u0090\u0091\u0003\u0012\t\u0000"+
		"\u0091\u0092\u0005\u000e\u0000\u0000\u0092\u0093\u0005\u000f\u0000\u0000"+
		"\u0093\u0094\u0003\u0018\f\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095"+
		"\u0086\u0001\u0000\u0000\u0000\u0095\u008d\u0001\u0000\u0000\u0000\u0096"+
		"\u0015\u0001\u0000\u0000\u0000\u0097\u00a4\u0005&\u0000\u0000\u0098\u0099"+
		"\u0005\u0015\u0000\u0000\u0099\u009a\u0005\t\u0000\u0000\u009a\u009b\u0003"+
		"\u0018\f\u0000\u009b\u009c\u0005\n\u0000\u0000\u009c\u00a4\u0001\u0000"+
		"\u0000\u0000\u009d\u00a4\u0003\u000e\u0007\u0000\u009e\u00a4\u0003\u0014"+
		"\n\u0000\u009f\u00a0\u0005\t\u0000\u0000\u00a0\u00a1\u0003\u0016\u000b"+
		"\u0000\u00a1\u00a2\u0005\n\u0000\u0000\u00a2\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a3\u0097\u0001\u0000\u0000\u0000\u00a3\u0098\u0001\u0000\u0000\u0000"+
		"\u00a3\u009d\u0001\u0000\u0000\u0000\u00a3\u009e\u0001\u0000\u0000\u0000"+
		"\u00a3\u009f\u0001\u0000\u0000\u0000\u00a4\u0017\u0001\u0000\u0000\u0000"+
		"\u00a5\u00ab\u0003\u0016\u000b\u0000\u00a6\u00a7\u0003\u0016\u000b\u0000"+
		"\u00a7\u00a8\u0005\u0005\u0000\u0000\u00a8\u00a9\u0003\u0004\u0002\u0000"+
		"\u00a9\u00ab\u0001\u0000\u0000\u0000\u00aa\u00a5\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a6\u0001\u0000\u0000\u0000\u00ab\u0019\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ad\u0007\u0001\u0000\u0000\u00ad\u001b\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0005\u001d\u0000\u0000\u00af\u001d\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b7\u0005\u0010\u0000\u0000\u00b1\u00b7\u0005\u0011\u0000\u0000"+
		"\u00b2\u00b7\u0005\u0018\u0000\u0000\u00b3\u00b7\u0005%\u0000\u0000\u00b4"+
		"\u00b7\u0003*\u0015\u0000\u00b5\u00b7\u0003,\u0016\u0000\u00b6\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b1\u0001\u0000\u0000\u0000\u00b6\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7\u001f\u0001"+
		"\u0000\u0000\u0000\u00b8\u00bd\u0003$\u0012\u0000\u00b9\u00ba\u0005\u0007"+
		"\u0000\u0000\u00ba\u00bc\u0003$\u0012\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be!\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c5\u0003\u0018\f\u0000\u00c1"+
		"\u00c2\u0005\u0007\u0000\u0000\u00c2\u00c4\u0003\u0018\f\u0000\u00c3\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000\u00c5\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6#\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00c9\u0006"+
		"\u0012\uffff\uffff\u0000\u00c9\u00de\u0005&\u0000\u0000\u00ca\u00de\u0003"+
		"\u001e\u000f\u0000\u00cb\u00cc\u0003\u001c\u000e\u0000\u00cc\u00cd\u0003"+
		"$\u0012\r\u00cd\u00de\u0001\u0000\u0000\u0000\u00ce\u00de\u0003&\u0013"+
		"\u0000\u00cf\u00de\u0003(\u0014\u0000\u00d0\u00d1\u0005\t\u0000\u0000"+
		"\u00d1\u00d2\u0003$\u0012\u0000\u00d2\u00d3\u0005\n\u0000\u0000\u00d3"+
		"\u00de\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005\u000b\u0000\u0000\u00d5"+
		"\u00d6\u0003$\u0012\u0000\u00d6\u00d7\u0005\f\u0000\u0000\u00d7\u00de"+
		"\u0001\u0000\u0000\u0000\u00d8\u00de\u0003.\u0017\u0000\u00d9\u00da\u0003"+
		"0\u0018\u0000\u00da\u00db\u0005\b\u0000\u0000\u00db\u00dc\u0003$\u0012"+
		"\u0002\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00c8\u0001\u0000\u0000"+
		"\u0000\u00dd\u00ca\u0001\u0000\u0000\u0000\u00dd\u00cb\u0001\u0000\u0000"+
		"\u0000\u00dd\u00ce\u0001\u0000\u0000\u0000\u00dd\u00cf\u0001\u0000\u0000"+
		"\u0000\u00dd\u00d0\u0001\u0000\u0000\u0000\u00dd\u00d4\u0001\u0000\u0000"+
		"\u0000\u00dd\u00d8\u0001\u0000\u0000\u0000\u00dd\u00d9\u0001\u0000\u0000"+
		"\u0000\u00de\u0100\u0001\u0000\u0000\u0000\u00df\u00e0\n\f\u0000\u0000"+
		"\u00e0\u00e1\u0003\u001a\r\u0000\u00e1\u00e2\u0003$\u0012\r\u00e2\u00ff"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e4\n\u0001\u0000\u0000\u00e4\u00e5\u0005"+
		"\u0004\u0000\u0000\u00e5\u00ff\u0003$\u0012\u0002\u00e6\u00e7\n\u0007"+
		"\u0000\u0000\u00e7\u00e9\u0005\t\u0000\u0000\u00e8\u00ea\u0003 \u0010"+
		"\u0000\u00e9\u00e8\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb\u00ff\u0005\n\u0000\u0000"+
		"\u00ec\u00ed\n\u0006\u0000\u0000\u00ed\u00ee\u0005\u0001\u0000\u0000\u00ee"+
		"\u00f0\u0005\t\u0000\u0000\u00ef\u00f1\u0003 \u0010\u0000\u00f0\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f2\u00ff\u0005\n\u0000\u0000\u00f3\u00f4\n"+
		"\u0005\u0000\u0000\u00f4\u00f5\u0005\r\u0000\u0000\u00f5\u00f6\u0003\""+
		"\u0011\u0000\u00f6\u00f7\u0005\u000e\u0000\u0000\u00f7\u00ff\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\n\u0004\u0000\u0000\u00f9\u00fa\u0005\u0001\u0000"+
		"\u0000\u00fa\u00fb\u0005\r\u0000\u0000\u00fb\u00fc\u0003\"\u0011\u0000"+
		"\u00fc\u00fd\u0005\u000e\u0000\u0000\u00fd\u00ff\u0001\u0000\u0000\u0000"+
		"\u00fe\u00df\u0001\u0000\u0000\u0000\u00fe\u00e3\u0001\u0000\u0000\u0000"+
		"\u00fe\u00e6\u0001\u0000\u0000\u0000\u00fe\u00ec\u0001\u0000\u0000\u0000"+
		"\u00fe\u00f3\u0001\u0000\u0000\u0000\u00fe\u00f8\u0001\u0000\u0000\u0000"+
		"\u00ff\u0102\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\u0001\u0000\u0000\u0000\u0101%\u0001\u0000\u0000\u0000\u0102"+
		"\u0100\u0001\u0000\u0000\u0000\u0103\u0104\u0005\u0015\u0000\u0000\u0104"+
		"\u0105\u0003$\u0012\u0000\u0105\'\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0005\u0002\u0000\u0000\u0107\u0108\u0003$\u0012\u0000\u0108)\u0001\u0000"+
		"\u0000\u0000\u0109\u010a\u0005\u0019\u0000\u0000\u010a\u010b\u0005&\u0000"+
		"\u0000\u010b\u010d\u0005\t\u0000\u0000\u010c\u010e\u0003\f\u0006\u0000"+
		"\u010d\u010c\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0112\u0005\n\u0000\u0000\u0110"+
		"\u0111\u0005\u0003\u0000\u0000\u0111\u0113\u0003\u0018\f\u0000\u0112\u0110"+
		"\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000\u0113\u0114"+
		"\u0001\u0000\u0000\u0000\u0114\u0115\u0005\u000b\u0000\u0000\u0115\u0116"+
		"\u0003$\u0012\u0000\u0116\u0117\u0005\f\u0000\u0000\u0117\u0127\u0001"+
		"\u0000\u0000\u0000\u0118\u0119\u0005\u0019\u0000\u0000\u0119\u011b\u0005"+
		"\t\u0000\u0000\u011a\u011c\u0003\f\u0006\u0000\u011b\u011a\u0001\u0000"+
		"\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000"+
		"\u0000\u0000\u011d\u0120\u0005\n\u0000\u0000\u011e\u011f\u0005\u0003\u0000"+
		"\u0000\u011f\u0121\u0003\u0018\f\u0000\u0120\u011e\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000"+
		"\u0122\u0123\u0005\u000b\u0000\u0000\u0123\u0124\u0003$\u0012\u0000\u0124"+
		"\u0125\u0005\f\u0000\u0000\u0125\u0127\u0001\u0000\u0000\u0000\u0126\u0109"+
		"\u0001\u0000\u0000\u0000\u0126\u0118\u0001\u0000\u0000\u0000\u0127+\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0005\u001a\u0000\u0000\u0129\u012a\u0005"+
		"&\u0000\u0000\u012a\u012b\u0005\r\u0000\u0000\u012b\u012c\u0003\u0012"+
		"\t\u0000\u012c\u012f\u0005\u000e\u0000\u0000\u012d\u012e\u0005\u0003\u0000"+
		"\u0000\u012e\u0130\u0003\u0018\f\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000"+
		"\u0131\u0132\u0005\u000b\u0000\u0000\u0132\u0133\u0003$\u0012\u0000\u0133"+
		"\u0134\u0005\f\u0000\u0000\u0134\u0142\u0001\u0000\u0000\u0000\u0135\u0136"+
		"\u0005\u001a\u0000\u0000\u0136\u0137\u0005\r\u0000\u0000\u0137\u0138\u0003"+
		"\u0012\t\u0000\u0138\u013b\u0005\u000e\u0000\u0000\u0139\u013a\u0005\u0003"+
		"\u0000\u0000\u013a\u013c\u0003\u0018\f\u0000\u013b\u0139\u0001\u0000\u0000"+
		"\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000"+
		"\u0000\u013d\u013e\u0005\u000b\u0000\u0000\u013e\u013f\u0003$\u0012\u0000"+
		"\u013f\u0140\u0005\f\u0000\u0000\u0140\u0142\u0001\u0000\u0000\u0000\u0141"+
		"\u0128\u0001\u0000\u0000\u0000\u0141\u0135\u0001\u0000\u0000\u0000\u0142"+
		"-\u0001\u0000\u0000\u0000\u0143\u0144\u0005\u0012\u0000\u0000\u0144\u0145"+
		"\u0005&\u0000\u0000\u0145\u0146\u0005\u001e\u0000\u0000\u0146\u0147\u0003"+
		"$\u0012\u0000\u0147\u0148\u0005\b\u0000\u0000\u0148\u0149\u0003$\u0012"+
		"\u0000\u0149\u0152\u0001\u0000\u0000\u0000\u014a\u014b\u0005\u0012\u0000"+
		"\u0000\u014b\u014c\u0003\u0006\u0003\u0000\u014c\u014d\u0005\u001e\u0000"+
		"\u0000\u014d\u014e\u0003$\u0012\u0000\u014e\u014f\u0005\b\u0000\u0000"+
		"\u014f\u0150\u0003$\u0012\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151"+
		"\u0143\u0001\u0000\u0000\u0000\u0151\u014a\u0001\u0000\u0000\u0000\u0152"+
		"/\u0001\u0000\u0000\u0000\u0153\u0154\u0005\u0014\u0000\u0000\u0154\u0155"+
		"\u0005&\u0000\u0000\u0155\u0157\u0005\t\u0000\u0000\u0156\u0158\u0003"+
		"\f\u0006\u0000\u0157\u0156\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000"+
		"\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015c\u0005\n\u0000"+
		"\u0000\u015a\u015b\u0005\u0003\u0000\u0000\u015b\u015d\u0003\u0018\f\u0000"+
		"\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000"+
		"\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0005\u001e\u0000\u0000"+
		"\u015f\u0172\u0003$\u0012\u0000\u0160\u0161\u0005\u0014\u0000\u0000\u0161"+
		"\u0162\u0005&\u0000\u0000\u0162\u0163\u0005\r\u0000\u0000\u0163\u0164"+
		"\u0003\u0012\t\u0000\u0164\u0165\u0005\u000e\u0000\u0000\u0165\u0167\u0005"+
		"\t\u0000\u0000\u0166\u0168\u0003\f\u0006\u0000\u0167\u0166\u0001\u0000"+
		"\u0000\u0000\u0167\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000"+
		"\u0000\u0000\u0169\u016c\u0005\n\u0000\u0000\u016a\u016b\u0005\u0003\u0000"+
		"\u0000\u016b\u016d\u0003\u0018\f\u0000\u016c\u016a\u0001\u0000\u0000\u0000"+
		"\u016c\u016d\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000"+
		"\u016e\u016f\u0005\u001e\u0000\u0000\u016f\u0170\u0003$\u0012\u0000\u0170"+
		"\u0172\u0001\u0000\u0000\u0000\u0171\u0153\u0001\u0000\u0000\u0000\u0171"+
		"\u0160\u0001\u0000\u0000\u0000\u01721\u0001\u0000\u0000\u0000\u0173\u0176"+
		"\u00030\u0018\u0000\u0174\u0176\u0003$\u0012\u0000\u0175\u0173\u0001\u0000"+
		"\u0000\u0000\u0175\u0174\u0001\u0000\u0000\u0000\u01763\u0001\u0000\u0000"+
		"\u0000\u0177\u0179\u00032\u0019\u0000\u0178\u0177\u0001\u0000\u0000\u0000"+
		"\u0179\u017c\u0001\u0000\u0000\u0000\u017a\u0178\u0001\u0000\u0000\u0000"+
		"\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u017d\u0001\u0000\u0000\u0000"+
		"\u017c\u017a\u0001\u0000\u0000\u0000\u017d\u017e\u0005\u0000\u0000\u0001"+
		"\u017e5\u0001\u0000\u0000\u0000&>FHPW_elq|\u0083\u0095\u00a3\u00aa\u00b6"+
		"\u00bd\u00c5\u00dd\u00e9\u00f0\u00fe\u0100\u010d\u0112\u011b\u0120\u0126"+
		"\u012f\u013b\u0141\u0151\u0157\u015c\u0167\u016c\u0171\u0175\u017a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}