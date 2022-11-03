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
            fillMap(ATTRIBUTES, FkTokenTypeSets.KEY_WORDS, FkColors.KEYWORD.textAttributesKey)

            ATTRIBUTES[FkElementTypes.IDENTIFIER] = FkColors.IDENTIFIER.textAttributesKey

            ATTRIBUTES[FkElementTypes.LBRACE] = DefaultLanguageHighlighterColors.BRACES;
            ATTRIBUTES[FkElementTypes.RBRACE] = DefaultLanguageHighlighterColors.BRACES;

            ATTRIBUTES[FkElementTypes.COMMA] = DefaultLanguageHighlighterColors.COMMA;
            ATTRIBUTES[FkElementTypes.COLON] = DefaultLanguageHighlighterColors.COMMA;

            ATTRIBUTES[FkElementTypes.SEMICOLON] = DefaultLanguageHighlighterColors.SEMICOLON;

            ATTRIBUTES[FkElementTypes.RARROW] = FkColors.CONSTANT.textAttributesKey;
            ATTRIBUTES[FkElementTypes.LARROW] = FkColors.CONSTANT.textAttributesKey;
            ATTRIBUTES[FkElementTypes.DARROW] = FkColors.CONSTANT.textAttributesKey;

            ATTRIBUTES[FkElementTypes.STRING_LITERAL] = FkColors.STRING.textAttributesKey;
            ATTRIBUTES[FkElementTypes.AUTHORIZATION_VALUE] = FkColors.STRING.textAttributesKey;
            ATTRIBUTES[FkElementTypes.ENTRY_KEY] = FkColors.STRING.textAttributesKey;

            ATTRIBUTES[FkElementTypes.COMMENT] = FkColors.COMMENT.textAttributesKey;
            ATTRIBUTES[FkElementTypes.BLOCK_COMMENT] = FkColors.COMMENT.textAttributesKey;
            ATTRIBUTES[FkParserDefinition.INLINE_DOC] = FkColors.COMMENT.textAttributesKey;

            ATTRIBUTES[FkElementTypes.INCLUDE_KEYWORD] = FkColors.INCLUDE.textAttributesKey;

            ATTRIBUTES[FkElementTypes.INTEGER_LITERAL] = FkColors.NUMBER.textAttributesKey;
        }
    }
}