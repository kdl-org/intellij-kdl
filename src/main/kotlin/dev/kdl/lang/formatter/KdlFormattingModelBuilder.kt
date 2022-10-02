package dev.kdl.lang.formatter

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import dev.kdl.lang.KdlLanguage
import dev.kdl.lang.psi.ext.KdlElementTypes.IDENTIFIER

class KdlFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings
        return FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            KdlBlock(
                formattingContext.node,
                null,
                Indent.getNoneIndent(),
                createSpaceBuilder(codeStyleSettings)
            ),
            codeStyleSettings
        )
    }

    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
        return SpacingBuilder(settings, KdlLanguage)
            .after(IDENTIFIER).spaces(1)
    }
}