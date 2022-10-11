package dev.kdl.lang.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiFile
import com.intellij.psi.formatter.common.AbstractBlock
import dev.kdl.lang.children
import dev.kdl.lang.parser.KdlParserDefinition.Companion.WHITESPACES
import dev.kdl.lang.psi.ext.KdlElementTypes.*

class KdlBlock constructor(
    node: ASTNode,
    wrap: Wrap?,
    private val indent: Indent,
    private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, null) {

    override fun buildChildren(): List<Block> {
        return myNode.children()
            .flatMap { childNode ->
                if (childNode.elementType == NODE_TERMINATOR) {
                    return@flatMap childNode.children()
                }
                return@flatMap sequenceOf(childNode)
            }
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

    override fun getChildIndent(): Indent? {
        if (myNode.elementType == NODE_CHILDREN) {
            return Indent.getNormalIndent()
        } else if (myNode.psi is PsiFile || myNode.treeParent?.psi is PsiFile) {
            return Indent.getNoneIndent()
        }
        return null
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