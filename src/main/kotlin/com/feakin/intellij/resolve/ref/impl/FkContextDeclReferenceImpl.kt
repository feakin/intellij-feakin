package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.stubs.index.FkNamedElementIndex
import com.intellij.psi.PsiElement

// todo: change to contextNameRef
//class FkContextDeclReferenceImpl(
//    element: FkContextDeclaration
//) : FkReferenceBase<FkContextDeclaration>(element) {
//    override fun multiResolve(): List<FkElement> {
//        val collection =
//            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project, element.resolveScope)
//                .filter { it is FkContextName }
//                .toCollection(mutableListOf())
//
//        return collection
//    }
//
//    override fun isReferenceTo(element: PsiElement): Boolean {
//        return element is FkContextDeclaration && super.isReferenceTo(element)
//    }
//}

