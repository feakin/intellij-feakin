package com.feakin.intellij.runconfig

class FkCommandLine(
    val path: String,
    val impl: String,
    val subcommand: String,
) {
    fun toCommandString(): String {
        return this.toCommand().joinToString(" ")
    }

    fun toCommand(): List<String> {
        return listOf("fkl", subcommand, "--path", path, "--impl", impl)
    }
}