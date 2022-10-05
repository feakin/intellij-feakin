package com.feakin.intellij.runconfig.config

class FkCommandLine(
    var path: String,
    var impl: String,
    private val subcommand: String,
) {
    fun toCommandString(): String {
        return this.toCommand().joinToString(" ")
    }

    fun toCommand(): List<String> {
        return listOf("fkl", subcommand, "--path", path, "--impl", impl)
    }
}