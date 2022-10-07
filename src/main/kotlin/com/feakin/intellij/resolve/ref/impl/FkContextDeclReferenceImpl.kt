package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.feakin.intellij.psi.*
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.*

class FkContextDeclReferenceImpl(
    element: FkContextDeclaration
) : FkReferenceBase<FkContextDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return element.inference
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkContextDeclaration && super.isReferenceTo(element)
    }
}

private val CONTEXT_NAME_INFERENCE_KEY: Key<CachedValue<List<FkElement>>> = Key.create("CONTEXT_NAME_INFERENCE_KEY")

typealias InferenceResult = List<FkElement>;

val FkElement.inference: InferenceResult
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

        val contextMaps: Array<out FkContextMapDeclaration>? = fkDeclarations?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkContextMapDeclaration::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val mapBodies: Array<out FkContextMapBody>? = contextMaps?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkContextMapBody::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        mapBodies?.forEach { contextMapDeclaration ->
            PsiTreeUtil.getChildrenOfType(contextMapDeclaration, FkContextName::class.java)
                ?.forEach { contextName ->
                    if (contextName.text == element.name) {
                        collection.add(contextName)
                    }
                }
        }
    }
    return collection
}
