// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/analizadorfinalmad/Lexer.flex

package analizadorfinalmad;
import compilerTools.Token;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
class Lexer {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\4\1\5\22\0\1\1"+
    "\1\6\1\7\1\10\1\11\1\10\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\11\25\1\10\1\26\1\27\1\30\1\31\2\10\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\5\40\1\41\2\40"+
    "\1\42\2\40\1\43\1\44\2\40\1\45\4\40\1\10"+
    "\1\46\2\10\1\40\1\10\1\47\2\40\1\50\1\51"+
    "\1\52\1\40\1\53\1\54\2\40\1\55\1\40\1\56"+
    "\1\57\1\60\1\40\1\61\1\40\1\62\1\40\1\63"+
    "\4\40\1\64\1\65\1\66\7\0\1\3\71\0\1\10"+
    "\1\0\1\67\7\0\1\67\3\0\1\67\3\0\1\67"+
    "\1\0\1\67\6\0\1\67\1\0\1\67\4\0\1\67"+
    "\7\0\1\67\3\0\1\67\3\0\1\67\1\0\1\67"+
    "\6\0\1\67\1\0\1\67\u012b\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\3\1\1\1\4\2\1\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\2\15"+
    "\1\16\1\17\1\20\1\21\13\22\1\23\1\1\1\24"+
    "\1\25\1\0\1\1\1\26\1\27\1\0\1\1\1\0"+
    "\2\1\1\30\1\31\1\0\1\2\1\0\1\1\1\0"+
    "\2\1\1\0\1\32\1\33\1\34\7\22\1\35\2\22"+
    "\1\36\2\26\1\1\1\0\1\37\1\0\2\1\1\37"+
    "\1\0\1\1\2\0\2\15\1\1\2\0\1\15\1\0"+
    "\1\1\1\15\1\1\2\0\10\22\1\0\1\1\1\0"+
    "\1\2\1\0\1\15\1\1\2\15\1\40\12\22";

