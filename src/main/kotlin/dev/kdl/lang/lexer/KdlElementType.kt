package dev.kdl.lang.lexer

import dev.kdl.lang.KdlLanguage
import com.intellij.psi.tree.IElementType

class KdlElementType(debugName: String) : IElementType(debugName, KdlLanguage)