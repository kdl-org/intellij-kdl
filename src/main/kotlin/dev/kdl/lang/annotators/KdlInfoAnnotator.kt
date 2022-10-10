package dev.kdl.lang.annotators

import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import dev.kdl.KdlBundle
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.IDENTIFIER
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.ITEM_COMMENT
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.PROPERTY_NAME
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter.Companion.TYPE
import dev.kdl.lang.psi.*
import dev.kdl.lang.psi.ext.KdlCommentableItem
import dev.kdl.lang.psiElement

class KdlInfoAnnotator : Annotator {

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
        if (element is KdlCommentableItem && element.getSlashdash() != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(ITEM_COMMENT)
                .withFix(UncommentIntention(element))
                .create()
        }
    }

    companion object {
        val identifierPattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiNodeBlock>())
            .without(KdlCommentedPsiElementPattern())

        val propNamePattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiProp>())
            .without(KdlCommentedPsiElementPattern())

        val typePattern: PsiElementPattern.Capture<KdlPsiIdentifier> = psiElement<KdlPsiIdentifier>()
            .withParent(psiElement<KdlPsiType>())
            .without(KdlCommentedPsiElementPattern())

    }
}

private class UncommentIntention(element: PsiElement) : LocalQuickFixAndIntentionActionOnPsiElement(element) {

    override fun getText(): String = KdlBundle.message("intention.uncomment")
    override fun getFamilyName(): String = text

    override fun invoke(
        project: Project,
        file: PsiFile,
        editor: Editor?,
        startElement: PsiElement,
        endElement: PsiElement
    ) {
        PsiTreeUtil.firstChild(startElement).delete()
    }
}