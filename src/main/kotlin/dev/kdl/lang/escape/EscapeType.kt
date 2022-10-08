package dev.kdl.lang.escape

import com.intellij.lang.Language
import com.intellij.psi.tree.IElementType

object EscapeType {
    @JvmField val ESCAPE = IElementType("ESCAPE", Language.ANY)
    @JvmField val NOT_ESCAPE = IElementType("NOT_ESCAPE", Language.ANY)
}