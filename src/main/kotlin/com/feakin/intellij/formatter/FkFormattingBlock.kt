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
            blocks.add(buildSubBlock(child, alignment))
            child = child.treeNext
        }
        return Collections.unmodifiableList(blocks)
    }

    private fun buildSubBlock(child: ASTNode, alignment: Alignment?): FkFormattingBlock {
//        val children = node.getChildren(null)
//            .filter { !(it == null || it.textLength == 0 || it.elementType == TokenType.WHITE_SPACE) }
//            .map { childNode: ASTNode ->
//
//            }
        val indent = Indent.getNoneIndent()
        return FkFormattingBlock(child, alignment, indent, null, mySettings, spacingBuilder)
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        val childIndent = Indent.getNoneIndent()

        val parentType = myNode.elementType
        if (parentType == CONTEXT_DECLARATION) {
            return ChildAttributes(Indent.getNormalIndent(), null)
        }

        return ChildAttributes(childIndent, null)
    }

    override fun isIncomplete(): Boolean = false
//    private val myIsIncomplete: Boolean by lazy { FormatterUtil.isIncomplete(node) }

    override fun isLeaf(): Boolean = false

    override fun toString() = "${node.text} $textRange"
}