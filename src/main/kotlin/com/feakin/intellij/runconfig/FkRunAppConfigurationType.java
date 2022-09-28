package com.feakin.intellij.runconfig;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.icons.AllIcons;

public class FkRunAppConfigurationType extends ConfigurationTypeBase {
    public FkRunAppConfigurationType() {
        super("FkRunAppConfigType", "Run Generator", "", AllIcons.RunConfigurations.TestState.Run);
    }
}
