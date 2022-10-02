package dev.kdl.lang.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.formatter.common.AbstractBlock
import dev.kdl.lang.parser.KdlParserDefinition.Companion.WHITESPACES
import dev.kdl.lang.psi.ext.KdlElementTypes.NODE_BLOCK
import dev.kdl.lang.psi.ext.KdlElementTypes.NODE_CHILDREN

class KdlBlock constructor(
    node: ASTNode,
    wrap: Wrap?,
    private val indent: Indent,
    private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, null) {

    override fun buildChildren(): List<Block> {
        return generateSequence({ myNode.firstChildNode }, { it.treeNext })
            .filter { childNode -> !WHITESPACES.contains(childNode.elementType) }
            .map { childNode ->
                val (childIndent, currentWrap) = if (myNode.elementType === NODE_CHILDREN) {
                    if (childNode.elementType == NODE_BLOCK) {
                        Indent.getNormalIndent() to Wrap.createWrap(WrapType.ALWAYS, true)
                    } else {
                        Indent.getNoneIndent() to null
                    }
                } else {
                    Indent.getNoneIndent() to null
                }

                KdlBlock(
                    childNode,
                    currentWrap,
                    childIndent,
                    spacingBuilder
                )
            }
            .toList()
    }

    override fun getChildIndent(): Indent {
        return Indent.getNormalIndent()
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