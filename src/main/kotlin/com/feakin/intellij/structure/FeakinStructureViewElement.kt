package com.feakin.intellij.structure

import com.feakin.intellij.FeakinFile
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.impl.FeakinContextMapDeclarationImpl
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil

class FeakinStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {
    override fun getValue(): Any {
        return myElement
    }

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return myElement.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return myElement.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        val name = myElement.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val struct = PresentationData()
        struct.setIcon(null)
        struct.locationString = null
        if (myElement is FeakinContextMapDeclarationImpl) {
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
        if (myElement is FeakinFile) {
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