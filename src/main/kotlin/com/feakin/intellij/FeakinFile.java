package com.feakin.intellij;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class FeakinFile extends PsiFileBase {

    public FeakinFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, FeakinLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return FeakinFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Feakin File";
    }

}
