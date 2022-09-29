package com.feakin.intellij.structure

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FeakinAggregateDeclaration
import com.feakin.intellij.psi.FeakinAggregateName
import com.feakin.intellij.psi.FeakinContextDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FeakinContextMapName
import com.feakin.intellij.psi.FeakinContextName
import com.feakin.intellij.psi.FeakinNamedElement
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
            FeakinNamedElement::class.java
        )
    }

    override fun getSorters(): Array<Sorter> = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = element.value is FkFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return when (element.value) {
            is FeakinContextMapDeclaration,
            is FeakinContextDeclaration,
            is FeakinAggregateDeclaration,

            is FeakinContextName,
            is FeakinContextMapName,
            is FeakinAggregateName,
            -> true

            else -> false
        }
    }


}