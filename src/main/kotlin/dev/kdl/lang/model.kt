package dev.kdl.lang

import dev.kdl.lang.psi.*
import dev.kdl.lang.psi.KdlLiteralKind.StringLiteral
import java.math.BigDecimal

data class KdlDocument(val nodes: List<KdlNode>)

data class KdlNode(
    val name: String,
    val type: KdlType?,
    val children: List<KdlNode>,
    val properties: Map<String, KdlTypedValue>,
    val arguments: List<KdlArgument>
)

data class KdlArgument(val value: KdlTypedValue)

data class KdlType(val name: String)

class KdlTypedValue(val type: KdlType?, val value: KdlValue)

sealed interface KdlValue

class KdlStringValue(val value: String) : KdlValue
class KdlNumberValue(val value: BigDecimal) : KdlValue
class KdlBooleanValue(val value: Boolean) : KdlValue
object KdlNullValue : KdlValue


fun fromPsi(file: KdlPsiFile): KdlDocument? {
    val psiNodes = file.findChildByClass(KdlPsiTopLevelNodeList::class.java) ?: return null

    val nodes: List<KdlNode> = psiNodes.nodeBlockList
        .map { nodeFromPsiNodeBlock(it) ?: return null }

    return KdlDocument(nodes)
}

private fun nodeFromPsiNodeBlock(nodeBlock: KdlPsiNodeBlock): KdlNode? {
    val identifierPsi = nodeBlock.identifier

    val identifier = stringFromPsiIdentifier(identifierPsi) ?: return null

    val type = nodeBlock.type
        ?.let { nodeTypeFromPsi(it) }

    val children: List<KdlNode> = nodeBlock.nodeChildren
        ?.nodeBlockList
        ?.map { nodeFromPsiNodeBlock(it) ?: return null }
        ?: return null

    val args: List<KdlArgument> = nodeBlock.nodePropOrArgList
        .mapNotNull { it.arg }
        .map { nodeArgFromPsi(it) ?: return null }

    val properties: Map<String, KdlTypedValue> = nodeBlock.nodePropOrArgList
        .mapNotNull { it.prop }
        .map { nodePropFromPsi(it) ?: return null }
        .toMap()

    return KdlNode(identifier, type, children, properties, args)
}

private fun nodeArgFromPsi(nodeArg: KdlPsiArg): KdlArgument? {
    val kdlValue = nodeValueFromPsi(nodeArg.value) ?: return null
    return KdlArgument(kdlValue)
}

private fun nodePropFromPsi(nodeProp: KdlPsiProp): Pair<String, KdlTypedValue>? {
    val kdlTypedValue = nodeValueFromPsi(nodeProp.value) ?: return null
    val identifier = stringFromPsiIdentifier(nodeProp.identifier) ?: return null
    return identifier to kdlTypedValue
}

private fun nodeValueFromPsi(nodeValue: KdlPsiValue): KdlTypedValue? {
    val type = nodeValue.type
        ?.let { nodeTypeFromPsi(it) }

    val kind = nodeValue.literal
        .let { KdlLiteralKind.fromAstNode(it.node) }

    val value = when (kind) {
        is KdlLiteralKind.BooleanLiteral -> KdlBooleanValue(kind.value)
        is KdlLiteralKind.NullLiteral -> KdlNullValue
        is KdlLiteralKind.NumberLiteral -> {
            kind.value
                ?.let { KdlNumberValue(it) }
                ?: return null
        }
        is StringLiteral -> KdlStringValue(kind.value)
        null -> return null
    }
    return KdlTypedValue(type, value)
}

private fun nodeTypeFromPsi(nodeType: KdlPsiType): KdlType? {
    return stringFromPsiIdentifier(nodeType.identifier)
        ?.let {
            KdlType(it)
        }
}

private fun stringFromPsiIdentifier(identifier: KdlPsiIdentifier): String? {
    identifier.string?.let {
        return StringLiteral(it.node).value
    }

    return identifier.bareIdentifier?.node?.text
}