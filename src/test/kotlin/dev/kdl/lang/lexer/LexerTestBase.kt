package dev.kdl.lang.lexer

import com.intellij.lexer.Lexer
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.CharsetToolkit
import com.intellij.testFramework.LexerTestCase
import com.intellij.testFramework.UsefulTestCase
import org.jetbrains.annotations.NonNls
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths

abstract class LexerTestBase: LexerTestCase() {

    private val testFileExtension: String = "kdl"

    fun doFileTest() {
        super.doFileTest(testFileExtension)
    }

    override fun shouldTrim(): Boolean = false

    // changes: path to source and utf8
    override fun loadTestDataFile(fileExt: String): String {
        val fileName = pathToSourceTestFile()
        try {
            val fileText = FileUtil.loadFile(fileName.toFile(), CharsetToolkit.UTF8)
            return StringUtil.convertLineSeparators(if (shouldTrim()) fileText.trim { it <= ' ' } else fileText)
        } catch (e: IOException) {
            fail("can't load file " + fileName + ": " + e.message)
        }
        return ""
    }

    // changes: path to source
    override fun doTest(@NonNls text: String, expected: String?, lexer: Lexer) {
        val result = printTokens(text, 0, lexer)
        if (expected != null) {
            UsefulTestCase.assertSameLines(expected, result)
        } else {
            UsefulTestCase.assertSameLinesWithFile(pathToGoldTestFile().toFile().canonicalPath, result)
        }
    }

    private fun pathToSourceTestFile(): Path =
        Paths.get("src/test/testData/${dirPath}/${getTestName(true)}.$testFileExtension")

    private fun pathToGoldTestFile(): Path =
        Paths.get("src/test/testData/${dirPath}/${getTestName(true)}.txt")

}