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
		TRUE=16, FALSE=17, VAL=18, IN=19, DEF=20, REF=21, TOP=22, FORALL=23, SUBTYPE=24, 
		UNIT=25, LAM=26, TYLAM=27, AND=28, OR=29, NOT=30, EQ=31, EQ2=32, NEQ=33, 
		ADD=34, MINUS=35, MULT=36, DIV=37, INT=38, ID=39, WS=40;
	public static final int
		RULE_qualElem = 0, RULE_qualElems = 1, RULE_qual = 2, RULE_idQty = 3, 
		RULE_arg = 4, RULE_argList = 5, RULE_namedArgList = 6, RULE_funTy = 7, 
		RULE_tyParam = 8, RULE_tyParamList = 9, RULE_tyFunTy = 10, RULE_ty = 11, 
		RULE_qty = 12, RULE_op2 = 13, RULE_op1 = 14, RULE_value = 15, RULE_expr = 16, 
		RULE_alloc = 17, RULE_assign = 18, RULE_deref = 19, RULE_lam = 20, RULE_tyLam = 21, 
		RULE_let = 22, RULE_def = 23, RULE_program = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualElem", "qualElems", "qual", "idQty", "arg", "argList", "namedArgList", 
			"funTy", "tyParam", "tyParamList", "tyFunTy", "ty", "qty", "op2", "op1", 
			"value", "expr", "alloc", "assign", "deref", "lam", "tyLam", "let", "def", 
			"program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@'", "'!'", "':'", "':='", "'^'", null, "','", "';'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'=>'", "'true'", "'false'", "'val'", 
			"'in'", "'def'", "'ref'", "'top'", "'forall'", "'<:'", "'()'", "'lam'", 
			"'Lam'", "'&&'", "'||'", "'~'", "'='", "'=='", "'!='", "'+'", "'-'", 
			"'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", 
			"TRUE", "FALSE", "VAL", "IN", "DEF", "REF", "TOP", "FORALL", "SUBTYPE", 
			"UNIT", "LAM", "TYLAM", "AND", "OR", "NOT", "EQ", "EQ2", "NEQ", "ADD", 
			"MINUS", "MULT", "DIV", "INT", "ID", "WS"
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
			setState(50);
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
			setState(52);
			match(LCURLY);
			setState(53);
			qualElem();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(54);
				match(COMMA);
				setState(55);
				qualElem();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FRESH:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(FRESH);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
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
				setState(66);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(65);
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
			setState(70);
			match(ID);
			setState(71);
			match(COLON);
			setState(72);
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
	public static class ArgContext extends ParserRuleContext {
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public IdQtyContext idQty() {
			return getRuleContext(IdQtyContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arg);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
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
	public static class ArgListContext extends ParserRuleContext {
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			arg();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(79);
				match(COMMA);
				setState(80);
				arg();
				}
				}
				setState(85);
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
	public static class NamedArgListContext extends ParserRuleContext {
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
		public NamedArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterNamedArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitNamedArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitNamedArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamedArgListContext namedArgList() throws RecognitionException {
		NamedArgListContext _localctx = new NamedArgListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_namedArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			idQty();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(87);
				match(COMMA);
				setState(88);
				idQty();
				}
				}
				setState(93);
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
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
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
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(ID);
				setState(95);
				match(LPAREN);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 549766300160L) != 0)) {
					{
					setState(96);
					argList();
					}
				}

				setState(99);
				match(RPAREN);
				setState(100);
				match(RIGHTARROW);
				setState(101);
				qty();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(LPAREN);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 549766300160L) != 0)) {
					{
					setState(103);
					argList();
					}
				}

				setState(106);
				match(RPAREN);
				setState(107);
				match(RIGHTARROW);
				setState(108);
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
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(ID);
				setState(113);
				match(SUBTYPE);
				setState(114);
				ty();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(ID);
				setState(116);
				match(HAT);
				setState(117);
				match(ID);
				setState(118);
				match(SUBTYPE);
				setState(119);
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
			setState(122);
			tyParam();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(123);
				match(COMMA);
				setState(124);
				tyParam();
				}
				}
				setState(129);
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
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(FORALL);
				setState(131);
				match(LBRACKET);
				setState(132);
				tyParamList();
				setState(133);
				match(RBRACKET);
				setState(134);
				match(RIGHTARROW);
				setState(135);
				qty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(FORALL);
				setState(138);
				match(ID);
				setState(139);
				match(LBRACKET);
				setState(140);
				tyParamList();
				setState(141);
				match(RBRACKET);
				setState(142);
				match(RIGHTARROW);
				setState(143);
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
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(REF);
				setState(149);
				match(LPAREN);
				setState(150);
				qty();
				setState(151);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				funTy();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(154);
				tyFunTy();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(155);
				match(LPAREN);
				setState(156);
				ty();
				setState(157);
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
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				ty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				ty();
				setState(163);
				match(HAT);
				setState(164);
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
			setState(168);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 271388246016L) != 0)) ) {
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
			setState(170);
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
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(FALSE);
				}
				break;
			case UNIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				match(UNIT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				match(INT);
				}
				break;
			case LAM:
				enterOuterAlt(_localctx, 5);
				{
				setState(176);
				lam();
				}
				break;
			case TYLAM:
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
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
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public AllocContext alloc() {
			return getRuleContext(AllocContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode LCURLY() { return getToken(DiamondParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DiamondParser.RCURLY, 0); }
		public Op2Context op2() {
			return getRuleContext(Op2Context.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(DiamondParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DiamondParser.COMMA, i);
		}
		public TerminalNode AT() { return getToken(DiamondParser.AT, 0); }
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(181);
				match(ID);
				}
				break;
			case 2:
				{
				setState(182);
				value();
				}
				break;
			case 3:
				{
				setState(183);
				op1();
				setState(184);
				expr(12);
				}
				break;
			case 4:
				{
				setState(186);
				let();
				}
				break;
			case 5:
				{
				setState(187);
				alloc();
				}
				break;
			case 6:
				{
				setState(188);
				assign();
				}
				break;
			case 7:
				{
				setState(189);
				deref();
				}
				break;
			case 8:
				{
				setState(190);
				match(LPAREN);
				setState(191);
				expr(0);
				setState(192);
				match(RPAREN);
				}
				break;
			case 9:
				{
				setState(194);
				match(LCURLY);
				setState(195);
				expr(0);
				setState(196);
				match(RCURLY);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(240);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(200);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(201);
						op2();
						setState(202);
						expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(204);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(205);
						match(LPAREN);
						setState(206);
						expr(0);
						setState(211);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(207);
							match(COMMA);
							setState(208);
							expr(0);
							}
							}
							setState(213);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(214);
						match(RPAREN);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(217);
						match(AT);
						setState(218);
						match(LPAREN);
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
						setState(227);
						match(RPAREN);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(229);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(230);
						match(LBRACKET);
						setState(231);
						qty();
						setState(232);
						match(RBRACKET);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(235);
						match(AT);
						setState(236);
						match(LBRACKET);
						setState(237);
						qty();
						setState(238);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		enterRule(_localctx, 34, RULE_alloc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(REF);
			setState(246);
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
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode COLONEQ() { return getToken(DiamondParser.COLONEQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(ID);
			setState(249);
			match(COLONEQ);
			setState(250);
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
		enterRule(_localctx, 38, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(EXCLA);
			setState(253);
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
		public NamedArgListContext namedArgList() {
			return getRuleContext(NamedArgListContext.class,0);
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
		enterRule(_localctx, 40, RULE_lam);
		int _la;
		try {
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				match(LAM);
				setState(256);
				match(ID);
				setState(257);
				match(LPAREN);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(258);
					namedArgList();
					}
				}

				setState(261);
				match(RPAREN);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(262);
					match(COLON);
					setState(263);
					qty();
					}
				}

				setState(266);
				match(LCURLY);
				setState(267);
				expr(0);
				setState(268);
				match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				match(LAM);
				setState(271);
				match(LPAREN);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(272);
					namedArgList();
					}
				}

				setState(275);
				match(RPAREN);
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(276);
					match(COLON);
					setState(277);
					qty();
					}
				}

				setState(280);
				match(LCURLY);
				setState(281);
				expr(0);
				setState(282);
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
		enterRule(_localctx, 42, RULE_tyLam);
		int _la;
		try {
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				match(TYLAM);
				setState(287);
				match(ID);
				setState(288);
				match(LBRACKET);
				setState(289);
				tyParamList();
				setState(290);
				match(RBRACKET);
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(291);
					match(COLON);
					setState(292);
					qty();
					}
				}

				setState(295);
				match(LCURLY);
				setState(296);
				expr(0);
				setState(297);
				match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(299);
				match(TYLAM);
				setState(300);
				match(LBRACKET);
				setState(301);
				tyParamList();
				setState(302);
				match(RBRACKET);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(303);
					match(COLON);
					setState(304);
					qty();
					}
				}

				setState(307);
				match(LCURLY);
				setState(308);
				expr(0);
				setState(309);
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
		enterRule(_localctx, 44, RULE_let);
		try {
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				match(VAL);
				setState(314);
				match(ID);
				setState(315);
				match(EQ);
				setState(316);
				expr(0);
				setState(317);
				match(SEMI);
				setState(318);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(320);
				match(VAL);
				setState(321);
				idQty();
				setState(322);
				match(EQ);
				setState(323);
				expr(0);
				setState(324);
				match(SEMI);
				setState(325);
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
	public static class DefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(DiamondParser.DEF, 0); }
		public TerminalNode ID() { return getToken(DiamondParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DiamondParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DiamondParser.RPAREN, 0); }
		public TerminalNode EQ() { return getToken(DiamondParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NamedArgListContext namedArgList() {
			return getRuleContext(NamedArgListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DiamondParser.COLON, 0); }
		public QtyContext qty() {
			return getRuleContext(QtyContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(DiamondParser.LBRACKET, 0); }
		public TyParamListContext tyParamList() {
			return getRuleContext(TyParamListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(DiamondParser.RBRACKET, 0); }
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DiamondParserListener ) ((DiamondParserListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DiamondParserVisitor ) return ((DiamondParserVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_def);
		int _la;
		try {
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(DEF);
				setState(330);
				match(ID);
				setState(331);
				match(LPAREN);
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(332);
					namedArgList();
					}
				}

				setState(335);
				match(RPAREN);
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(336);
					match(COLON);
					setState(337);
					qty();
					}
				}

				setState(340);
				match(EQ);
				setState(341);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				match(DEF);
				setState(343);
				match(ID);
				setState(344);
				match(LBRACKET);
				setState(345);
				tyParamList();
				setState(346);
				match(RBRACKET);
				setState(347);
				match(LPAREN);
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(348);
					namedArgList();
					}
				}

				setState(351);
				match(RPAREN);
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(352);
					match(COLON);
					setState(353);
					qty();
					}
				}

				setState(356);
				match(EQ);
				setState(357);
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
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
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
		enterRule(_localctx, 48, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 825945950724L) != 0)) {
				{
				setState(363);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DEF:
					{
					setState(361);
					def();
					}
					break;
				case EXCLA:
				case LPAREN:
				case LCURLY:
				case TRUE:
				case FALSE:
				case VAL:
				case REF:
				case UNIT:
				case LAM:
				case TYLAM:
				case NOT:
				case INT:
				case ID:
					{
					setState(362);
					expr(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(368);
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
		case 16:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001(\u0173\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u00019\b\u0001\n\u0001\f\u0001<\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002C\b\u0002\u0003\u0002"+
		"E\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0003\u0004M\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005R\b\u0005\n\u0005\f\u0005U\t\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006Z\b\u0006\n\u0006\f\u0006]\t\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007b\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007i\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007n\b\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\by\b\b\u0001\t"+
		"\u0001\t\u0001\t\u0005\t~\b\t\n\t\f\t\u0081\t\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u0092\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00a7\b\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u00b3\b\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00c7\b\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00d2\b\u0010\n\u0010\f\u0010"+
		"\u00d5\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00df\b\u0010\n\u0010"+
		"\f\u0010\u00e2\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00f1\b\u0010\n\u0010"+
		"\f\u0010\u00f4\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0104\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0109\b\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u0112\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u0117\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u011d\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0126\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0132\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0138\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u0148\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u014e\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u0153\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u015e\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0163\b\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0168\b\u0017\u0001\u0018\u0001\u0018"+
		"\u0005\u0018\u016c\b\u0018\n\u0018\f\u0018\u016f\t\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0000\u0001 \u0019\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000\u0002"+
		"\u0002\u0000\u0006\u0006\'\'\u0002\u0000\u001c\u001d %\u0190\u00002\u0001"+
		"\u0000\u0000\u0000\u00024\u0001\u0000\u0000\u0000\u0004D\u0001\u0000\u0000"+
		"\u0000\u0006F\u0001\u0000\u0000\u0000\bL\u0001\u0000\u0000\u0000\nN\u0001"+
		"\u0000\u0000\u0000\fV\u0001\u0000\u0000\u0000\u000em\u0001\u0000\u0000"+
		"\u0000\u0010x\u0001\u0000\u0000\u0000\u0012z\u0001\u0000\u0000\u0000\u0014"+
		"\u0091\u0001\u0000\u0000\u0000\u0016\u009f\u0001\u0000\u0000\u0000\u0018"+
		"\u00a6\u0001\u0000\u0000\u0000\u001a\u00a8\u0001\u0000\u0000\u0000\u001c"+
		"\u00aa\u0001\u0000\u0000\u0000\u001e\u00b2\u0001\u0000\u0000\u0000 \u00c6"+
		"\u0001\u0000\u0000\u0000\"\u00f5\u0001\u0000\u0000\u0000$\u00f8\u0001"+
		"\u0000\u0000\u0000&\u00fc\u0001\u0000\u0000\u0000(\u011c\u0001\u0000\u0000"+
		"\u0000*\u0137\u0001\u0000\u0000\u0000,\u0147\u0001\u0000\u0000\u0000."+
		"\u0167\u0001\u0000\u0000\u00000\u016d\u0001\u0000\u0000\u000023\u0007"+
		"\u0000\u0000\u00003\u0001\u0001\u0000\u0000\u000045\u0005\u000b\u0000"+
		"\u00005:\u0003\u0000\u0000\u000067\u0005\u0007\u0000\u000079\u0003\u0000"+
		"\u0000\u000086\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000:8\u0001"+
		"\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000"+
		"<:\u0001\u0000\u0000\u0000=>\u0005\f\u0000\u0000>\u0003\u0001\u0000\u0000"+
		"\u0000?E\u0005\u0006\u0000\u0000@E\u0005\'\u0000\u0000AC\u0003\u0002\u0001"+
		"\u0000BA\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CE\u0001\u0000"+
		"\u0000\u0000D?\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000\u0000DB\u0001"+
		"\u0000\u0000\u0000E\u0005\u0001\u0000\u0000\u0000FG\u0005\'\u0000\u0000"+
		"GH\u0005\u0003\u0000\u0000HI\u0003\u0018\f\u0000I\u0007\u0001\u0000\u0000"+
		"\u0000JM\u0003\u0018\f\u0000KM\u0003\u0006\u0003\u0000LJ\u0001\u0000\u0000"+
		"\u0000LK\u0001\u0000\u0000\u0000M\t\u0001\u0000\u0000\u0000NS\u0003\b"+
		"\u0004\u0000OP\u0005\u0007\u0000\u0000PR\u0003\b\u0004\u0000QO\u0001\u0000"+
		"\u0000\u0000RU\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000T\u000b\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000V[\u0003\u0006\u0003\u0000WX\u0005\u0007\u0000\u0000XZ\u0003\u0006"+
		"\u0003\u0000YW\u0001\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001"+
		"\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\\r\u0001\u0000\u0000\u0000"+
		"][\u0001\u0000\u0000\u0000^_\u0005\'\u0000\u0000_a\u0005\t\u0000\u0000"+
		"`b\u0003\n\u0005\u0000a`\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000cd\u0005\n\u0000\u0000de\u0005\u000f\u0000\u0000"+
		"en\u0003\u0018\f\u0000fh\u0005\t\u0000\u0000gi\u0003\n\u0005\u0000hg\u0001"+
		"\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jk\u0005\n\u0000\u0000kl\u0005\u000f\u0000\u0000ln\u0003\u0018\f\u0000"+
		"m^\u0001\u0000\u0000\u0000mf\u0001\u0000\u0000\u0000n\u000f\u0001\u0000"+
		"\u0000\u0000oy\u0005\'\u0000\u0000pq\u0005\'\u0000\u0000qr\u0005\u0018"+
		"\u0000\u0000ry\u0003\u0016\u000b\u0000st\u0005\'\u0000\u0000tu\u0005\u0005"+
		"\u0000\u0000uv\u0005\'\u0000\u0000vw\u0005\u0018\u0000\u0000wy\u0003\u0018"+
		"\f\u0000xo\u0001\u0000\u0000\u0000xp\u0001\u0000\u0000\u0000xs\u0001\u0000"+
		"\u0000\u0000y\u0011\u0001\u0000\u0000\u0000z\u007f\u0003\u0010\b\u0000"+
		"{|\u0005\u0007\u0000\u0000|~\u0003\u0010\b\u0000}{\u0001\u0000\u0000\u0000"+
		"~\u0081\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u0001\u0000\u0000\u0000\u0080\u0013\u0001\u0000\u0000\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0017\u0000\u0000\u0083\u0084"+
		"\u0005\r\u0000\u0000\u0084\u0085\u0003\u0012\t\u0000\u0085\u0086\u0005"+
		"\u000e\u0000\u0000\u0086\u0087\u0005\u000f\u0000\u0000\u0087\u0088\u0003"+
		"\u0018\f\u0000\u0088\u0092\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0017"+
		"\u0000\u0000\u008a\u008b\u0005\'\u0000\u0000\u008b\u008c\u0005\r\u0000"+
		"\u0000\u008c\u008d\u0003\u0012\t\u0000\u008d\u008e\u0005\u000e\u0000\u0000"+
		"\u008e\u008f\u0005\u000f\u0000\u0000\u008f\u0090\u0003\u0018\f\u0000\u0090"+
		"\u0092\u0001\u0000\u0000\u0000\u0091\u0082\u0001\u0000\u0000\u0000\u0091"+
		"\u0089\u0001\u0000\u0000\u0000\u0092\u0015\u0001\u0000\u0000\u0000\u0093"+
		"\u00a0\u0005\'\u0000\u0000\u0094\u0095\u0005\u0015\u0000\u0000\u0095\u0096"+
		"\u0005\t\u0000\u0000\u0096\u0097\u0003\u0018\f\u0000\u0097\u0098\u0005"+
		"\n\u0000\u0000\u0098\u00a0\u0001\u0000\u0000\u0000\u0099\u00a0\u0003\u000e"+
		"\u0007\u0000\u009a\u00a0\u0003\u0014\n\u0000\u009b\u009c\u0005\t\u0000"+
		"\u0000\u009c\u009d\u0003\u0016\u000b\u0000\u009d\u009e\u0005\n\u0000\u0000"+
		"\u009e\u00a0\u0001\u0000\u0000\u0000\u009f\u0093\u0001\u0000\u0000\u0000"+
		"\u009f\u0094\u0001\u0000\u0000\u0000\u009f\u0099\u0001\u0000\u0000\u0000"+
		"\u009f\u009a\u0001\u0000\u0000\u0000\u009f\u009b\u0001\u0000\u0000\u0000"+
		"\u00a0\u0017\u0001\u0000\u0000\u0000\u00a1\u00a7\u0003\u0016\u000b\u0000"+
		"\u00a2\u00a3\u0003\u0016\u000b\u0000\u00a3\u00a4\u0005\u0005\u0000\u0000"+
		"\u00a4\u00a5\u0003\u0004\u0002\u0000\u00a5\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a1\u0001\u0000\u0000\u0000\u00a6\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a7\u0019\u0001\u0000\u0000\u0000\u00a8\u00a9\u0007\u0001\u0000\u0000"+
		"\u00a9\u001b\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005\u001e\u0000\u0000"+
		"\u00ab\u001d\u0001\u0000\u0000\u0000\u00ac\u00b3\u0005\u0010\u0000\u0000"+
		"\u00ad\u00b3\u0005\u0011\u0000\u0000\u00ae\u00b3\u0005\u0019\u0000\u0000"+
		"\u00af\u00b3\u0005&\u0000\u0000\u00b0\u00b3\u0003(\u0014\u0000\u00b1\u00b3"+
		"\u0003*\u0015\u0000\u00b2\u00ac\u0001\u0000\u0000\u0000\u00b2\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b2\u00ae\u0001\u0000\u0000\u0000\u00b2\u00af\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b3\u001f\u0001\u0000\u0000\u0000\u00b4\u00b5\u0006"+
		"\u0010\uffff\uffff\u0000\u00b5\u00c7\u0005\'\u0000\u0000\u00b6\u00c7\u0003"+
		"\u001e\u000f\u0000\u00b7\u00b8\u0003\u001c\u000e\u0000\u00b8\u00b9\u0003"+
		" \u0010\f\u00b9\u00c7\u0001\u0000\u0000\u0000\u00ba\u00c7\u0003,\u0016"+
		"\u0000\u00bb\u00c7\u0003\"\u0011\u0000\u00bc\u00c7\u0003$\u0012\u0000"+
		"\u00bd\u00c7\u0003&\u0013\u0000\u00be\u00bf\u0005\t\u0000\u0000\u00bf"+
		"\u00c0\u0003 \u0010\u0000\u00c0\u00c1\u0005\n\u0000\u0000\u00c1\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\u000b\u0000\u0000\u00c3\u00c4"+
		"\u0003 \u0010\u0000\u00c4\u00c5\u0005\f\u0000\u0000\u00c5\u00c7\u0001"+
		"\u0000\u0000\u0000\u00c6\u00b4\u0001\u0000\u0000\u0000\u00c6\u00b6\u0001"+
		"\u0000\u0000\u0000\u00c6\u00b7\u0001\u0000\u0000\u0000\u00c6\u00ba\u0001"+
		"\u0000\u0000\u0000\u00c6\u00bb\u0001\u0000\u0000\u0000\u00c6\u00bc\u0001"+
		"\u0000\u0000\u0000\u00c6\u00bd\u0001\u0000\u0000\u0000\u00c6\u00be\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c2\u0001\u0000\u0000\u0000\u00c7\u00f2\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c9\n\u000b\u0000\u0000\u00c9\u00ca\u0003\u001a"+
		"\r\u0000\u00ca\u00cb\u0003 \u0010\f\u00cb\u00f1\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cd\n\u0004\u0000\u0000\u00cd\u00ce\u0005\t\u0000\u0000\u00ce"+
		"\u00d3\u0003 \u0010\u0000\u00cf\u00d0\u0005\u0007\u0000\u0000\u00d0\u00d2"+
		"\u0003 \u0010\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\u0005\n\u0000\u0000\u00d7\u00f1\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\n\u0003\u0000\u0000\u00d9\u00da\u0005\u0001\u0000"+
		"\u0000\u00da\u00db\u0005\t\u0000\u0000\u00db\u00e0\u0003 \u0010\u0000"+
		"\u00dc\u00dd\u0005\u0007\u0000\u0000\u00dd\u00df\u0003 \u0010\u0000\u00de"+
		"\u00dc\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0"+
		"\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0005\n\u0000\u0000\u00e4\u00f1\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\n\u0002\u0000\u0000\u00e6\u00e7\u0005\r\u0000\u0000\u00e7\u00e8\u0003"+
		"\u0018\f\u0000\u00e8\u00e9\u0005\u000e\u0000\u0000\u00e9\u00f1\u0001\u0000"+
		"\u0000\u0000\u00ea\u00eb\n\u0001\u0000\u0000\u00eb\u00ec\u0005\u0001\u0000"+
		"\u0000\u00ec\u00ed\u0005\r\u0000\u0000\u00ed\u00ee\u0003\u0018\f\u0000"+
		"\u00ee\u00ef\u0005\u000e\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f0\u00c8\u0001\u0000\u0000\u0000\u00f0\u00cc\u0001\u0000\u0000\u0000"+
		"\u00f0\u00d8\u0001\u0000\u0000\u0000\u00f0\u00e5\u0001\u0000\u0000\u0000"+
		"\u00f0\u00ea\u0001\u0000\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f3!\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f6\u0005\u0015\u0000\u0000\u00f6\u00f7\u0003 \u0010\u0000\u00f7#\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f9\u0005\'\u0000\u0000\u00f9\u00fa\u0005\u0004"+
		"\u0000\u0000\u00fa\u00fb\u0003 \u0010\u0000\u00fb%\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0005\u0002\u0000\u0000\u00fd\u00fe\u0003 \u0010\u0000\u00fe"+
		"\'\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u001a\u0000\u0000\u0100\u0101"+
		"\u0005\'\u0000\u0000\u0101\u0103\u0005\t\u0000\u0000\u0102\u0104\u0003"+
		"\f\u0006\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0108\u0005\n\u0000"+
		"\u0000\u0106\u0107\u0005\u0003\u0000\u0000\u0107\u0109\u0003\u0018\f\u0000"+
		"\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000"+
		"\u0109\u010a\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u000b\u0000\u0000"+
		"\u010b\u010c\u0003 \u0010\u0000\u010c\u010d\u0005\f\u0000\u0000\u010d"+
		"\u011d\u0001\u0000\u0000\u0000\u010e\u010f\u0005\u001a\u0000\u0000\u010f"+
		"\u0111\u0005\t\u0000\u0000\u0110\u0112\u0003\f\u0006\u0000\u0111\u0110"+
		"\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0001\u0000\u0000\u0000\u0113\u0116\u0005\n\u0000\u0000\u0114\u0115\u0005"+
		"\u0003\u0000\u0000\u0115\u0117\u0003\u0018\f\u0000\u0116\u0114\u0001\u0000"+
		"\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000"+
		"\u0000\u0000\u0118\u0119\u0005\u000b\u0000\u0000\u0119\u011a\u0003 \u0010"+
		"\u0000\u011a\u011b\u0005\f\u0000\u0000\u011b\u011d\u0001\u0000\u0000\u0000"+
		"\u011c\u00ff\u0001\u0000\u0000\u0000\u011c\u010e\u0001\u0000\u0000\u0000"+
		"\u011d)\u0001\u0000\u0000\u0000\u011e\u011f\u0005\u001b\u0000\u0000\u011f"+
		"\u0120\u0005\'\u0000\u0000\u0120\u0121\u0005\r\u0000\u0000\u0121\u0122"+
		"\u0003\u0012\t\u0000\u0122\u0125\u0005\u000e\u0000\u0000\u0123\u0124\u0005"+
		"\u0003\u0000\u0000\u0124\u0126\u0003\u0018\f\u0000\u0125\u0123\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000"+
		"\u0000\u0000\u0127\u0128\u0005\u000b\u0000\u0000\u0128\u0129\u0003 \u0010"+
		"\u0000\u0129\u012a\u0005\f\u0000\u0000\u012a\u0138\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0005\u001b\u0000\u0000\u012c\u012d\u0005\r\u0000\u0000\u012d"+
		"\u012e\u0003\u0012\t\u0000\u012e\u0131\u0005\u000e\u0000\u0000\u012f\u0130"+
		"\u0005\u0003\u0000\u0000\u0130\u0132\u0003\u0018\f\u0000\u0131\u012f\u0001"+
		"\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0133\u0001"+
		"\u0000\u0000\u0000\u0133\u0134\u0005\u000b\u0000\u0000\u0134\u0135\u0003"+
		" \u0010\u0000\u0135\u0136\u0005\f\u0000\u0000\u0136\u0138\u0001\u0000"+
		"\u0000\u0000\u0137\u011e\u0001\u0000\u0000\u0000\u0137\u012b\u0001\u0000"+
		"\u0000\u0000\u0138+\u0001\u0000\u0000\u0000\u0139\u013a\u0005\u0012\u0000"+
		"\u0000\u013a\u013b\u0005\'\u0000\u0000\u013b\u013c\u0005\u001f\u0000\u0000"+
		"\u013c\u013d\u0003 \u0010\u0000\u013d\u013e\u0005\b\u0000\u0000\u013e"+
		"\u013f\u0003 \u0010\u0000\u013f\u0148\u0001\u0000\u0000\u0000\u0140\u0141"+
		"\u0005\u0012\u0000\u0000\u0141\u0142\u0003\u0006\u0003\u0000\u0142\u0143"+
		"\u0005\u001f\u0000\u0000\u0143\u0144\u0003 \u0010\u0000\u0144\u0145\u0005"+
		"\b\u0000\u0000\u0145\u0146\u0003 \u0010\u0000\u0146\u0148\u0001\u0000"+
		"\u0000\u0000\u0147\u0139\u0001\u0000\u0000\u0000\u0147\u0140\u0001\u0000"+
		"\u0000\u0000\u0148-\u0001\u0000\u0000\u0000\u0149\u014a\u0005\u0014\u0000"+
		"\u0000\u014a\u014b\u0005\'\u0000\u0000\u014b\u014d\u0005\t\u0000\u0000"+
		"\u014c\u014e\u0003\f\u0006\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014d"+
		"\u014e\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f"+
		"\u0152\u0005\n\u0000\u0000\u0150\u0151\u0005\u0003\u0000\u0000\u0151\u0153"+
		"\u0003\u0018\f\u0000\u0152\u0150\u0001\u0000\u0000\u0000\u0152\u0153\u0001"+
		"\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0155\u0005"+
		"\u001f\u0000\u0000\u0155\u0168\u0003 \u0010\u0000\u0156\u0157\u0005\u0014"+
		"\u0000\u0000\u0157\u0158\u0005\'\u0000\u0000\u0158\u0159\u0005\r\u0000"+
		"\u0000\u0159\u015a\u0003\u0012\t\u0000\u015a\u015b\u0005\u000e\u0000\u0000"+
		"\u015b\u015d\u0005\t\u0000\u0000\u015c\u015e\u0003\f\u0006\u0000\u015d"+
		"\u015c\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e"+
		"\u015f\u0001\u0000\u0000\u0000\u015f\u0162\u0005\n\u0000\u0000\u0160\u0161"+
		"\u0005\u0003\u0000\u0000\u0161\u0163\u0003\u0018\f\u0000\u0162\u0160\u0001"+
		"\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0001"+
		"\u0000\u0000\u0000\u0164\u0165\u0005\u001f\u0000\u0000\u0165\u0166\u0003"+
		" \u0010\u0000\u0166\u0168\u0001\u0000\u0000\u0000\u0167\u0149\u0001\u0000"+
		"\u0000\u0000\u0167\u0156\u0001\u0000\u0000\u0000\u0168/\u0001\u0000\u0000"+
		"\u0000\u0169\u016c\u0003.\u0017\u0000\u016a\u016c\u0003 \u0010\u0000\u016b"+
		"\u0169\u0001\u0000\u0000\u0000\u016b\u016a\u0001\u0000\u0000\u0000\u016c"+
		"\u016f\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016d"+
		"\u016e\u0001\u0000\u0000\u0000\u016e\u0170\u0001\u0000\u0000\u0000\u016f"+
		"\u016d\u0001\u0000\u0000\u0000\u0170\u0171\u0005\u0000\u0000\u0001\u0171"+
		"1\u0001\u0000\u0000\u0000$:BDLS[ahmx\u007f\u0091\u009f\u00a6\u00b2\u00c6"+
		"\u00d3\u00e0\u00f0\u00f2\u0103\u0108\u0111\u0116\u011c\u0125\u0131\u0137"+
		"\u0147\u014d\u0152\u015d\u0162\u0167\u016b\u016d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}