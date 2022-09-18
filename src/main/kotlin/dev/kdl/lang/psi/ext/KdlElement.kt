package dev.kdl.lang.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

interface KdlElement: PsiElement

abstract class KdlElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), KdlElement
