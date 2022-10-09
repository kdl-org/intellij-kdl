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
import com.intellij.patterns.StandardPatterns.string
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.ProcessingContext
import dev.kdl.KdlBundle
import dev.kdl.lang.psi.*
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psi.ext.KdlElementTypes.BARE_IDENTIFIER
import dev.kdl.lang.psi.ext.KdlElementTypes.RAW_STRING_LITERAL
import dev.kdl.lang.psiElement
import java.util.regex.Pattern

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
        if (unbalancedHashesPattern.accepts(element)) {
            holder.newSilentAnnotation(HighlightSeverity.ERROR)
                .range(element)
                .tooltip(KdlBundle.message("annotator.unbalancedHashes"))
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

        // in some cases hashes can be unbalanced, spec doesn't allow that
        val unbalancedHashesPattern: Capture<PsiElement> = psiElement(RAW_STRING_LITERAL)
            .withText(string().with(object : PatternCondition<String>("endsWith") {
                val PATTERN = Pattern.compile("^r(?<starthash>#*)\".*\"(?<endhash>#*)$")
                override fun accepts(str: String, context: ProcessingContext): Boolean {
                    val matcher = PATTERN.matcher(str)
                    if (!matcher.find()) {
                        return false
                    }
                    val startHashes = matcher.group("starthash").length
                    val endHashes = matcher.group("endhash").length
                    return startHashes != endHashes
                }
            }))
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
