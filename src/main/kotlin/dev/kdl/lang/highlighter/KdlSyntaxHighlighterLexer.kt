package dev.kdl.lang.highlighter;

import com.intellij.lexer.LayeredLexer
import com.intellij.psi.tree.IElementType
import dev.kdl.lang.escape.KdlStringFlexAdapter
import dev.kdl.lang.lexer.KdlFlexAdapter
import dev.kdl.lang.psi.ext.KdlElementTypes

class KdlSyntaxHighlighterLexer : LayeredLexer(KdlFlexAdapter()) {
    init {
        registerSelfStoppingLayer(
            KdlStringFlexAdapter(),
            arrayOf(KdlElementTypes.STRING_LITERAL),
            IElementType.EMPTY_ARRAY
        )
    }
}
