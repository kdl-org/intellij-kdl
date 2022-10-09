package dev.kdl.lang.editing

import com.intellij.codeInsight.actions.MultiCaretCodeInsightAction
import com.intellij.codeInsight.generation.actions.CommentByBlockCommentAction
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase


@TestDataPath("\$CONTENT_ROOT/testData/editing")
class KdlEditingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/editing"

    fun testEnter1() = doEditingTest("\n")
    fun testEnter2() = doEditingTest("\n")

    fun testCommentLine() = doCommenterTest(CommentByLineCommentAction())
    fun testCommentMultiline() = doCommenterTest(CommentByBlockCommentAction())

    fun testUncommentArg() = doIntentionTest("Uncomment")
    fun testUncommentNode() = doIntentionTest("Uncomment")
    fun testUncommentNodeChildren() = doIntentionTest("Uncomment")
    fun testUncommentProp() = doIntentionTest("Uncomment")
    fun testWrapBareIdentifierInQuotes() = doIntentionTest("Wrap in quotes")

    private fun doEditingTest(characters: String) {
        myFixture.configureByFile(getBeforeFile())
        myFixture.type(characters)
        myFixture.checkResultByFile(getAfterFile())
    }

    private fun doCommenterTest(action: MultiCaretCodeInsightAction) {
        myFixture.configureByFile(getBeforeFile())
        action.actionPerformedImpl(project, myFixture.editor)
        myFixture.checkResultByFile(getAfterFile())
    }

    private fun doIntentionTest(hint: String) {
        myFixture.configureByFile(getBeforeFile())
        val action: IntentionAction = myFixture.findSingleIntention(hint)
        assertNotNull(action)
        myFixture.launchAction(action)
        myFixture.checkResultByFile(getAfterFile())
    }

    private fun getBeforeFile(): String {
        return "${getTestName(true)}.kdl"
    }

    private fun getAfterFile(): String {
        return "${getTestName(true)}_after.kdl"
    }
}