package dev.kdl

import com.intellij.lang.annotation.HighlightSeverity.ERROR
import com.intellij.testFramework.Parameterized
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil
import dev.kdl.lang.fromPsi
import dev.kdl.lang.psi.KdlPsiFile
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized.Parameter
import java.io.File

@Suppress("RedundantVisibilityModifier")
@TestDataPath("\$CONTENT_ROOT/testData/testSuite")
@RunWith(Parameterized::class)
class KdlTestSuite : BasePlatformTestCase() {

    override fun getTestDataPath() = "src/test/testData/testSuite"

    @Parameter
    @JvmField
    public var input: String? = null

    @Parameter(1)
    @JvmField
    public var expectedKdl: String? = null

    @Test
    fun testCase() {
        val inputFile = myFixture.configureByFile(input!!)
        val kdlFile = assertInstanceOf(inputFile, KdlPsiFile::class.java)
        val hasErrors = PsiErrorElementUtil.hasErrors(project, kdlFile.virtualFile)
        val hasHighlightErrors = myFixture.doHighlighting().any { it.severity == ERROR }

        val expectedFile = expectedKdl?.let { myFixture.configureByFile(it) }

        if (expectedFile == null) {
            assertTrue(hasErrors || hasHighlightErrors)
        } else {
            assertFalse(hasErrors)
            val expectedKdlFile = assertInstanceOf(inputFile, KdlPsiFile::class.java)
            val expectedHasErrors = PsiErrorElementUtil.hasErrors(project, expectedKdlFile.virtualFile)
            val expectedHasHighlightErrors = myFixture.doHighlighting().any { it.severity == ERROR }

            assertFalse(expectedHasErrors && expectedHasHighlightErrors)
            assertEquals(fromPsi(kdlFile), fromPsi(expectedKdlFile))
        }
    }

    companion object {
        private const val BEFORE = "input"
        private const val AFTER = "expected_kdl"

        private val IGNORED_TEST_CASES = arrayOf(
            "comment_after_arg_type.kdl",
            "comment_after_node_type.kdl",
            "comment_after_prop_type.kdl",
            "comment_in_arg_type.kdl",
            "comment_in_node_type.kdl",
            "comment_in_prop_type.kdl",
        )

        @JvmStatic
        @org.junit.runners.Parameterized.Parameters()
        fun params() = listOf<Any>()

        @JvmStatic
        @Suppress("unused")
        @Parameterized.Parameters(name = "{0}")
        fun params(klass: Class<*>): List<Array<String?>> {
            val instance = klass.getConstructor().newInstance() as KdlTestSuite

            val testSuite = File(instance.testDataPath)

            val inputs = testSuite.resolve(BEFORE)
            val outputs = testSuite.resolve(AFTER)

            val inputFiles = inputs.listFiles()!!
            val outputsFiles = outputs.listFiles()!!

            val outputsMap: Map<String, File> = outputsFiles
                .map { it.relativeTo(testSuite) }
                .associateBy { it.name }

            return inputFiles
                .sortedBy { file -> file.name }
                .filter { !IGNORED_TEST_CASES.contains(it.name) }
                .map { it.relativeTo(testSuite) }
                .map { arrayOf(it.path, outputsMap[it.name]?.path) }
        }
    }


}
