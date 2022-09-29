package com.feakin.intellij.ide.navigate

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.psi.stubs.FkContextIndex
import com.intellij.lang.Language
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.GotoClassContributor
import com.intellij.navigation.NavigationItem
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter

class FkDomainObjectNavigationContributor : GotoClassContributor, ChooseByNameContributorEx {
    override fun processNames(processor: Processor<in String?>, scope: GlobalSearchScope, filter: IdFilter?) {
        if (!StubIndex.getInstance()
                .processAllKeys(FkContextIndex.KEY, processor, scope, filter)
        ) {
            return
        }
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
    }

    override fun getQualifiedName(item: NavigationItem): String? {
        return null
    }

    override fun getQualifiedNameSeparator(): String? {
        return null
    }

    override fun getElementLanguage(): Language {
        return FkLanguage
    }
}