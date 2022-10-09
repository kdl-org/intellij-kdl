package dev.kdl.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import dev.kdl.KdlIcons.FileType
import javax.swing.Icon

object KdlFileType: LanguageFileType(KdlLanguage) {
    override fun getName(): String = "KDL"

    override fun getDescription(): String = "The KDL document language"

    override fun getDefaultExtension(): String = "kdl"

    override fun getIcon(): Icon = FileType
}