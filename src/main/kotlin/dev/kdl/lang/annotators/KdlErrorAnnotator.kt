package dev.kdl.lang.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.patterns.PatternCondition
import com.intellij.patterns.PlatformPatterns.psiComment
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern.Capture
import com.intellij.patterns.StandardPatterns.or
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import dev.kdl.KdlBundle
import dev.kdl.lang.psi.KdlPsiProp
import dev.kdl.lang.psi.KdlPsiType
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psiElement
import java.util.*

class KdlErrorAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (illegalWhitespacesPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range(element)
                .tooltip(KdlBundle.message("annotator.illegalWhitespace"))
                .create()
        }
        if (illegalCommentsPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range(element)
                .tooltip(KdlBundle.message("annotator.illegalComments"))
                .create()
        }
    }

    companion object {
        // spec doesn't allow spaces in following places
        val illegalWhitespacesPattern: Capture<PsiElement> = psiElement()
            .withElementType(KdlElementTypes.UNICODE_SPACE)
            .withParent(
                or(
                    psiElement<KdlPsiType>(),
                    psiElement<KdlPsiProp>(),
                )
            )

        // spec doesn't allow comments in following places
        val illegalCommentsPattern: Capture<PsiComment> = psiComment()
            .andOr(
                psiElement().with(object : PatternCondition<PsiElement>("withPrevSibling") {
                    override fun accepts(t: PsiElement, context: ProcessingContext?): Boolean {
                        return psiElement<KdlPsiType>().accepts(t.prevSibling, context)
                    }
                }),
                psiElement().withParent(
                    or(
                        psiElement<KdlPsiType>(),
                        psiElement<KdlPsiProp>(),
                    )
                ),
            )
    }
}