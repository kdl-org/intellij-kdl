package dev.kdl.lang.psi

import dev.kdl.lang.KdlFileType
import dev.kdl.lang.KdlLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class KdlPsiFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, KdlLanguage) {
    override fun getFileType(): FileType = KdlFileType
}