package dev.kdl.lang.escape;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static dev.kdl.lang.escape.EscapeType.*;
import static dev.kdl.lang.psi.ext.KdlElementTypes.*;

%%

%{}
  /**
    * Dedicated storage for starting position of some previously successful
    * match
    */
  private int zzPostponedMarkedPos = -1;
%}


%class KdlStringLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType


ESCAPE_PREFIX="\\"

LF={ESCAPE_PREFIX} "n"
CR={ESCAPE_PREFIX} "r"
TAB={ESCAPE_PREFIX} "t"
BACKSLASH={ESCAPE_PREFIX} "\\"
FORWARDSLASH={ESCAPE_PREFIX} "/"
QUOTE={ESCAPE_PREFIX} "\""
BACKSPACE={ESCAPE_PREFIX} "b"
FORM_FEED={ESCAPE_PREFIX} "f"

UNICODE_ESCAPE={ESCAPE_PREFIX} "u{" {HEX_DIGIT}{1, 6} "}"

HEX_DIGIT=[0-9a-fA-F]

ESCAPE = {LF}
    | {CR}
    | {TAB}
    | {BACKSLASH}
    | {FORWARDSLASH}
    | {QUOTE}
    | {BACKSPACE}
    | {FORM_FEED}
    | {UNICODE_ESCAPE}

%state OTHER

%%

<YYINITIAL> {
    {ESCAPE}  { return ESCAPE; }
    [^]       { return NOT_ESCAPE; }
}