  private static int [] zzUnpackAction() {
    int [] result = new int[124];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\70\0\70\0\160\0\250\0\340\0\70\0\u0118"+
    "\0\u0150\0\70\0\70\0\70\0\u0188\0\70\0\u01c0\0\70"+
    "\0\u01f8\0\u0230\0\u0268\0\70\0\u02a0\0\u02d8\0\u0310\0\u0348"+
    "\0\u0380\0\u03b8\0\u03f0\0\u0428\0\u0460\0\u0498\0\u04d0\0\u0508"+
    "\0\u0540\0\u0578\0\70\0\u05b0\0\70\0\70\0\u05e8\0\u0620"+
    "\0\u0658\0\70\0\u0690\0\u06c8\0\u0700\0\u0738\0\u0770\0\70"+
    "\0\70\0\u07a8\0\u07e0\0\u0818\0\u0850\0\u0888\0\u08c0\0\u08f8"+
    "\0\u0930\0\70\0\70\0\70\0\u0968\0\u09a0\0\u09d8\0\u0a10"+
    "\0\u0a48\0\u0a80\0\u0ab8\0\u0348\0\u0af0\0\u0b28\0\70\0\70"+
    "\0\u0b60\0\u0b98\0\u0bd0\0\70\0\u0c08\0\u0c40\0\u0c78\0\u0cb0"+
    "\0\u0ce8\0\u0d20\0\u0d58\0\u0d90\0\u0dc8\0\u0e00\0\u0e00\0\u0e38"+
    "\0\u0e70\0\u0ea8\0\u0ee0\0\u0f18\0\u0f18\0\u0f50\0\u0f88\0\u0fc0"+
    "\0\u0ff8\0\u1030\0\u1068\0\u10a0\0\u10d8\0\u1110\0\u1148\0\u1180"+
    "\0\u11b8\0\u11f0\0\u1228\0\u0d58\0\u1260\0\u1298\0\u12d0\0\u12d0"+
    "\0\u1308\0\u0348\0\u1340\0\u1378\0\u13b0\0\u13e8\0\u1420\0\u1458"+
    "\0\u1490\0\u14c8\0\u1500\0\u1538";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[124];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\0\1\3\1\4\1\5\1\6\1\2"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\30\1\33\1\34\5\30"+
    "\1\35\1\2\3\30\1\36\1\30\1\37\3\30\1\40"+
    "\1\41\1\30\1\42\1\43\1\44\1\45\1\30\72\0"+
    "\1\3\115\0\1\46\37\0\1\47\1\50\3\47\2\50"+
    "\1\51\3\50\1\47\32\50\1\47\20\50\1\47\12\0"+
    "\1\52\55\0\2\53\1\0\10\53\1\54\3\53\1\55"+
    "\1\53\1\55\2\53\2\56\4\53\14\57\1\0\15\57"+
    "\4\53\17\0\1\60\71\0\1\61\3\0\1\23\60\0"+
    "\1\62\4\0\1\63\44\0\2\64\4\0\24\64\14\65"+
    "\1\64\15\65\3\64\1\65\2\66\4\0\16\66\2\23"+
    "\4\66\4\67\1\70\7\67\1\66\2\67\1\70\12\67"+
    "\1\66\1\71\1\66\1\67\30\0\1\72\67\0\1\73"+
    "\67\0\1\74\63\0\2\30\4\0\14\30\1\0\15\30"+
    "\3\0\1\30\24\0\2\30\4\0\14\30\1\0\10\30"+
    "\1\75\4\30\3\0\1\30\24\0\2\30\4\0\14\30"+
    "\1\0\1\76\3\30\1\77\10\30\3\0\1\30\24\0"+
    "\2\30\4\0\14\30\1\0\7\30\1\100\5\30\3\0"+
    "\1\30\24\0\2\30\4\0\1\101\13\30\1\0\15\30"+
    "\3\0\1\30\24\0\2\30\4\0\4\30\1\102\7\30"+
    "\1\0\15\30\3\0\1\30\24\0\2\30\4\0\14\30"+
    "\1\0\10\30\1\103\4\30\3\0\1\30\24\0\2\30"+
    "\4\0\14\30\1\0\3\30\1\104\11\30\3\0\1\30"+
    "\24\0\2\30\4\0\14\30\1\0\12\30\1\105\2\30"+
    "\3\0\1\30\24\0\2\30\4\0\14\30\1\0\2\30"+
    "\1\106\12\30\3\0\1\30\24\0\2\30\4\0\14\30"+
    "\1\0\1\103\14\30\3\0\1\30\65\0\1\107\2\0"+
    "\7\47\1\110\61\47\1\50\3\47\2\50\1\111\3\50"+
    "\1\47\32\50\1\47\20\50\1\47\1\0\1\112\3\0"+
    "\2\112\1\51\3\112\1\0\32\112\1\0\20\112\1\0"+
    "\2\113\1\0\10\113\1\114\32\113\1\0\21\113\13\0"+
    "\1\54\3\0\1\115\1\0\1\115\2\0\2\116\4\0"+
    "\14\117\1\0\15\117\4\0\2\113\1\0\10\113\1\114"+
    "\10\113\2\56\20\113\1\0\23\113\1\0\10\113\1\120"+
    "\3\113\1\121\1\113\1\121\2\113\2\56\4\113\14\122"+
    "\1\0\15\122\6\113\1\0\10\113\1\120\3\113\1\121"+
    "\1\113\1\121\2\113\2\122\4\113\14\122\1\0\15\122"+
    "\4\113\16\123\1\124\51\123\2\63\1\3\2\63\1\4"+
    "\62\63\24\0\1\125\67\0\1\126\1\127\4\0\14\127"+
    "\1\0\15\127\3\0\1\127\16\0\1\130\5\0\1\131"+
    "\1\132\10\0\1\133\12\0\1\133\13\0\1\133\20\0"+
    "\1\130\5\0\1\134\1\135\4\0\4\127\1\136\7\127"+
    "\1\0\2\127\1\136\12\127\1\0\1\133\1\0\1\127"+
    "\16\0\1\130\1\137\1\0\1\137\2\0\1\134\1\135"+
    "\4\0\4\127\1\136\7\127\1\0\2\127\1\136\12\127"+
    "\1\0\1\140\1\0\1\127\16\0\1\130\1\137\1\0"+
    "\1\137\2\0\1\131\1\132\10\0\1\133\12\0\1\133"+
    "\13\0\1\140\26\0\2\30\4\0\14\30\1\0\10\30"+
    "\1\141\4\30\3\0\1\30\24\0\2\30\4\0\14\30"+
    "\1\0\1\30\1\142\13\30\3\0\1\30\24\0\2\30"+
    "\4\0\14\30\1\0\1\143\14\30\3\0\1\30\24\0"+
    "\2\30\4\0\14\30\1\0\13\30\1\144\1\30\3\0"+
    "\1\30\24\0\2\30\4\0\7\30\1\145\4\30\1\0"+
    "\15\30\3\0\1\30\24\0\2\30\4\0\11\30\1\146"+
    "\2\30\1\0\15\30\3\0\1\30\24\0\2\30\4\0"+
    "\14\30\1\0\12\30\1\104\2\30\3\0\1\30\24\0"+
    "\2\30\4\0\14\30\1\0\5\30\1\147\7\30\3\0"+
    "\1\30\24\0\2\30\4\0\14\30\1\0\1\150\14\30"+
    "\3\0\1\30\7\0\1\111\61\0\1\112\3\0\2\112"+
    "\1\111\3\112\1\0\32\112\1\0\20\112\1\0\2\113"+
    "\1\0\10\113\1\2\32\113\1\0\21\113\24\0\2\116"+
    "\55\0\1\120\3\0\1\151\1\0\1\151\2\0\2\116"+
    "\4\0\14\152\1\0\15\152\17\0\1\120\3\0\1\151"+
    "\1\0\1\151\2\0\2\152\4\0\14\152\1\0\15\152"+
    "\17\0\1\120\54\0\2\113\1\0\10\113\1\2\10\113"+
    "\2\122\20\113\1\0\23\113\1\0\10\113\1\2\3\113"+
    "\1\121\1\113\1\121\2\113\2\122\4\113\14\122\1\0"+
    "\15\122\4\113\16\123\1\153\67\123\1\153\4\123\1\154"+
    "\44\123\32\0\14\127\1\0\15\127\3\0\1\127\24\0"+
    "\2\127\4\0\14\127\1\0\15\127\3\0\1\127\36\0"+
    "\1\133\12\0\1\133\13\0\1\133\26\0\1\155\1\156"+
    "\10\0\1\133\12\0\1\133\13\0\1\133\26\0\1\155"+
    "\1\156\4\0\4\127\1\136\7\127\1\0\2\127\1\136"+
    "\12\127\1\0\1\133\1\0\1\127\17\0\1\137\1\0"+
    "\1\137\43\0\1\137\26\0\1\157\1\160\4\0\4\127"+
    "\1\136\7\127\1\0\2\127\1\136\12\127\1\0\1\133"+
    "\1\0\1\127\17\0\1\137\1\0\1\137\2\0\2\127"+
    "\4\0\14\127\1\0\15\127\1\0\1\137\1\0\1\127"+
    "\25\0\1\161\61\0\1\137\1\0\1\137\3\0\1\161"+
    "\37\0\1\137\26\0\2\30\4\0\14\30\1\0\6\30"+
    "\1\162\6\30\3\0\1\30\24\0\2\30\4\0\14\30"+
    "\1\0\2\30\1\163\12\30\3\0\1\30\24\0\2\30"+
    "\4\0\14\30\1\0\12\30\1\162\2\30\3\0\1\30"+
    "\24\0\2\30\4\0\14\30\1\0\2\30\1\164\12\30"+
    "\3\0\1\30\24\0\2\30\4\0\12\30\1\165\1\30"+
    "\1\0\15\30\3\0\1\30\24\0\2\30\4\0\3\30"+
    "\1\166\10\30\1\0\15\30\3\0\1\30\24\0\2\30"+
    "\4\0\14\30\1\0\7\30\1\167\5\30\3\0\1\30"+
    "\24\0\2\30\4\0\14\30\1\0\1\30\1\104\13\30"+
    "\3\0\1\30\24\0\2\152\61\0\1\151\1\0\1\151"+
    "\2\0\2\152\4\0\14\152\1\0\15\152\4\0\16\123"+
    "\1\153\4\123\1\3\44\123\24\0\1\155\1\156\66\0"+
    "\1\155\1\156\4\0\14\127\1\0\15\127\3\0\1\127"+
    "\24\0\1\157\1\160\4\0\14\127\1\0\15\127\3\0"+
    "\1\127\24\0\2\161\4\0\14\127\1\0\15\127\3\0"+
    "\1\127\24\0\2\30\4\0\14\30\1\0\7\30\1\170"+
    "\5\30\3\0\1\30\24\0\2\30\4\0\14\30\1\0"+
    "\12\30\1\171\2\30\3\0\1\30\24\0\2\30\4\0"+
    "\10\30\1\104\3\30\1\0\15\30\3\0\1\30\24\0"+
    "\2\30\4\0\1\172\13\30\1\0\15\30\3\0\1\30"+
    "\24\0\2\30\4\0\14\30\1\0\13\30\1\104\1\30"+
    "\3\0\1\30\24\0\2\30\4\0\14\30\1\0\1\162"+
    "\14\30\3\0\1\30\24\0\2\30\4\0\14\30\1\0"+
    "\10\30\1\162\4\30\3\0\1\30\24\0\2\30\4\0"+
    "\3\30\1\173\10\30\1\0\15\30\3\0\1\30\24\0"+
    "\2\30\4\0\4\30\1\174\7\30\1\0\15\30\3\0"+
    "\1\30\24\0\2\30\4\0\11\30\1\165\2\30\1\0"+
    "\15\30\3\0\1\30";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5488];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\3\1\1\11\2\1\3\11\1\1\1\11"+
    "\1\1\1\11\3\1\1\11\16\1\1\11\1\1\2\11"+
    "\1\0\2\1\1\11\1\0\1\1\1\0\2\1\2\11"+
    "\1\0\1\1\1\0\1\1\1\0\2\1\1\0\3\11"+
    "\12\1\2\11\2\1\1\0\1\11\1\0\3\1\1\0"+
    "\1\1\2\0\3\1\2\0\1\1\1\0\3\1\2\0"+
    "\10\1\1\0\1\1\1\0\1\1\1\0\17\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[124];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return token(yytext(), "ERROR", yyline, yycolumn);
            }
            // fall through
          case 33: break;
          case 2:
            { /*Ignorar*/
            }
            // fall through
          case 34: break;
          case 3:
            { return token(yytext(), "?", yyline, yycolumn);
            }
            // fall through
          case 35: break;
          case 4:
            { return token(yytext(), "$", yyline, yycolumn);
            }
            // fall through
          case 36: break;
          case 5:
            { return token(yytext(), "(", yyline, yycolumn);
            }
            // fall through
          case 37: break;
          case 6:
            { return token(yytext(), ")", yyline, yycolumn);
            }
            // fall through
          case 38: break;
          case 7:
            { return token(yytext(), "*", yyline, yycolumn);
            }
            // fall through
          case 39: break;
          case 8:
            { return token(yytext(), "+", yyline, yycolumn);
            }
            // fall through
          case 40: break;
          case 9:
            { return token(yytext(), ",", yyline, yycolumn);
            }
            // fall through
          case 41: break;
          case 10:
            { return token(yytext(), "-", yyline, yycolumn);
            }
            // fall through
          case 42: break;
          case 11:
            { return token(yytext(), ".", yyline, yycolumn);
            }
            // fall through
          case 43: break;
          case 12:
            { return token(yytext(), "/", yyline, yycolumn);
            }
            // fall through
          case 44: break;
          case 13:
            { return token(yytext(), "num", yyline, yycolumn);
            }
            // fall through
          case 45: break;
          case 14:
            { return token(yytext(), ";", yyline, yycolumn);
            }
            // fall through
          case 46: break;
          case 15:
            { return token(yytext(), "<", yyline, yycolumn);
            }
            // fall through
          case 47: break;
          case 16:
            { return token(yytext(), "=", yyline, yycolumn);
            }
            // fall through
          case 48: break;
          case 17:
            { return token(yytext(), ">", yyline, yycolumn);
            }
            // fall through
          case 49: break;
          case 18:
            { return token(yytext(), "id", yyline, yycolumn);
            }
            // fall through
          case 50: break;
          case 19:
            { return token(yytext(), "{", yyline, yycolumn);
            }
            // fall through
          case 51: break;
          case 20:
            { return token(yytext(), "}", yyline, yycolumn);
            }
            // fall through
          case 52: break;
          case 21:
            { return token(yytext(), "!=", yyline, yycolumn);
            }
            // fall through
          case 53: break;
          case 22:
            { return token(yytext(), "cadena", yyline, yycolumn);
            }
            // fall through
          case 54: break;
          case 23:
            { return token(yytext(), "@", yyline, yycolumn);
            }
            // fall through
          case 55: break;
          case 24:
            { return token(yytext(), "++", yyline, yycolumn);
            }
            // fall through
          case 56: break;
          case 25:
            { return token(yytext(), "--", yyline, yycolumn);
            }
            // fall through
          case 57: break;
          case 26:
            { return token(yytext(), "<=", yyline, yycolumn);
            }
            // fall through
          case 58: break;
          case 27:
            { return token(yytext(), "==", yyline, yycolumn);
            }
            // fall through
          case 59: break;
          case 28:
            { return token(yytext(), ">=", yyline, yycolumn);
            }
            // fall through
          case 60: break;
          case 29:
            { return token(yytext(), "pReservada", yyline, yycolumn);
            }
            // fall through
          case 61: break;
          case 30:
            { return token(yytext(), "%", yyline, yycolumn);
            }
            // fall through
          case 62: break;
          case 31:
            { return token(yytext(), "char", yyline, yycolumn);
            }
            // fall through
          case 63: break;
          case 32:
            { return token(yytext(), "tipoDa", yyline, yycolumn);
            }
            // fall through
          case 64: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
