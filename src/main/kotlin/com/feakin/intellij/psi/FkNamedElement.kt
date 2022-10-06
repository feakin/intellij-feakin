/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.psi

import com.feakin.intellij.lexer.FkElementTypes.IDENTIFIER
import com.intellij.lang.ASTNode
import com.intellij.navigation.NavigationItem
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.util.PsiTreeUtil
import java.util.*

interface FkNamedElement : FkElement, PsiNamedElement, NavigatablePsiElement

interface FkNameIdentifierOwner : FkNamedElement, PsiNameIdentifierOwner

interface FkNamedStub {
    val name: String?
}

abstract class FkStubbedNamedElementImpl<StubT> : FkStubbedElementImpl<StubT>,
    FkNameIdentifierOwner where StubT : FkNamedStub, StubT : StubElement<*> {

    constructor(node: ASTNode) : super(node)

    constructor(stub: StubT, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getNameIdentifier(): PsiElement? = findChildByType(IDENTIFIER)

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()

    override fun getName(): String? = nameIdentifier?.text

    private val leaf: LeafPsiElement?
        get() = Objects.requireNonNull(PsiTreeUtil.getChildOfType(this, LeafPsiElement::class.java))

    override fun setName(name: String): PsiElement? {
//        nameIdentifier?.replace(RsPsiFactory(project).createIdentifier(name))
        leaf?.replaceWithText(name)
        return this
    }

}
