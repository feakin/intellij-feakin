package com.feakin.intellij.formatter

import com.feakin.intellij.lexer.FeakinElementTypes.*
import com.intellij.formatting.*
import com.intellij.formatting.alignment.AlignmentStrategy
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import java.util.*

open class FkFormattingBlock(
    private val myNode: ASTNode,
    private val myAlignment: Alignment?,
    private val myIndent: Indent?,
    private val myWrap: Wrap?,
    private val mySettings: CodeStyleSettings,
    private val spacingBuilder: SpacingBuilder
) : UserDataHolderBase(), ASTBlock {
    override fun getNode(): ASTNode {
        return myNode
    }

    override fun getTextRange(): TextRange {
        return myNode.textRange
    }

    override fun getWrap(): Wrap? {
        return myWrap
    }

    override fun getIndent(): Indent? {
        return myIndent
    }

    override fun getAlignment(): Alignment? {
        return myAlignment
    }

    override fun getSubBlocks(): List<Block> = mySubBlocks
    private val mySubBlocks: List<Block> by lazy { buildChildren() }

    private fun buildChildren(): List<Block> {
        val strategy: AlignmentStrategy.AlignmentPerTypeStrategy? = null
        val blocks = mutableListOf<Block>()
        var child: ASTNode? = myNode.firstChildNode
        while (child != null) {
            val childType = child.elementType
            if (child.textRange.length == 0) {
                child = child.treeNext
                continue
            }
            if (childType === TokenType.WHITE_SPACE) {
                child = child.treeNext
                continue
            }

            val substitutor = if (childType === BLOCK_COMMENT) COMMENT else childType
            val alignment = strategy?.getAlignment(substitutor)
            blocks.addAll(buildSubBlocks(child, alignment))
            child = child.treeNext
        }
        return Collections.unmodifiableList(blocks)
    }

    private fun buildSubBlocks(child: ASTNode, alignment: Alignment?): List<FkFormattingBlock> {
        // DECLARATION
        val children = child.getChildren(null)
        if (children.isEmpty()) {
            return listOf(FkFormattingBlock(child, alignment, Indent.getNoneIndent(), null, mySettings, spacingBuilder))
        }

        // all children DECLARATION
        val decl = child.firstChildNode
        decl?.elementType
            ?: return listOf(FkFormattingBlock(child, alignment, Indent.getNoneIndent(), null, mySettings, spacingBuilder))

        // declaration with children and body
//        val blocks = decl.getChildren(null)
//            .filter {
//                it.elementType == CONTEXT_MAP_BODY || it.elementType == CONTEXT_BODY || it.elementType == ENDPOINT_BODY || it.elementType == IMPL_BODY
//                        || it.elementType == REQUEST_BODY
//            }
//            .map {
//                val indent = Indent.getNormalIndent()
//                FkFormattingBlock(child, alignment, indent, null, mySettings, spacingBuilder)
//            }.toCollection(mutableListOf())

        return listOf()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        val childIndent = Indent.getNoneIndent()

        val parentType = myNode.elementType
        if (parentType == CONTEXT_MAP_BODY || parentType == CONTEXT_BODY || parentType == ENDPOINT_BODY || parentType == IMPL_BODY
            || parentType == REQUEST_BODY
        ) {
            return ChildAttributes(Indent.getNormalIndent(), null)
        }

        return ChildAttributes(childIndent, null)
    }

    override fun isIncomplete(): Boolean = false
//    private val myIsIncomplete: Boolean by lazy { FormatterUtil.isIncomplete(node) }

    override fun isLeaf(): Boolean = false

    override fun toString() = "${node.text} $textRange"
}