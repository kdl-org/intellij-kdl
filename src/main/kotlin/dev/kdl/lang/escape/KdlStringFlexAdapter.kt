package dev.kdl.lang.escape

import com.intellij.lexer.FlexAdapter

class KdlStringFlexAdapter: FlexAdapter(KdlStringLexer(null))