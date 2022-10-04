package com.feakin.intellij.runconfig

class FkCommandLine(
    val path: String,
    val impl: String,
) {
    fun toCommand(): String {
        val commands: List<String> = listOf(
            "--path", path, "--impl", impl
        )

        return commands.joinToString(" ")
    }
}