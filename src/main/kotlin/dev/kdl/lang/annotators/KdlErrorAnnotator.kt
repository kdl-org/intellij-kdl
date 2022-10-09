package dev.kdl.lang.annotators

import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.patterns.PatternCondition
import com.intellij.patterns.PlatformPatterns.psiComment
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern.Capture
import com.intellij.patterns.StandardPatterns.or
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.ProcessingContext
import dev.kdl.KdlBundle
import dev.kdl.lang.psi.*
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psi.ext.KdlElementTypes.BARE_IDENTIFIER
import dev.kdl.lang.psiElement

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
        if (illegalBareIdentifierPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range(element)
                .tooltip(KdlBundle.message("annotator.bareIdentifierAsString"))
                .withFix(WrapBareIdentifierIntention(element))
                .create()
        }
        if (missingNodeTerminatorPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range((element as KdlPsiNodeBlock).identifier)
                .tooltip(KdlBundle.message("annotator.missingTerminator"))
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

        // spec doesn't allow bare identifier in literal
        val illegalBareIdentifierPattern: Capture<PsiElement> = psiElement(BARE_IDENTIFIER)
            .withParent(psiElement<KdlPsiLiteral>())

        // spec doesn't allow whitespace or empty node terminator
        val missingNodeTerminatorPattern: Capture<KdlPsiNodeBlock> = psiElement<KdlPsiNodeBlock>()
            .andNot(psiElement().withLastChild(psiElement<KdlPsiNodeTerminator>()))
    }
}

private class WrapBareIdentifierIntention(element: PsiElement) : LocalQuickFixAndIntentionActionOnPsiElement(element) {

    override fun getText(): String = KdlBundle.message("intention.bareIdentifierAsString")
    override fun getFamilyName(): String = text

    override fun invoke(
        project: Project,
        file: PsiFile,
        editor: Editor?,
        startElement: PsiElement,
        endElement: PsiElement
    ) {
        KdlElementFactory.createStringLiteral(project, startElement.text)
            .let { startElement.replace(it) }

        FileEditorManager.getInstance(project)
            .selectedTextEditor!!
            .caretModel
            .moveCaretRelatively(1, 0, false, false, false)
    }
}
