package com.feakin.intellij.stubs

fun factory(name: String): FkStubElementType<*, *> = when (name) {
    "PATH" -> FkPathStub.Type
    "CONTEXT_MAP_DECLARATION" -> FkContextMapDeclStub.Type
    "CONTEXT_DECLARATION" -> FkContextDeclStub.Type
    else -> {
        throw IllegalArgumentException("Unknown element type: $name")
    }
}