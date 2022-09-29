package com.feakin.intellij.structure

import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.TreeAnchorizer
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class FkStructureViewElement(private val element: NavigatablePsiElement) : StructureViewTreeElement {
    companion object {
        private val log: Logger = logger<FkStructureViewElement>()
    }

    private val psiAnchor = TreeAnchorizer.getService().createAnchor(element)

    private val psi: PsiElement? get() = TreeAnchorizer.getService().retrieveElement(psiAnchor) as? PsiElement

    override fun navigate(requestFocus: Boolean) {
        (psi as? Navigatable)?.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = (psi as? Navigatable)?.canNavigate() == true

    override fun canNavigateToSource(): Boolean = (psi as? Navigatable)?.canNavigateToSource() == true

    override fun getValue(): PsiElement? = psi

    override fun getPresentation(): ItemPresentation {
        val struct = PresentationData()
        struct.setIcon(null)
        log.warn(psi?.javaClass?.name)

        when (val psi = psi) {
            is FeakinContextMapDeclaration -> {
                val structNameDeclaration = psi.contextMapName
                struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
                struct.presentableText = structNameDeclaration.text
                return struct
            }

            is FeakinContextDeclaration -> {
                val structNameDeclaration = psi.contextName
                struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
                struct.presentableText = structNameDeclaration.text
                return struct
            }

            else -> {
                val presentation = element.presentation
                return presentation ?: PresentationData()
            }
        }
    }

    override fun getChildren(): Array<TreeElement> {
        log.warn(psi?.javaClass?.name)
        return when (val psi = psi) {
            is FeakinContextMapDeclaration -> {
                PsiTreeUtil.getChildrenOfTypeAsList(psi, FeakinContextMapDeclaration::class.java)
            }

            is FeakinContextDeclaration -> {
                PsiTreeUtil.getChildrenOfTypeAsList(psi, FeakinContextDeclaration::class.java)
            }

            else -> {
                emptyList()
            }
        }.map(::FkStructureViewElement).toTypedArray()
    }
}