package com.feakin.intellij.parser

import com.feakin.intellij.FkIcons
import com.feakin.intellij.lexer.FeakinElementTypes
import com.feakin.intellij.psi.FeakinAggregateDeclaration
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FeakinEntityDeclaration
import com.feakin.intellij.psi.FeakinNameComponent
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import javax.swing.Icon

object FkPsiImplUtil {
    fun getName(element: FeakinNameComponent): String {
        return element.identifier.text
    }

    fun getKey(element: FeakinNameComponent): String? {
        val keyNode = element.node.findChildByType(FeakinElementTypes.NAME_COMPONENT)
        return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
    }

    fun getPresentation(element: FeakinNameComponent): ItemPresentation {
        return object : ItemPresentation {
            override fun getPresentableText(): String? {
                return element.name
            }

            override fun getLocationString(): String? {
                val containingFile = element.containingFile
                return containingFile?.name
            }

            override fun getIcon(unused: Boolean): Icon {
                return FkIcons.FILE
            }
        }
    }

    fun getNameIdentifier(o: FeakinContextMapDeclaration): PsiElement = o
}