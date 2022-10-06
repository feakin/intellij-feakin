package com.feakin.intellij.ide;

import com.feakin.intellij.lexer.FkElementTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FkBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] BRACE_PAIRS = {
            new BracePair(FkElementTypes.LBRACE, FkElementTypes.RBRACE, true),
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return BRACE_PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return false;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
