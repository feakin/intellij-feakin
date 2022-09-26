package com.feakin.intellij;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FeakinFileType extends LanguageFileType {
    public static final FeakinFileType INSTANCE = new FeakinFileType();

    private FeakinFileType() {
        super(FeakinLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "Charj File";
    }

    @Override
    public @NotNull String getDescription() {
        return "Charj language file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "cj";
    }

    @Override
    public @Nullable Icon getIcon() {
        return FeakinIcons.FILE;
    }
}
