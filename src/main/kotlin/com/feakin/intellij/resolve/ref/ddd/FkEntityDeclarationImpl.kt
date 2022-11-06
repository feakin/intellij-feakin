package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.feakin.intellij.psi.*
import com.feakin.intellij.resolve.ref.CONTEXT_NAME_INFERENCE_KEY
import com.feakin.intellij.resolve.ref.ENTITY_NAME_INFERENCE_KEY
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.feakin.intellij.resolve.ref.InferenceResult
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.*

class FkEntityDeclReferenceImpl(
    element: FkEntityDeclaration
) : FkReferenceBase<FkEntityDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return element.entityInference
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkContextDeclaration && super.isReferenceTo(element)
    }
}

val FkElement.entityInference: InferenceResult
    get() = CachedValuesManager.getCachedValue(this, ENTITY_NAME_INFERENCE_KEY) {
        CachedValueProvider.Result.create(
            inferContextName(this as FkNamedElement),
            PsiModificationTracker.MODIFICATION_COUNT
        )
    }


private fun inferContextName(
    element: FkNamedElement
): InferenceResult {
    val project = element.project
    val scope = GlobalSearchScope.allScope(project)
    val collection = mutableListOf<FkElement>()

    val virtualFiles: Collection<VirtualFile> = FileTypeIndex.getFiles(FkFileType, scope)
    virtualFiles.forEach { virtualFile ->
        val fkFile = PsiManager.getInstance(project).findFile(virtualFile) as FkFile
        val fkDeclarations = PsiTreeUtil.getChildrenOfType(fkFile, FkDeclaration::class.java)

        val aggregates: Array<out FkAggregateDeclaration>? = fkDeclarations?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkAggregateDeclaration::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val bodies: Array<out FkAggregateBody>? = aggregates?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkAggregateBody::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val entites: Array<out FkUseEntity>? = bodies?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkUseEntity::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        entites?.forEach { aggregate ->
            PsiTreeUtil.getChildrenOfType(aggregate, FkUseEntityName::class.java)
                ?.forEach { contextName ->
                    if (contextName.text == element.name) {
                        collection.add(contextName)
                    }
                }
        }
    }

    return collection
}
