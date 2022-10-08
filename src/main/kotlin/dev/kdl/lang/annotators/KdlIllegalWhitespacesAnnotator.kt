package dev.kdl.lang.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PsiElementPattern.Capture
import com.intellij.patterns.StandardPatterns.or
import com.intellij.psi.PsiElement
import dev.kdl.KdlBundle
import dev.kdl.lang.psi.KdlPsiProp
import dev.kdl.lang.psi.KdlPsiType
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psiElement

class KdlIllegalWhitespacesAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (illegalWhitespacesPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range(element)
                .tooltip(KdlBundle.message("annotator.illegalWhitespace"))
                .create()
        }
    }

    companion object {
        // spec doesn't allow spaces in following places
        val illegalWhitespacesPattern: Capture<PsiElement> = PlatformPatterns.psiElement()
            .withElementType(KdlElementTypes.UNICODE_SPACE)
            .withParent(or(
                psiElement<KdlPsiType>(),
                psiElement<KdlPsiProp>(),
            ))
    }
}