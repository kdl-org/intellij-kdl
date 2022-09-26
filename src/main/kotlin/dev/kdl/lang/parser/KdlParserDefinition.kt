package dev.kdl.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import dev.kdl.lang.KdlLanguage
import dev.kdl.lang.lexer.KdlFlexAdapter
import dev.kdl.lang.psi.KdlPsiFile
import dev.kdl.lang.psi.ext.KdlElementTypes.*

class KdlParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = KdlFlexAdapter()
    override fun createParser(project: Project?): PsiParser = KdlParser()
    override fun createElement(node: ASTNode?): PsiElement = Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = KdlPsiFile(viewProvider)
    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = TokenSet.create(SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT)
    override fun getWhitespaceTokens(): TokenSet = TokenSet.EMPTY
    override fun getStringLiteralElements(): TokenSet = TokenSet.create(STRING)

    companion object {
        val FILE = IFileElementType(KdlLanguage)
        val WHITESPACES = TokenSet.create(UNICODE_SPACE, NEWLINE)
    }
}