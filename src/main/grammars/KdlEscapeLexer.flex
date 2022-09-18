package dev.kdl.lang.escape;

import static dev.kdl.lang.escape.EscapeType.*;

%%

%class KdlEscapeLexer
%unicode
%function advance
%type EscapeType


ESCAPE="\\"

LF={ESCAPE} "n"
CR={ESCAPE} "r"
TAB={ESCAPE} "t"
BACKSLASH={ESCAPE} "\\"
FORWARDSLASH={ESCAPE} "/"
QUOTE={ESCAPE} "\""
BACKSPACE={ESCAPE} "b"
FORM_FEED={ESCAPE} "f"

UNICODE_ESCAPE={ESCAPE} "u{" {HEX_DIGIT}{1, 6} "}"

HEX_DIGIT=[0-9a-fA-F]

%%

<YYINITIAL> {
    {LF}                { return LF; }
    {CR}                { return CR; }
    {TAB}               { return TAB; }
    {BACKSLASH}         { return BACKSLASH; }
    {FORWARDSLASH}      { return FORWARDSLASH; }
    {QUOTE}             { return QUOTE; }
    {BACKSPACE}         { return BACKSPACE; }
    {FORM_FEED}         { return FORM_FEED; }
    {UNICODE_ESCAPE}    { return UNICODE_ESCAPE; }

    [^]                 { return NOT_ESCAPE; }
}