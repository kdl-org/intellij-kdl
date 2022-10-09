package dev.kdl.lang

import com.intellij.lang.ASTNode
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement

inline fun <reified I : PsiElement> psiElement(): PsiElementPattern.Capture<I> {
    return PlatformPatterns.psiElement(I::class.java)
}

fun ASTNode.children() = generateSequence({ this.firstChildNode }, { it.treeNext })
