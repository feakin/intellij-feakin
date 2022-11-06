package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.feakin.intellij.psi.*
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.feakin.intellij.resolve.ref.InferenceResult
import com.feakin.intellij.resolve.ref.VALUE_OBJECT_NAME_INFERENCE_KEY
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.PsiTreeUtil

class FkValueObjectDeclReferenceImpl(
    element: FkValueObjectDeclaration
) : FkReferenceBase<FkValueObjectDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return element.voInference
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkValueObjectDeclaration && super.isReferenceTo(element)
    }
}

val FkElement.voInference: InferenceResult
    get() = CachedValuesManager.getCachedValue(this, VALUE_OBJECT_NAME_INFERENCE_KEY) {
        CachedValueProvider.Result.create(
            inferVoName(this as FkNamedElement),
            PsiModificationTracker.MODIFICATION_COUNT
        )
    }

private fun inferVoName(
    element: FkNamedElement
): InferenceResult {
    val project = element.project
    val scope = GlobalSearchScope.allScope(project)
    val collection = mutableListOf<FkElement>()

    val virtualFiles: Collection<VirtualFile> = FileTypeIndex.getFiles(FkFileType, scope)
    virtualFiles.forEach { virtualFile ->
        val fkFile = PsiManager.getInstance(project).findFile(virtualFile) as FkFile
        val fkDeclarations = PsiTreeUtil.getChildrenOfType(fkFile, FkDeclaration::class.java)

        val entities: Array<out FkEntityDeclaration>? = fkDeclarations?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkEntityDeclaration::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val bodies: Array<out FkEntityBody>? = entities?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkEntityBody::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        val uses: Array<out FkUseValueObject>? = bodies?.mapNotNull {
            PsiTreeUtil.getChildrenOfType(it, FkUseValueObject::class.java)?.toList()
        }?.flatten()?.toTypedArray()

        uses?.forEach { declaration ->
            PsiTreeUtil.getChildrenOfType(declaration, FkUseValueObjectName::class.java)
                ?.forEach { contextName ->
                    if (contextName.text == element.name) {
                        collection.add(contextName)
                    }
                }
        }
    }

    return collection
}
