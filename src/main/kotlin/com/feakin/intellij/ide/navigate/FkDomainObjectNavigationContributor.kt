package com.feakin.intellij.ide.navigate

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinNamedElement
import com.feakin.intellij.psi.stubs.FkNamedElementIndex
import com.intellij.lang.Language
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.GotoClassContributor
import com.intellij.navigation.NavigationItem
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter

class FkDomainObjectNavigationContributor : GotoClassContributor, ChooseByNameContributorEx {
    private val indexKey: StubIndexKey<String, FeakinNamedElement> = FkNamedElementIndex.KEY

    override fun processNames(processor: Processor<in String?>, scope: GlobalSearchScope, filter: IdFilter?) {
        StubIndex.getInstance().processAllKeys(
            indexKey,
            processor,
            scope,
            null
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
            FeakinNamedElement::class.java
        ) { element ->
            processor.process(element)
        }
    }

    override fun getQualifiedName(item: NavigationItem): String? = (item as? FeakinNamedElement)?.name

    override fun getQualifiedNameSeparator(): String? {
        return null
    }

    override fun getElementLanguage(): Language {
        return FkLanguage
    }
}