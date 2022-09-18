package dev.kdl.lang.lexer

import dev.kdl.lang.KdlLanguage
import com.intellij.psi.tree.IElementType

class KdlTokenType(debugName: String): IElementType(debugName, KdlLanguage)
