package com.feakin.intellij.structure

import com.feakin.intellij.FeakinFile
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile

class FeakinStructureViewModel(psiFile: PsiFile) : StructureViewModelBase(psiFile, FeakinStructureViewElement(psiFile)),
    ElementInfoProvider {
    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return element.value is FeakinFile
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        when (element.value) {
            is FeakinContextDeclaration,
            is FeakinContextMapDeclaration -> return true
            else -> return false
        }
    }
}