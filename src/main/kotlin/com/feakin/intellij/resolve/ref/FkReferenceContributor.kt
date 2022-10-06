package com.feakin.intellij.resolve.ref

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.FkNamedElement
import com.feakin.intellij.psi.stubs.index.FkNamedElementIndex
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class FkReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(psiElement(FkContextName::class.java), FkContextReferenceProvider())
    }
}

class FkContextReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        val contextDeclaration = element as FkContextDeclaration
        val contextName = contextDeclaration.name ?: return PsiReference.EMPTY_ARRAY

        val contextReferences = mutableListOf<PsiReference>()

        FkNamedElementIndex.findElementsByName(contextName, element.project, element.resolveScope).forEach {
            contextReferences.add(FkContextReference(contextDeclaration, it))
        }

        return contextReferences.toTypedArray()
    }
}

class FkContextReference(element: FkContextDeclaration, contextDeclaration: FkNamedElement) :
    PsiReferenceBase<FkContextDeclaration>(element) {
    private val contextDeclaration = contextDeclaration

    override fun resolve(): PsiElement {
        return contextDeclaration
    }

    override fun getVariants(): Array<Any> {
        return FkNamedElementIndex.findElementsByName("", element.project, element.resolveScope).toTypedArray()
    }
}