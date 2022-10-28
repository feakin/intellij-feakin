package com.feakin.intellij.structure

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkIcons
import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkContextMapDeclaration
import com.feakin.intellij.psi.FkDeclaration
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.TreeAnchorizer
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement

class FkStructureViewElement(element: PsiElement) : StructureViewTreeElement {
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

        when (val psi = psi) {
            is FkContextMapDeclaration -> {
                val structNameDeclaration = psi.identifier
                struct.setAttributesKey(DefaultLanguageHighlighterColors.IDENTIFIER)
                struct.presentableText = structNameDeclaration.text
                return struct
            }

            is FkContextDeclaration -> {
                val structNameDeclaration = psi.identifier
                struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
                struct.presentableText = structNameDeclaration.text
                return struct
            }

            is FkFile -> {
                struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD)
                struct.setIcon(FkIcons.FILE)
                struct.presentableText = psi.originalFile.name
                return struct
            }

            else -> {
                return PresentationData()
            }
        }
    }

    override fun getChildren(): Array<TreeElement> {
        val treeElements = ArrayList<TreeElement>()

        psi?.children?.forEach { child ->
            val nodes = findContentNode(child).filterNotNull()
            nodes.forEach {
                treeElements.add(FkStructureViewElement(it))
            }
        }

        return treeElements.toTypedArray()
    }

    // todo: make it better.
    private fun findContentNode(psi: PsiElement?): List<PsiElement?> {
        when (psi) {
            is FkDeclaration -> {
                val res = ArrayList<PsiElement?>()
                psi.children.forEach {
                    res.addAll(findContentNode(it).filterNotNull())
                }
                return res
            }

            is FkContextMapDeclaration -> {
                return listOf(psi)
            }

            is FkContextDeclaration -> {
                return listOf(psi)
            }

            else -> {
                return emptyList()
            }
        }
    }
}