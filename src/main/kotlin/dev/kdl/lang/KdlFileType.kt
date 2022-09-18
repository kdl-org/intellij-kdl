package dev.kdl.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.util.ui.ColorIcon
import java.awt.Color
import javax.swing.Icon

object KdlFileType: LanguageFileType(KdlLanguage) {
    override fun getName(): String = "KDL"

    override fun getDescription(): String = "The KDL document language"

    override fun getDefaultExtension(): String = "kdl"

    override fun getIcon(): Icon = ColorIcon(10, Color(0xFFDA00)) // TODO icon
}