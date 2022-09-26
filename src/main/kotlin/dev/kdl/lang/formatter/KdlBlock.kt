package dev.kdl.lang.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.formatter.common.AbstractBlock
import dev.kdl.lang.parser.KdlParserDefinition.Companion.WHITESPACES
import dev.kdl.lang.psi.ext.KdlElementTypes.*

class KdlBlock constructor(
    node: ASTNode,
    private val indent: Indent,
    private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, null, null) {

    override fun buildChildren(): List<Block> {
        return generateSequence({ myNode.firstChildNode }, { it.treeNext })
            .filter { astNode -> !WHITESPACES.contains(astNode.elementType) }
            .map { astNode ->
                val currentIndent = if (astNode.elementType === NODE_BLOCK && astNode.treeParent?.elementType != TOP_LEVEL_NODE_LIST) {
                    Indent.getNormalIndent()
                } else {
                    Indent.getNoneIndent()
                }

                KdlBlock(
                    astNode,
                    currentIndent,
                    spacingBuilder
                )
            }
            .toList()
    }

    override fun getIndent(): Indent {
        return indent
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}