package dev.kdl.lang.folding

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/testData/folding")
class KdlFoldingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/folding"

    fun testFolding() = doFoldingTest()

    private fun doFoldingTest() {
        myFixture.testFolding(testDataPath + "/" + getTestName(true) + ".kdl")
    }
}