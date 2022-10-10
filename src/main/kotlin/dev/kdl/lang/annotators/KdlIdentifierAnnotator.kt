package dev.kdl.lang.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.IDENTIFIER
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.PROPERTY_NAME
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.TYPE
import dev.kdl.lang.psi.KdlPsiIdentifier
import dev.kdl.lang.psi.KdlPsiNodeBlock
import dev.kdl.lang.psi.KdlPsiProp
import dev.kdl.lang.psi.KdlPsiType
import dev.kdl.lang.psiElement

class KdlIdentifierAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (identifierPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(IDENTIFIER)
                .create()
        }
        if (propNamePattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(PROPERTY_NAME)
                .create()
        }
        if (typePattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(TYPE)
                .create()
        }
    }

    companion object {
        val identifierPattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiNodeBlock>())
        val propNamePattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiProp>())
        val typePattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiType>())
    }
}