package com.feakin.intellij.psi.impl

import com.feakin.intellij.lexer.FeakinElementTypes.IDENTIFIER
import com.feakin.intellij.psi.FeakinNamedElement
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.IncorrectOperationException
import org.jetbrains.annotations.NonNls
import java.util.*

open class FeakinNamedElementImpl(node: ASTNode) : FeakinPsiCompositeElementImpl(node), FeakinNamedElement,
    PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement? = findChildByType(IDENTIFIER)

    override fun getName(): String? = nameIdentifier?.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: @NonNls String): PsiElement {
        leaf?.replaceWithText(name)
        return this
    }

    private val leaf: LeafPsiElement?
        get() = Objects.requireNonNull(PsiTreeUtil.getChildOfType(this, LeafPsiElement::class.java))

    override fun getPresentation(): ItemPresentation? {
        val parent = parent
        return if (parent is NavigationItem) {
            (parent as NavigationItem).presentation
        } else null
    }
}
