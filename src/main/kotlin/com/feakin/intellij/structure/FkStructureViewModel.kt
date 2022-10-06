package com.feakin.intellij.structure

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkContextMapDeclaration
import com.feakin.intellij.psi.FkNamedElement
import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor

class FkStructureViewModel(editor: Editor?, psiFile: FkFile) :
    StructureViewModelBase(psiFile, editor, FkStructureViewElement(psiFile)),
    ElementInfoProvider {

    init {
        withSuitableClasses(
            FkNamedElement::class.java
        )
    }

    override fun getSorters(): Array<Sorter> = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = element.value is FkFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return when (element.value) {
            is FkContextMapDeclaration,
            is FkContextDeclaration,
            is FkAggregateDeclaration,
            -> true

            else -> false
        }
    }
}