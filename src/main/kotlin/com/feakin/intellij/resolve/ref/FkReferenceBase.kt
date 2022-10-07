/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.resolve.ref

import com.feakin.intellij.ide.editor.elementType
import com.feakin.intellij.lexer.FkElementTypes.IDENTIFIER
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkPsiFactory
import com.feakin.intellij.psi.ext.FkReferenceElementBase
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult

abstract class FkReferenceBase<T : FkReferenceElementBase>(
    element: T
) : PsiPolyVariantReferenceBase<T>(element),
    FkReference {

    override fun resolve(): FkElement? = super.resolve() as? FkElement

    override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> =
        multiResolve().map { PsiElementResolveResult(it) }.toTypedArray()

    open val T.referenceAnchor: PsiElement? get() = referenceNameElement

    final override fun getRangeInElement(): TextRange = super.getRangeInElement()

    final override fun calculateDefaultRangeInElement(): TextRange {
        val anchor = element.referenceAnchor ?: return TextRange.EMPTY_RANGE
        check(anchor.parent === element)
        return TextRange.from(anchor.startOffsetInParent, anchor.textLength)
    }

    override fun handleElementRename(newName: String): PsiElement {
        val referenceNameElement = element.referenceNameElement
        if (referenceNameElement != null) {
            doRename(referenceNameElement, newName)
        }
        return element
    }

    override fun getVariants(): Array<out LookupElement> = LookupElement.EMPTY_ARRAY

    override fun equals(other: Any?): Boolean = other is FkReferenceBase<*> && element === other.element

    override fun hashCode(): Int = element.hashCode()

    companion object {
        @JvmStatic
        fun doRename(identifier: PsiElement, newName: String) {
            val factory = FkPsiFactory(identifier.project)
            val newId = when (identifier.elementType) {
                IDENTIFIER -> {
                    val name = newName.replace(".fkl", "")
                    factory.createIdentifier(name)

                }

                else -> error("Unsupported identifier type for `$newName` (${identifier.elementType})")
            }
            identifier.replace(newId)
        }
    }
}