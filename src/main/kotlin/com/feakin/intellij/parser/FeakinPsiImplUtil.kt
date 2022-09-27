package com.feakin.intellij.parser

import com.feakin.intellij.FeakinIcons
import com.feakin.intellij.lexer.FeakinElementTypes
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FeakinNameComponent
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiReference
import javax.swing.Icon

object FeakinPsiImplUtil {
    fun qualifiedName(builder: PsiBuilder?, level: Int, parser: GeneratedParserUtilBase.Parser?) {}
    @JvmStatic
    fun getReference(namePart: FeakinNameComponent?): PsiReference? {
        return null
    }

    fun getKey(element: FeakinContextMapDeclaration): String? {
        val keyNode = element.node.findChildByType(FeakinElementTypes.CONTEXT_MAP_DECLARATION)
        return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
    }

    fun getName(element: FeakinContextMapDeclaration): String? {
        return getKey(element)
    }

    fun getPresentation(element: FeakinContextMapDeclaration): ItemPresentation {
        return object : ItemPresentation {
            override fun getPresentableText(): String? {
                return element.name
            }

            override fun getLocationString(): String? {
                val containingFile = element.containingFile
                return containingFile?.name
            }

            override fun getIcon(unused: Boolean): Icon {
                return FeakinIcons.FILE
            }
        }
    }
}