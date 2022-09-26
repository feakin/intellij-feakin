package com.feakin.intellij.lexer

import com.feakin.intellij.FeakinLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class FeakinTokenType(debugName: @NonNls String) : IElementType(debugName, FeakinLanguage.INSTANCE) {
    override fun toString(): String {
        return "FeakinTokenType." + super.toString()
    }
}