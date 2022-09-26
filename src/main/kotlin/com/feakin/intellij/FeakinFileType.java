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
        return "Feakin File";
    }

    @Override
    public @NotNull String getDescription() {
        return "Feakin language file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "fkl";
    }

    @Override
    public @Nullable Icon getIcon() {
        return FeakinIcons.FILE;
    }
}
