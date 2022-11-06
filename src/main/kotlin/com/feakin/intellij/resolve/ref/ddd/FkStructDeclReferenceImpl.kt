package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkNamedElement
import com.feakin.intellij.psi.FkStructDeclaration
import com.feakin.intellij.psi.FkValueObjectDeclaration
import com.feakin.intellij.resolve.ref.CONTEXT_NAME_INFERENCE_KEY
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.feakin.intellij.resolve.ref.InferenceResult
import com.intellij.psi.PsiElement
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker

class FkStructDeclReferenceImpl(
    element: FkStructDeclaration
) : FkReferenceBase<FkStructDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return element.structInference
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkStructDeclaration && super.isReferenceTo(element)
    }
}

val FkElement.structInference: InferenceResult
    get() = CachedValuesManager.getCachedValue(this, CONTEXT_NAME_INFERENCE_KEY) {
        CachedValueProvider.Result.create(
            inferVoName(this as FkNamedElement),
            PsiModificationTracker.MODIFICATION_COUNT
        )
    }

private fun inferVoName(
    element: FkNamedElement
): InferenceResult {
//    val project = element.project
//    val scope = GlobalSearchScope.allScope(project)
    val collection = mutableListOf<FkElement>()

    return collection
}
