package com.feakin.intellij.highlight;

import com.feakin.intellij.FeakinLexerAdapter;
import com.feakin.intellij.lexer.FeakinTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FeakinSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static {
        fillMap(ATTRIBUTES, FeakinTokenTypeSets.KEY_WORDS, DefaultLanguageHighlighterColors.KEYWORD);

        ATTRIBUTES.put(FeakinTypes.IDENTIFIER, DefaultLanguageHighlighterColors.IDENTIFIER);
    }

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new FeakinLexerAdapter();
    }

    @Override
    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}
