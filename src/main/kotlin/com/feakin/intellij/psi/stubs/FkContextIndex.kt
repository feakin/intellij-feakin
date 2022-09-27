package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FeakinContextDeclaration
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey

// todo: change to generic
class FkContextIndex : StringStubIndexExtension<FeakinContextDeclaration>() {
    override fun getKey(): StubIndexKey<String, FeakinContextDeclaration> {
        return KEY
    }

    companion object {
        val KEY = StubIndexKey.createIndexKey<String, FeakinContextDeclaration>("feakin.declaration.shortName")
        fun allKeys(project: Project?): Collection<String> {
            return StubIndex.getInstance().getAllKeys(KEY, project!!)
        }

        fun find(name: String, project: Project?, scope: GlobalSearchScope?): Collection<FeakinContextDeclaration> {
            return StubIndex.getElements(KEY, name, project!!, scope, FeakinContextDeclaration::class.java)
        }

        fun find(
            name: String,
            project: Project?,
            includeNonProjectItems: Boolean
        ): Collection<FeakinContextDeclaration> {
            val scope = GlobalSearchScope.projectScope(
                project!!
            )
            return find(name, project, scope)
        }
    }
}