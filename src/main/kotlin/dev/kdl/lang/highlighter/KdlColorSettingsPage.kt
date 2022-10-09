package dev.kdl.lang.highlighter

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class KdlColorSettingsPage : ColorSettingsPage {
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "KDL"

    override fun getIcon(): Icon? = null

    override fun getHighlighter(): SyntaxHighlighter = KdlSyntaxHighlighter()

    override fun getDemoText(): String = """
        title "Hello, World"
        bookmarks 12 15 188 1234
        author "Alex Monad" email="alex@example.com" active=true
        contents {
          section "First section" {
            paragraph "This is the first paragraph"
            paragraph "This is the second paragraph"
          }
        }
        node1; node2; node3;
        node "this\nhas\tescapes"
        other r"C:\Users\zkat\"
        string "my
        multiline
        value"
        other-raw r#"hello"world"#
        node null
        num 1.234e-42
        my-hex 0xdeadbeef
        my-octal 0o755
        my-binary 0b10101101
        bignum 1_000_000
        // C style comment
        /*
        C style multiline comment
        */
        tag /*foo=true*/ bar=false
        /*/*
        hello
        */*/
        
        /-mynode "foo" key=1 {
          a
          b
          c
        }

        mynode /-"commented" "not commented" /-key="value" /-{
          a
          b
        }
        
        numbers (u8)10 (i32)20 myfloat=(f32)1.5 {
          strings (uuid)"123e4567-e89b-12d3-a456-426614174000" (date)"2021-02-03" filter=(regex)r"${'$'}\d+"
          (author)person name="Alex"
        }
    """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Keyword", KdlSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Identifier", KdlSyntaxHighlighter.BARE_IDENTIFIER),
            AttributesDescriptor("Comma", KdlSyntaxHighlighter.COMMA),
            AttributesDescriptor("Semicolon", KdlSyntaxHighlighter.SEMICOLON),
            AttributesDescriptor("Parentheses", KdlSyntaxHighlighter.PARENTHESES),
            AttributesDescriptor("Braces", KdlSyntaxHighlighter.BRACES),
            AttributesDescriptor("Brackets", KdlSyntaxHighlighter.BRACKETS),
            AttributesDescriptor("Block comment", KdlSyntaxHighlighter.BLOCK_COMMENT),
            AttributesDescriptor("Line comment", KdlSyntaxHighlighter.LINE_COMMENT),
            AttributesDescriptor("Item comment", KdlSyntaxHighlighter.ITEM_COMMENT),
            AttributesDescriptor("Number", KdlSyntaxHighlighter.NUMBER),
            AttributesDescriptor("String", KdlSyntaxHighlighter.STRING),
            AttributesDescriptor("String//Escape sequence", KdlSyntaxHighlighter.STRING_ESCAPE_SEQUENCE)
        )
    }
}