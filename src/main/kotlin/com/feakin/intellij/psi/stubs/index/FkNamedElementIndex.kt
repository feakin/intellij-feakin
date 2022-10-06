package com.feakin.intellij.psi.stubs.index

import com.feakin.intellij.psi.FkNamedElement
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey

class FkNamedElementIndex : StringStubIndexExtension<FkNamedElement>() {
    private val VERSION = 0
    override fun getVersion(): Int {
        return super.getVersion() + VERSION
    }

    override fun getKey(): StubIndexKey<String, FkNamedElement> = KEY

    companion object {

        val KEY: StubIndexKey<String, FkNamedElement> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.stubs.FeakinNamedElementIndex")

        fun findElementsByName(
            target: String,
            project: Project,
            resolveScope: GlobalSearchScope,
        ): Collection<FkNamedElement> {
            return getElements(KEY, target, project, resolveScope)
        }
    }
}

inline fun <Key, reified Psi : PsiElement> getElements(
    indexKey: StubIndexKey<Key, Psi>,
    key: Key,
    project: Project,
    scope: GlobalSearchScope?
): Collection<Psi> =
    StubIndex.getElements(indexKey, key, project, scope, Psi::class.java)

