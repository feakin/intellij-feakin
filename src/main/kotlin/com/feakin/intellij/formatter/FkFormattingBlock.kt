package com.feakin.intellij.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.formatter.FormatterUtil

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
        val toCollection = node.getChildren(null)
            .filter { !it.isWhitespaceOrEmpty() }
            .map { childNode: ASTNode ->
                childNode.elementType
            }
            .toCollection(ArrayList())

        println(toCollection)

        return listOf()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        return ChildAttributes(Indent.getNoneIndent(), null)
    }

    override fun isIncomplete(): Boolean = myIsIncomplete
    private val myIsIncomplete: Boolean by lazy { FormatterUtil.isIncomplete(node) }

    override fun isLeaf(): Boolean = node.firstChildNode == null

    override fun toString() = "${node.text} $textRange"
}

fun ASTNode?.isWhitespaceOrEmpty() = this == null || textLength == 0 || elementType == TokenType.WHITE_SPACE