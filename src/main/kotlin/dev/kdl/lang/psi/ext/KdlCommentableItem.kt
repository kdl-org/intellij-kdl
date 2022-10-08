package dev.kdl.lang.psi.ext

import com.intellij.psi.PsiElement

interface KdlCommentableItem : KdlElement {
    fun getSlashdash(): PsiElement?
}