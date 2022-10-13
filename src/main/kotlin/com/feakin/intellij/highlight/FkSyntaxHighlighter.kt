package com.feakin.intellij.highlight

import com.feakin.intellij.colors.FkColors
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.lexer.FkLexer
import com.feakin.intellij.parser.FkParserDefinition
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class FkSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return FkLexer()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(ATTRIBUTES[tokenType])
    }

    companion object {
        private val ATTRIBUTES: MutableMap<IElementType, TextAttributesKey> = HashMap()

        init {
            fillMap(ATTRIBUTES, FkTokenTypeSets.KEY_WORDS, DefaultLanguageHighlighterColors.KEYWORD)
            ATTRIBUTES[FkElementTypes.IDENTIFIER] = DefaultLanguageHighlighterColors.LABEL

            ATTRIBUTES[FkElementTypes.LBRACE] = DefaultLanguageHighlighterColors.BRACES;
            ATTRIBUTES[FkElementTypes.RBRACE] = DefaultLanguageHighlighterColors.BRACES;

            ATTRIBUTES[FkElementTypes.COMMA] = DefaultLanguageHighlighterColors.COMMA;
            ATTRIBUTES[FkElementTypes.SEMICOLON] = DefaultLanguageHighlighterColors.SEMICOLON;

            ATTRIBUTES[FkElementTypes.COLON] = DefaultLanguageHighlighterColors.COMMA;

            ATTRIBUTES[FkElementTypes.RARROW] = DefaultLanguageHighlighterColors.CONSTANT;
            ATTRIBUTES[FkElementTypes.LARROW] = DefaultLanguageHighlighterColors.CONSTANT;
            ATTRIBUTES[FkElementTypes.DARROW] = DefaultLanguageHighlighterColors.CONSTANT;

            ATTRIBUTES[FkElementTypes.STRING_LITERAL] = DefaultLanguageHighlighterColors.STRING;
            ATTRIBUTES[FkElementTypes.AUTHORIZATION_VALUE] = DefaultLanguageHighlighterColors.STRING;

            ATTRIBUTES[FkElementTypes.ENTRY_KEY] = DefaultLanguageHighlighterColors.LOCAL_VARIABLE;

            ATTRIBUTES[FkElementTypes.COMMENT] = FkColors.COMMENT.textAttributesKey;
            ATTRIBUTES[FkElementTypes.BLOCK_COMMENT] = FkColors.COMMENT.textAttributesKey;
            ATTRIBUTES[FkParserDefinition.INLINE_DOC] = FkColors.COMMENT.textAttributesKey;

            ATTRIBUTES[FkElementTypes.INCLUDE_KEYWORD] = FkColors.INCLUDE.textAttributesKey;
        }
    }
}