package com.feakin.intellij.parser

import com.feakin.intellij.FkIcons
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.psi.FkNameComponent
import com.intellij.navigation.ItemPresentation
import javax.swing.Icon

object FkPsiImplUtil {
    fun getName(element: FkNameComponent): String {
        return element.identifier.text
    }

    fun getKey(element: FkNameComponent): String? {
        val keyNode = element.node.findChildByType(FkElementTypes.NAME_COMPONENT)
        return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
    }

    fun getPresentation(element: FkNameComponent): ItemPresentation {
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
}