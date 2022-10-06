package com.feakin.intellij.ide.navigate

import com.feakin.intellij.psi.FkNamedElement
import com.feakin.intellij.psi.stubs.index.FkNamedElementIndex
import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.util.ArrayUtil

class FkGotoSymbolContributor : ChooseByNameContributor {
    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> {
        return ArrayUtil.toStringArray(
            StubIndex.getInstance().getAllKeys(
                FkNamedElementIndex.KEY,
                project!!
            )
        )
    }

    override fun getItemsByName(
        name: String,
        pattern: String,
        project: Project,
        includeNonProjectItems: Boolean
    ): Array<NavigationItem> {
        val scope = if (includeNonProjectItems) GlobalSearchScope.allScope(project) else GlobalSearchScope.projectScope(project)

        val result: Collection<FkNamedElement> = StubIndex.getElements(
            FkNamedElementIndex.KEY, name,
            project, scope,
            FkNamedElement::class.java
        )
        val items: MutableList<NavigationItem> = ArrayList(result.size)
        for (element in result) {
            items.add(element)
        }
        return items.toTypedArray()
    }

}