package dev.kdl.lang.annotators

import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import dev.kdl.KdlBundle
import dev.kdl.lang.highlighter.KdlSyntaxHighlighter
import dev.kdl.lang.psi.ext.KdlCommentableItem

class KdlItemCommentAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is KdlCommentableItem && element.getSlashdash() != null) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(KdlSyntaxHighlighter.ITEM_COMMENT)
                .withFix(UncommentIntention(element)).create()
        }
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