package com.feakin.intellij.runconfig.config;

/// path to the main file
abstract class BaseConfig<T>(
    open val commandName: String,
    open val main: String,
    open val sourceElement: T
) {
    open val implName: String = ""
    open val configurationName: String = "Gen $implName"

    open val fkCommandLine = FkCommandLine(main, implName, commandName, path = "")

    open fun fromImplDecl(decl: T, path: String): FkCommandLine {
        return FkCommandLine(path, implName, commandName, path = "")
    }
}
