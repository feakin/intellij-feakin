package com.feakin.intellij.stubs

fun factory(name: String): FkStubElementType<*, *> = when (name) {
    "PATH" -> FkPathStub.Type
    "LAYER_DECLARATION" -> FkLayerDeclarationStub.Type
    "DEP_NODE" -> FkDepNodeStub.Type
    "CONTEXT_MAP_DECLARATION" -> FkContextMapDeclStub.Type
    "CONTEXT_DECLARATION" -> FkContextDeclStub.Type
    "AGGREGATE_DECLARATION" -> FkAggregateDeclStub.Type
    else -> {
        throw IllegalArgumentException("Unknown element type: $name")
    }
}