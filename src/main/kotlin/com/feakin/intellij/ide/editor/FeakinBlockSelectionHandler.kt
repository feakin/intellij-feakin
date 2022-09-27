package com.feakin.intellij.ide.editor

import com.feakin.intellij.lexer.FeakinElementTypes
import com.feakin.intellij.psi.FeakinContextBody
import com.feakin.intellij.psi.FeakinContextMapBody
import com.intellij.codeInsight.editorActions.wordSelection.AbstractWordSelectioner
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiUtilCore

class FeakinBlockSelectionHandler : AbstractWordSelectioner() {
    override fun canSelect(e: PsiElement): Boolean {
        return e is FeakinContextBody || e is FeakinContextMapBody
    }

    override fun select(e: PsiElement, editorText: CharSequence, cursorOffset: Int, editor: Editor): List<TextRange>? {
        val startNode =
            e.childrenWithLeaves.firstOrNull { it.elementType == FeakinElementTypes.LBRACE }
                ?.rightSiblings?.firstOrNull { it !is PsiWhiteSpace }
                ?: return null

        val endNode =
            startNode.rightSiblings.firstOrNull { it.elementType == FeakinElementTypes.RBRACE }
                ?.leftSiblings?.firstOrNull { it !is PsiWhiteSpace }
                ?: return null

        val startOffset = startNode.startOffset
        val endOffset = endNode.endOffset
        if (startOffset >= endOffset) return null

        val range = TextRange.create(startOffset, endOffset)
        return expandToWholeLine(editorText, range)
    }

}

/*
 * Use of this source code is governed by the MIT license that can be
 * Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
 * Copyright (c) 2016 JetBrains
 * found in the LICENSE file.
 */

val PsiElement.startOffset: Int get() = textRange.startOffset

val PsiElement.endOffset: Int get() = textRange.endOffset

val PsiElement.rightSiblings: Sequence<PsiElement> get() = generateSequence(this.nextSibling) { it.nextSibling }

val PsiElement.leftSiblings: Sequence<PsiElement> get() = generateSequence(this.prevSibling) { it.prevSibling }

val PsiElement.elementTypeOrNull: IElementType? get() = PsiUtilCore.getElementType(this)

val PsiElement.elementType: IElementType get() = elementTypeOrNull!!

val PsiElement.childrenWithLeaves: Sequence<PsiElement> get() = generateSequence(this.firstChild) { it.nextSibling }
