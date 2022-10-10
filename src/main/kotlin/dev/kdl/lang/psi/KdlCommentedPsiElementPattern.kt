package dev.kdl.lang.psi

import com.intellij.patterns.PatternCondition
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.ProcessingContext
import dev.kdl.lang.isInjectedFragment
import dev.kdl.lang.psi.ext.KdlCommentableItem

class KdlCommentedPsiElementPattern : PatternCondition<PsiElement>("withKdlCommentedParentNode") {
    override fun accepts(t: PsiElement, context: ProcessingContext?): Boolean {
        val parentFunction: (PsiElement) -> PsiElement? = {
            if (it is PsiFile && it.isInjectedFragment) {
                it.parent
            } else {
                it.context
            }
        }

        return generateSequence(t, parentFunction)
            .mapNotNull { it as? KdlCommentableItem }
            .map { it.getSlashdash() != null }
            .reduce { acc, b -> acc || b }
    }
}