package com.feakin.intellij.highlight

import com.feakin.intellij.FeakinLexerAdapter
import com.feakin.intellij.lexer.FeakinTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class FeakinSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return FeakinLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(ATTRIBUTES[tokenType])
    }

    companion object {
        private val ATTRIBUTES: MutableMap<IElementType, TextAttributesKey> = HashMap()

        init {
            fillMap(ATTRIBUTES, FeakinTokenTypeSets.KEY_WORDS, DefaultLanguageHighlighterColors.KEYWORD)
            ATTRIBUTES[FeakinTypes.IDENTIFIER] = DefaultLanguageHighlighterColors.IDENTIFIER

            ATTRIBUTES[FeakinTypes.COMMENT] = DefaultLanguageHighlighterColors.LINE_COMMENT;
            ATTRIBUTES[FeakinTypes.CLOSE_BRACE] = DefaultLanguageHighlighterColors.BRACES;
            ATTRIBUTES[FeakinTypes.OPEN_BRACE] = DefaultLanguageHighlighterColors.BRACES;

            ATTRIBUTES[FeakinTypes.STRING_LITERAL] = DefaultLanguageHighlighterColors.STRING;
        }
    }
}