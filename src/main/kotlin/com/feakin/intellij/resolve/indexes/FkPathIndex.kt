package com.feakin.intellij.resolve.indexes

import com.feakin.intellij.psi.FkPath
import com.intellij.openapi.project.Project
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkPathIndex: StringStubIndexExtension<FkPath>() {
    override fun getKey(): StubIndexKey<String, FkPath> = KEY

    companion object {
        fun findElementsByName(referenceName: String, project: Project) {
            println(referenceName)
        }

        val KEY: StubIndexKey<String, FkPath> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.index.FkPathIndex")
    }
}
