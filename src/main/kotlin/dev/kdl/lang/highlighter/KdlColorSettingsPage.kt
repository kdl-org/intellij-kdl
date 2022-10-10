package dev.kdl.lang.highlighter

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import dev.kdl.KdlIcons
import dev.kdl.lang.KdlLanguage
import javax.swing.Icon

class KdlColorSettingsPage : ColorSettingsPage {
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "KDL"

    override fun getIcon(): Icon = KdlIcons.FileType

    override fun getHighlighter(): SyntaxHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(KdlLanguage, null, null)

    override fun getDemoText(): String = """
        (<type>author</type>)<identifier>author</identifier> "Alex Monad" <propertyName>email</propertyName>="alex@example.com" <propertyName>active</propertyName>=true null
        <identifier>bookmarks</identifier> (<type>u8</type>)12 1.234e-42 0xdeadbeef 0o755 0b10101101 1_000_000 (<type>f32</type>)1.5
        <identifier>contents</identifier> {
          <identifier>section</identifier> r#"C:\Users\zkat\"# {
            <identifier>paragraph</identifier> "This is the first paragraph"
            <identifier>paragraph</identifier> "this\nhas\tescapes" <itemComment>/-"commented"</itemComment> <itemComment>/-key="value"</itemComment> <itemComment>/-{
              a
              b
            }
          }</itemComment>
          <itemComment>/-mynode "foo" key=1 {
            a
            c
          }</itemComment>
        }
        <identifier>node1</identifier>; <identifier>node2</identifier>; <identifier>node3</identifier>;
        // comment
        /*
        multiline comment
        */
    """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = ADDITIONAL_HIGHLIGHTING

    companion object {
        private val ADDITIONAL_HIGHLIGHTING: Map<String, TextAttributesKey> = mapOf(
            "itemComment" to KdlSyntaxHighlighter.ITEM_COMMENT,
            "identifier" to KdlSyntaxHighlighter.IDENTIFIER,
            "propertyName" to KdlSyntaxHighlighter.PROPERTY_NAME,
            "type" to KdlSyntaxHighlighter.TYPE,
        )

        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Keyword", KdlSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Identifier", KdlSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Property name", KdlSyntaxHighlighter.PROPERTY_NAME),
            AttributesDescriptor("Type", KdlSyntaxHighlighter.TYPE),
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