package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.feakin.intellij.lexer.FkElementTypes.IDENTIFIER
import com.feakin.intellij.psi.*
import com.feakin.intellij.resolve.ref.CONTEXT_NAME_INFERENCE_KEY
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.feakin.intellij.resolve.ref.InferenceResult
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.PsiTreeUtil

class FkAggregateDeclReferenceImpl(
    element: FkAggregateDeclaration
) : FkReferenceBase<FkAggregateDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return element.aggregateInference
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkAggregateDeclaration && super.isReferenceTo(element)
    }
}

val FkElement.aggregateInference: InferenceResult
    get() = CachedValuesManager.getCachedValue(this, CONTEXT_NAME_INFERENCE_KEY) {
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

        val contextDeclarations: Array<out FkContextDeclaration>? = fkDeclarations?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkContextDeclaration::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val mapBodies: Array<out FkContextBody>? = contextDeclarations?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkContextBody::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val usedAggregates: Array<out FkUseAggregate>? = mapBodies?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkUseAggregate::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        usedAggregates?.forEach { aggregate ->
            aggregate.children.forEach { child ->
                if (child.node.elementType == IDENTIFIER && child.text == element.name) {
                    collection.add(aggregate)
                }
            }
        }
    }

    return collection
}
