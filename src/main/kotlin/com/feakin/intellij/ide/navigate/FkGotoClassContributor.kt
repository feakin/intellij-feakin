package com.feakin.intellij.ide.navigate

import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FkNamedElement
import com.feakin.intellij.psi.index.FkGotoClassIndex
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.GotoClassContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey
import com.intellij.util.ArrayUtil
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter

class FkGotoClassContributor : GotoClassContributor, ChooseByNameContributorEx {

    private val indexKey: StubIndexKey<String, FeakinContextMapDeclaration> = FkGotoClassIndex.KEY

    override fun processNames(processor: Processor<in String>, scope: GlobalSearchScope, filter: IdFilter?) {
        StubIndex.getInstance().processAllKeys(
            indexKey,
            processor,
            scope,
            null
        )
    }

    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> {
        return ArrayUtil.toStringArray(
            StubIndex.getInstance().getAllKeys(
                FkGotoClassIndex.KEY,
                project!!
            )
        )
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
        StubIndex.getInstance().processElements(
            indexKey,
            name,
            parameters.project,
            parameters.searchScope,
            null,
            FeakinContextMapDeclaration::class.java
        ) { element ->
            processor.process(element)
        }
    }

    override fun getQualifiedName(item: NavigationItem): String? = (item as? FkNamedElement)?.name

    override fun getQualifiedNameSeparator(): String = ":"

    override fun getItemsByName(
        name: String,
        pattern: String,
        project: Project,
        includeNonProjectItems: Boolean
    ): Array<NavigationItem> {
        val result = ArrayList<NavigationItem>()
        val params = FindSymbolParameters("", "", FindSymbolParameters.searchScopeFor(project, includeNonProjectItems))
        processElementsWithName(name, { result.add(it) }, params)
        return if (result.isEmpty()) NavigationItem.EMPTY_NAVIGATION_ITEM_ARRAY else result.toTypedArray()
    }
}