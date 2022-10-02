package dev.kdl.lang.editing

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/testData/editing")
class KdlEditingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/editing"

    fun testEnter1() = doEditingTest("\n")
    fun testEnter2() = doEditingTest("\n")

    private fun doEditingTest(characters: String) {
        val testName = getTestName(true)
        myFixture.configureByFile("$testName.kdl")
        myFixture.type(characters)
        myFixture.checkResultByFile("${testName}_after.kdl")
    }
}