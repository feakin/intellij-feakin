package com.feakin.intellij.highlight

import com.feakin.intellij.lexer.FeakinLexerAdapter
import com.feakin.intellij.lexer.FeakinElementTypes
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
            ATTRIBUTES[FeakinElementTypes.IDENTIFIER] = DefaultLanguageHighlighterColors.LABEL

            ATTRIBUTES[FeakinElementTypes.COMMENT] = DefaultLanguageHighlighterColors.LINE_COMMENT;
            ATTRIBUTES[FeakinElementTypes.BLOCK_COMMENT] = DefaultLanguageHighlighterColors.BLOCK_COMMENT;

            ATTRIBUTES[FeakinElementTypes.LBRACE] = DefaultLanguageHighlighterColors.BRACES;
            ATTRIBUTES[FeakinElementTypes.RBRACE] = DefaultLanguageHighlighterColors.BRACES;

            ATTRIBUTES[FeakinElementTypes.COMMA] = DefaultLanguageHighlighterColors.COMMA;
            ATTRIBUTES[FeakinElementTypes.SEMICOLON] = DefaultLanguageHighlighterColors.SEMICOLON;

            ATTRIBUTES[FeakinElementTypes.COLON] = DefaultLanguageHighlighterColors.COMMA;

            ATTRIBUTES[FeakinElementTypes.RARROW] = DefaultLanguageHighlighterColors.CONSTANT;
            ATTRIBUTES[FeakinElementTypes.LARROW] = DefaultLanguageHighlighterColors.CONSTANT;
            ATTRIBUTES[FeakinElementTypes.DARROW] = DefaultLanguageHighlighterColors.CONSTANT;

            ATTRIBUTES[FeakinElementTypes.STRING_LITERAL] = DefaultLanguageHighlighterColors.STRING;
            ATTRIBUTES[FeakinElementTypes.AUTHORIZATION_VALUE] = DefaultLanguageHighlighterColors.STRING;
        }
    }
}