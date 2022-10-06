package com.feakin.intellij.psi.stubs


fun factory(name: String): FkStubElementType<*, *> = when (name) {
    "CONTEXT_MAP_DECLARATION" -> FkContextMapDeclStub.Type
    else -> {
        throw IllegalArgumentException("Unknown element type: $name")
    }
}