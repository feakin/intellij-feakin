package com.feakin.intellij.ide

import com.feakin.intellij.lexer.FkElementTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class FkBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> {
        return BRACE_PAIRS
    }

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
        return false
    }

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int {
        return openingBraceOffset
    }

    companion object {
        private val BRACE_PAIRS = arrayOf(
            BracePair(FkElementTypes.LBRACE, FkElementTypes.RBRACE, true)
        )
    }
}