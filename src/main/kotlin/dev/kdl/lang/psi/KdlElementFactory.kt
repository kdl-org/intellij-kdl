package dev.kdl.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import dev.kdl.lang.KdlFileType
import dev.kdl.lang.escape.escapeString


object KdlElementFactory {
    fun createStringLiteral(project: Project, stringValue: String): KdlPsiString {
        val escapedString = escapeString(stringValue)
        val nodeString = """node "$escapedString""""

        val file = createFile(project, nodeString)

        return file.findChildrenByClass(KdlPsiNodeBlock::class.java)
            .first()
            .nodePropOrArgList
            .first()
            .arg!!
            .value
            .literal
            .string!!
    }

    fun createFile(project: Project, text: String): KdlPsiFile {
        return PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.kdl", KdlFileType, text) as KdlPsiFile
    }
}