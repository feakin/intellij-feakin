package com.feakin.intellij.structure

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FeakinAggregateDeclaration
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile

class FeakinStructureViewModel(psiFile: PsiFile) : StructureViewModelBase(psiFile, FeakinStructureViewElement(psiFile)),
    ElementInfoProvider {

    override fun getSorters(): Array<Sorter> = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = element.value is FkFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return when (element.value) {
            is FeakinContextMapDeclaration,
            is FeakinContextDeclaration,
            is FeakinAggregateDeclaration,
            -> true

            else -> false
        }
    }
}