package com.feakin.intellij.formatter

import com.feakin.intellij.lexer.FeakinElementTypes.LBRACE
import com.feakin.intellij.lexer.FeakinElementTypes.RBRACE
import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.FormatterUtil

open class FkFormattingBlock(
    private val myNode: ASTNode,
    private val myAlignment: Alignment?,
    private val myIndent: Indent?,
    private val myWrap: Wrap?,
    private val ctx: FkFmtContext,
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
        val children = node.getChildren(null)
            .filter { !it.isWhitespaceOrEmpty() }
            .map { childNode: ASTNode ->
                val childCtx = ctx.copy()

                FkFormattingModelBuilder.createBlock(
                    node = childNode,
                    alignment = null,
                    indent = computeIndent(childNode),
                    wrap = null,
                    ctx = childCtx
                )
            }
            .toCollection(ArrayList())

        return children
    }

    private fun computeIndent(child: ASTNode): Indent {
        val childType = child.elementType

        return when {
            node.isDelimitedBlock -> getIndentIfNotDelim(child, node)
            childType == TokenType.WHITE_SPACE -> Indent.getNoneIndent()
            else -> Indent.getNoneIndent()
        }
    }

    // todo: add more logic for spacing
    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return ctx.spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        return ChildAttributes(Indent.getNoneIndent(), null)
    }

    override fun isIncomplete(): Boolean = myIsIncomplete
    private val myIsIncomplete: Boolean by lazy { FormatterUtil.isIncomplete(node) }

    override fun isLeaf(): Boolean = node.firstChildNode == null

    override fun toString() = "${node.text} $textRange"
}

/*
 *
 * Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
 * Copyright (c) 2016 JetBrains
 *
 */
fun ASTNode?.isWhitespaceOrEmpty() = this == null || textLength == 0 || elementType == TokenType.WHITE_SPACE


private val ASTNode.isDelimitedBlock: Boolean get() = elementType in BLOCK_LIKE

private fun getIndentIfNotDelim(child: ASTNode, parent: ASTNode): Indent =
    if (child.isBlockDelim(parent)) {
        Indent.getNoneIndent()
    } else {
        Indent.getNormalIndent()
    }

fun ASTNode.isBlockDelim(parent: ASTNode?): Boolean {
    if (parent == null) return false
    val parentType = parent.elementType
    return when (elementType) {
        LBRACE, RBRACE -> parentType in BLOCK_LIKE
        else -> false
    }
}
