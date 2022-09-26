package com.feakin.intellij;

import com.intellij.lang.Language;

public class FeakinLanguage extends Language {

    public static final FeakinLanguage INSTANCE = new FeakinLanguage();

    public static FeakinLanguage getInstance() {
        return INSTANCE;
    }

    private FeakinLanguage() {
        super("Feakin");
    }

}
