package dev.kdl.lang.lexer;


import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;import org.intellij.lang.regexp.RegExpTT;

import static com.intellij.psi.TokenType.*;
import static dev.kdl.lang.psi.ext.KdlElementTypes.*;

%%


%{}
  /**
    * '#+' stride demarking start/end of raw string
    */
  private int zzShaStride = -1;

  /**
    * Dedicated storage for starting position of some previously successful
    * match
    */
  private int zzPostponedMarkedPos = -1;

  /**
    * Dedicated nested-comment level counter
    */
  private int zzNestedCommentLevel = 0;
%}

%{
  IElementType imbueBlockComment() {
      assert(zzNestedCommentLevel == 0);
      yybegin(YYINITIAL);

      zzStartRead = zzPostponedMarkedPos;
      zzPostponedMarkedPos = -1;

      return MULTI_LINE_COMMENT;
  }

  IElementType imbueRawLiteral() {
      zzStartRead = zzPostponedMarkedPos;
      zzShaStride = -1;
      zzPostponedMarkedPos = -1;

      return RAW_STRING_LITERAL;
  }

  IElementType imbueBareIdentifier() {
      zzStartRead = zzPostponedMarkedPos;
      zzPostponedMarkedPos = -1;

      return BARE_IDENTIFIER;
  }

  void resetMatch(int newState) {
        yybegin(newState);
        yypushback(yylength());
  }
%}

%class KdlLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

