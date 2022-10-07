package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.feakin.intellij.psi.*
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil

class FkContextDeclReferenceImpl(
    element: FkContextDeclaration
) : FkReferenceBase<FkContextDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
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

            val mapBodys: Array<out FkContextMapBody>? = contextMaps?.mapNotNull {
                PsiTreeUtil.getChildrenOfType(it, FkContextMapBody::class.java)?.toList()
            }?.flatten()?.toTypedArray()

            mapBodys?.forEach { contextMapDeclaration ->
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

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkContextDeclaration && super.isReferenceTo(element)
    }
}

