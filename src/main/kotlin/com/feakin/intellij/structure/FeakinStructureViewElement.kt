package com.feakin.intellij.structure

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FeakinNamedElement
import com.feakin.intellij.psi.impl.FeakinContextMapDeclarationImpl
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.TreeAnchorizer
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class FeakinStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {

    private val psiAnchor = TreeAnchorizer.getService().createAnchor(myElement)

    private val psi: PsiElement? get() = TreeAnchorizer.getService().retrieveElement(psiAnchor) as? PsiElement

    override fun navigate(requestFocus: Boolean) {
        (psi as? Navigatable)?.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = (psi as? Navigatable)?.canNavigate() == true

    override fun canNavigateToSource(): Boolean = (psi as? Navigatable)?.canNavigateToSource() == true

    override fun getValue(): PsiElement? = psi

    override fun getAlphaSortKey(): String {
        val name = myElement.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val struct = PresentationData()
        struct.setIcon(null)
        struct.locationString = null
        if (myElement is FeakinContextMapDeclaration) {
            val structNameDeclaration = myElement.contextMapName
            struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
            struct.presentableText = structNameDeclaration.text
            return struct
        } else if (myElement is FeakinContextDeclaration) {
            val structNameDeclaration = myElement.contextName
            struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
            struct.presentableText = structNameDeclaration.text
            return struct
        }
        val presentation = myElement.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is FkFile) {
            val properties = PsiTreeUtil.getChildrenOfTypeAsList(myElement, FeakinContextMapDeclaration::class.java)
            val treeElements: MutableList<TreeElement> = ArrayList(properties.size)
            val methodProperties = PsiTreeUtil.getChildrenOfTypeAsList(myElement, FeakinContextDeclaration::class.java)
            for (property in properties) {
                treeElements.add(FeakinStructureViewElement(property))
            }
            for (property in methodProperties) {
                treeElements.add(FeakinStructureViewElement(property))
            }
            return treeElements.toTypedArray()
        }

        return arrayOf()
    }
}