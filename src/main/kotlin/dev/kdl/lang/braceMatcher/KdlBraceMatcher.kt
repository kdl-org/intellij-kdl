package dev.kdl.lang.braceMatcher

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import dev.kdl.lang.psi.ext.KdlElementTypes

class KdlBraceMatcher: PairedBraceMatcher {

    override fun getPairs() = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int = openingBraceOffset

    companion object {
        private val PAIRS = arrayOf(
            BracePair(KdlElementTypes.L_BRACE, KdlElementTypes.R_BRACE, true),
            BracePair(KdlElementTypes.L_BRACK, KdlElementTypes.R_BRACK, false),
            BracePair(KdlElementTypes.L_PAREN, KdlElementTypes.R_PAREN, false),
        )
    }
}