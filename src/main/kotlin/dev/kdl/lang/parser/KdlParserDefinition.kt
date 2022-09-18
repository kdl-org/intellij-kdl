package dev.kdl.lang.parser

import dev.kdl.lang.KdlLanguage
import dev.kdl.lang.lexer.KdlFlexAdapter
import dev.kdl.lang.psi.KdlPsiFile
import dev.kdl.lang.psi.ext.KdlElementTypes
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

class KdlParserDefinition: ParserDefinition {
    override fun createLexer(project: Project?): Lexer = KdlFlexAdapter()
    override fun createParser(project: Project?): PsiParser = KdlParser()
    override fun createElement(node: ASTNode?): PsiElement = KdlElementTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = KdlPsiFile(viewProvider)
    override fun getFileNodeType(): IFileElementType = FILE
    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    companion object {
        val FILE = IFileElementType(KdlLanguage)
    }
}