package com.feakin.intellij.completion

import com.feakin.intellij.highlight.FkTokenTypeSets
import com.feakin.intellij.psi.FkNamedElement
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext


// refs: https://github.com/JetBrains/intellij-community/blob/master/plugins/kotlin/completion/impl-shared/src/org/jetbrains/kotlin/idea/completion/implCommon/KeywordCompletion.kt
class FkKeywordCompletionContributor : CompletionContributor(), DumbAware {
    init {
        val psiElement = PlatformPatterns.psiElement(FkNamedElement::class.java)
        extend(CompletionType.BASIC, psiElement, object : CompletionProvider<CompletionParameters>() {
            override fun addCompletions(
                parameters: CompletionParameters,
                context: ProcessingContext,
                result: CompletionResultSet
            ) {
                FkTokenTypeSets.KEY_WORDS.types.forEach {
                    result.addElement(LookupElementBuilder.create(it.toString()))
                }
            }
        }
        )
    }
}