ESCAPED_STRING=\"{CHARACTER}*\"
CHARACTER=\\{ESCAPE} | [^\"]
ESCAPE=[\"\\\/bfnrt] | u\{{HEX_DIGIT}{1, 6}\}
HEX_DIGIT=[0-9a-fA-F]

DECIMAL={SIGN}?{INTEGER}(\.{INTEGER})?{EXPONENT}?
EXPONENT=[eE]{SIGN}?{INTEGER}
INTEGER={DIGIT}({DIGIT}|_)*
DIGIT=[0-9]
SIGN=[+-]

HEX={SIGN}?0x{HEX_DIGIT}({HEX_DIGIT}|_)*
OCTAL={SIGN}?0o[0-7][0-7_]*
BINARY={SIGN}?0b(0|1)(0|1|_)*

NULL="null"
TRUE="true"
FALSE="false"
SLASH="/"
BACKSLASH="\\"
SEMI=";"
COMMA=","
L_BRACE="{"
R_BRACE="}"
L_PAREN="("
R_PAREN=")"
L_BRACK="["
R_BRACK="]"
LESS_THEN="<"
MORE_THEN=">"
EQ="="
QUOTE="\""

KEYWORD={NULL} | {TRUE} | {FALSE}
NUMBER={DECIMAL} | {HEX} | {OCTAL} | {BINARY}

NEWLINE=\u000D |
    \u000A |
    \u000D\u000A |
    \u0085 |
    \u000C |
    \u2028 |
    \u2029

BOM=\uFEFF

UNICODE_SPACE=\u0009 |
    \u0020 |
    \u00A0 |
    \u1680 |
    \u2000 |
    \u2001 |
    \u2002 |
    \u2003 |
    \u2004 |
    \u2005 |
    \u2006 |
    \u2007 |
    \u2008 |
    \u2009 |
    \u200A |
    \u202F |
    \u205F |
    \u3000

BARE_IDENTIFIER_KILLER={NEWLINE} |
    {BOM} |
    {UNICODE_SPACE} |
    {SLASH} |
    {BACKSLASH} |
    {SEMI} |
    {COMMA} |
    {L_BRACE} |
    {R_BRACE} |
    {L_PAREN} |
    {R_PAREN} |
    {L_BRACK} |
    {R_BRACK} |
    {LESS_THEN} |
    {MORE_THEN} |
    {EQ} |
    {QUOTE}


%state IN_SINGLE_LINE_COMMENT
%state IN_MULTILINE_COMMENT
%state IN_RAW_STRING
%state IN_BARE_IDENTIFIER
%state IN_BARE_IDENTIFIER_TAIL
%state IN_KEYWORD
%state IN_NUMBER

%%

<YYINITIAL> {
    {NEWLINE}                   { return NEWLINE; }
    {BOM}                       { return BOM; }
    {UNICODE_SPACE}             { return UNICODE_SPACE; }

    "//"                        { resetMatch(IN_SINGLE_LINE_COMMENT); }
    "/*"                        { resetMatch(IN_MULTILINE_COMMENT); }

    "/-"                        { return SLASHDASH; }
    {SLASH}                     { return SLASH; }
    {BACKSLASH}                 { return BACKSLASH; }
    {SEMI}                      { return SEMI; }
    {COMMA}                     { return COMMA; }
    {L_BRACE}                   { return L_BRACE; }
    {R_BRACE}                   { return R_BRACE; }
    {L_PAREN}                   { return L_PAREN; }
    {R_PAREN}                   { return R_PAREN; }
    {L_BRACK}                   { return L_BRACK; }
    {R_BRACK}                   { return R_BRACK; }
    {LESS_THEN}                 { return LESS_THEN; }
    {MORE_THEN}                 { return MORE_THEN; }
    {EQ}                        { return EQ; }
    {QUOTE}                     { return QUOTE; }

    {KEYWORD}                   { resetMatch(IN_KEYWORD); }

    {NUMBER}                    { resetMatch(IN_NUMBER); }

    {ESCAPED_STRING}            { return STRING_LITERAL; }

    "r" #* \"                   {
          yybegin(IN_RAW_STRING);
          zzPostponedMarkedPos = zzStartRead;
          zzShaStride          = yylength() - 2;
    }

    ~{BARE_IDENTIFIER_KILLER} | [^] {
          zzPostponedMarkedPos = zzStartRead;
          yypushback(yylength());
          yybegin(IN_BARE_IDENTIFIER);
    }
}

<IN_MULTILINE_COMMENT> {
    "/*"    { if (zzNestedCommentLevel++ == 0)
                zzPostponedMarkedPos = zzStartRead;
            }

    "*/"    { if (--zzNestedCommentLevel == 0)
                return imbueBlockComment();
            }

    <<EOF>> { zzNestedCommentLevel = 0; return imbueBlockComment(); }

    [^]     { }
}

<IN_SINGLE_LINE_COMMENT> {
    <<EOF>>   { yybegin(YYINITIAL); return SINGLE_LINE_COMMENT; }
    {NEWLINE} { yybegin(YYINITIAL); return SINGLE_LINE_COMMENT; }
    [^]       { }
}

<IN_RAW_STRING> {
    \" #*     {
        int shaExcess = yylength() - 1 - zzShaStride;
        if (shaExcess >= 0) {
            yypushback(shaExcess);
            yybegin(YYINITIAL);
            return imbueRawLiteral();
        }
    }

    [^]       { }
    <<EOF>>   {
        yybegin(YYINITIAL);
        return imbueRawLiteral();
    }
}

<IN_BARE_IDENTIFIER> {
    {KEYWORD} {BARE_IDENTIFIER_KILLER} { resetMatch(IN_KEYWORD); }
    {NUMBER} {BARE_IDENTIFIER_KILLER}  { resetMatch(IN_NUMBER); }
    [^]                                { resetMatch(IN_BARE_IDENTIFIER_TAIL); }
}

<IN_BARE_IDENTIFIER_TAIL> {
    {BARE_IDENTIFIER_KILLER}  {
        resetMatch(YYINITIAL);
        return imbueBareIdentifier();
    }
    [^]                       { }
    <<EOF>>   {
        yybegin(YYINITIAL);
        return imbueBareIdentifier();
    }
}


<IN_KEYWORD> {
    {NULL}  { return NULL_LITERAL; }
    {TRUE}  { return TRUE_LITERAL; }
    {FALSE} { return FALSE_LITERAL; }
    [^]     {
        resetMatch(YYINITIAL);
    }
}

<IN_NUMBER> {
    {DECIMAL}  { return DECIMAL_LITERAL; }
    {HEX}      { return HEX_LITERAL; }
    {OCTAL}    { return OCTAL_LITERAL; }
    {BINARY}   { return BINARY_LITERAL; }
    [^]     {
        resetMatch(YYINITIAL);
    }
}

