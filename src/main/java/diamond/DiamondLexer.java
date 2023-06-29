package diamond.parser;
// Generated from DiamondLexer.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DiamondLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AT=1, EXCLA=2, COLON=3, COLONEQ=4, HAT=5, FRESH=6, COMMA=7, SEMI=8, LPAREN=9, 
		RPAREN=10, LCURLY=11, RCURLY=12, LBRACKET=13, RBRACKET=14, RIGHTARROW=15, 
		TRUE=16, FALSE=17, VAL=18, IN=19, DEF=20, REF=21, FORALL=22, SUBTYPE=23, 
		UNIT=24, LAM=25, TYLAM=26, AND=27, OR=28, NOT=29, EQ=30, EQ2=31, NEQ=32, 
		ADD=33, MINUS=34, MULT=35, DIV=36, INT=37, ID=38, WS=39;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AT", "EXCLA", "COLON", "COLONEQ", "HAT", "FRESH", "COMMA", "SEMI", "LPAREN", 
			"RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "RIGHTARROW", "TRUE", 
			"FALSE", "VAL", "IN", "DEF", "REF", "FORALL", "SUBTYPE", "UNIT", "LAM", 
			"TYLAM", "AND", "OR", "NOT", "EQ", "EQ2", "NEQ", "ADD", "MINUS", "MULT", 
			"DIV", "INT", "ID", "WS"
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
			"MULT", "DIV", "INT", "ID", "WS"
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


	public DiamondLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DiamondLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\'\u00ce\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005^\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001$\u0004$\u00bd\b$\u000b$\f$\u00be\u0001%\u0001%\u0005%\u00c3\b"+
		"%\n%\f%\u00c6\t%\u0001&\u0004&\u00c9\b&\u000b&\f&\u00ca\u0001&\u0001&"+
		"\u0000\u0000\'\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f"+
		"? A!C\"E#G$I%K&M\'\u0001\u0000\u0004\u0001\u000009\u0003\u0000AZ__az\u0004"+
		"\u000009AZ__az\u0003\u0000\t\n\f\r  \u00d1\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?"+
		"\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000"+
		"\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000"+
		"\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M"+
		"\u0001\u0000\u0000\u0000\u0001O\u0001\u0000\u0000\u0000\u0003Q\u0001\u0000"+
		"\u0000\u0000\u0005S\u0001\u0000\u0000\u0000\u0007U\u0001\u0000\u0000\u0000"+
		"\tX\u0001\u0000\u0000\u0000\u000b]\u0001\u0000\u0000\u0000\r_\u0001\u0000"+
		"\u0000\u0000\u000fa\u0001\u0000\u0000\u0000\u0011c\u0001\u0000\u0000\u0000"+
		"\u0013e\u0001\u0000\u0000\u0000\u0015g\u0001\u0000\u0000\u0000\u0017i"+
		"\u0001\u0000\u0000\u0000\u0019k\u0001\u0000\u0000\u0000\u001bm\u0001\u0000"+
		"\u0000\u0000\u001do\u0001\u0000\u0000\u0000\u001fr\u0001\u0000\u0000\u0000"+
		"!w\u0001\u0000\u0000\u0000#}\u0001\u0000\u0000\u0000%\u0081\u0001\u0000"+
		"\u0000\u0000\'\u0084\u0001\u0000\u0000\u0000)\u0088\u0001\u0000\u0000"+
		"\u0000+\u008c\u0001\u0000\u0000\u0000-\u0093\u0001\u0000\u0000\u0000/"+
		"\u0096\u0001\u0000\u0000\u00001\u009b\u0001\u0000\u0000\u00003\u009f\u0001"+
		"\u0000\u0000\u00005\u00a3\u0001\u0000\u0000\u00007\u00a6\u0001\u0000\u0000"+
		"\u00009\u00a9\u0001\u0000\u0000\u0000;\u00ab\u0001\u0000\u0000\u0000="+
		"\u00ad\u0001\u0000\u0000\u0000?\u00b0\u0001\u0000\u0000\u0000A\u00b3\u0001"+
		"\u0000\u0000\u0000C\u00b5\u0001\u0000\u0000\u0000E\u00b7\u0001\u0000\u0000"+
		"\u0000G\u00b9\u0001\u0000\u0000\u0000I\u00bc\u0001\u0000\u0000\u0000K"+
		"\u00c0\u0001\u0000\u0000\u0000M\u00c8\u0001\u0000\u0000\u0000OP\u0005"+
		"@\u0000\u0000P\u0002\u0001\u0000\u0000\u0000QR\u0005!\u0000\u0000R\u0004"+
		"\u0001\u0000\u0000\u0000ST\u0005:\u0000\u0000T\u0006\u0001\u0000\u0000"+
		"\u0000UV\u0005:\u0000\u0000VW\u0005=\u0000\u0000W\b\u0001\u0000\u0000"+
		"\u0000XY\u0005^\u0000\u0000Y\n\u0001\u0000\u0000\u0000Z[\u0005<\u0000"+
		"\u0000[^\u0005>\u0000\u0000\\^\u0005\u2666\u0000\u0000]Z\u0001\u0000\u0000"+
		"\u0000]\\\u0001\u0000\u0000\u0000^\f\u0001\u0000\u0000\u0000_`\u0005,"+
		"\u0000\u0000`\u000e\u0001\u0000\u0000\u0000ab\u0005;\u0000\u0000b\u0010"+
		"\u0001\u0000\u0000\u0000cd\u0005(\u0000\u0000d\u0012\u0001\u0000\u0000"+
		"\u0000ef\u0005)\u0000\u0000f\u0014\u0001\u0000\u0000\u0000gh\u0005{\u0000"+
		"\u0000h\u0016\u0001\u0000\u0000\u0000ij\u0005}\u0000\u0000j\u0018\u0001"+
		"\u0000\u0000\u0000kl\u0005[\u0000\u0000l\u001a\u0001\u0000\u0000\u0000"+
		"mn\u0005]\u0000\u0000n\u001c\u0001\u0000\u0000\u0000op\u0005=\u0000\u0000"+
		"pq\u0005>\u0000\u0000q\u001e\u0001\u0000\u0000\u0000rs\u0005t\u0000\u0000"+
		"st\u0005r\u0000\u0000tu\u0005u\u0000\u0000uv\u0005e\u0000\u0000v \u0001"+
		"\u0000\u0000\u0000wx\u0005f\u0000\u0000xy\u0005a\u0000\u0000yz\u0005l"+
		"\u0000\u0000z{\u0005s\u0000\u0000{|\u0005e\u0000\u0000|\"\u0001\u0000"+
		"\u0000\u0000}~\u0005v\u0000\u0000~\u007f\u0005a\u0000\u0000\u007f\u0080"+
		"\u0005l\u0000\u0000\u0080$\u0001\u0000\u0000\u0000\u0081\u0082\u0005i"+
		"\u0000\u0000\u0082\u0083\u0005n\u0000\u0000\u0083&\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0005d\u0000\u0000\u0085\u0086\u0005e\u0000\u0000\u0086\u0087"+
		"\u0005f\u0000\u0000\u0087(\u0001\u0000\u0000\u0000\u0088\u0089\u0005R"+
		"\u0000\u0000\u0089\u008a\u0005e\u0000\u0000\u008a\u008b\u0005f\u0000\u0000"+
		"\u008b*\u0001\u0000\u0000\u0000\u008c\u008d\u0005f\u0000\u0000\u008d\u008e"+
		"\u0005o\u0000\u0000\u008e\u008f\u0005r\u0000\u0000\u008f\u0090\u0005a"+
		"\u0000\u0000\u0090\u0091\u0005l\u0000\u0000\u0091\u0092\u0005l\u0000\u0000"+
		"\u0092,\u0001\u0000\u0000\u0000\u0093\u0094\u0005<\u0000\u0000\u0094\u0095"+
		"\u0005:\u0000\u0000\u0095.\u0001\u0000\u0000\u0000\u0096\u0097\u0005u"+
		"\u0000\u0000\u0097\u0098\u0005n\u0000\u0000\u0098\u0099\u0005i\u0000\u0000"+
		"\u0099\u009a\u0005t\u0000\u0000\u009a0\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0005l\u0000\u0000\u009c\u009d\u0005a\u0000\u0000\u009d\u009e\u0005m"+
		"\u0000\u0000\u009e2\u0001\u0000\u0000\u0000\u009f\u00a0\u0005L\u0000\u0000"+
		"\u00a0\u00a1\u0005a\u0000\u0000\u00a1\u00a2\u0005m\u0000\u0000\u00a24"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005&\u0000\u0000\u00a4\u00a5\u0005"+
		"&\u0000\u0000\u00a56\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005|\u0000"+
		"\u0000\u00a7\u00a8\u0005|\u0000\u0000\u00a88\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0005~\u0000\u0000\u00aa:\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005"+
		"=\u0000\u0000\u00ac<\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005=\u0000"+
		"\u0000\u00ae\u00af\u0005=\u0000\u0000\u00af>\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0005!\u0000\u0000\u00b1\u00b2\u0005=\u0000\u0000\u00b2@\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b4\u0005+\u0000\u0000\u00b4B\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0005-\u0000\u0000\u00b6D\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0005*\u0000\u0000\u00b8F\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005"+
		"/\u0000\u0000\u00baH\u0001\u0000\u0000\u0000\u00bb\u00bd\u0007\u0000\u0000"+
		"\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000"+
		"\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bfJ\u0001\u0000\u0000\u0000\u00c0\u00c4\u0007\u0001\u0000\u0000"+
		"\u00c1\u00c3\u0007\u0002\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5L\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c9\u0007\u0003\u0000\u0000\u00c8"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca"+
		"\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0006&\u0000\u0000\u00cdN\u0001"+
		"\u0000\u0000\u0000\u0005\u0000]\u00be\u00c4\u00ca\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}