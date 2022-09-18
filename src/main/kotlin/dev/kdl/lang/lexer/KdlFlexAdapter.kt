package dev.kdl.lang.lexer

import com.intellij.lexer.FlexAdapter

class KdlFlexAdapter: FlexAdapter(KdlLexer(null))