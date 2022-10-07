package com.feakin.intellij.completion


import com.feakin.intellij.highlight.FkTokenTypeSets
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.StandardPatterns.instanceOf
import com.intellij.util.ProcessingContext


// refs: https://github.com/JetBrains/intellij-community/blob/master/plugins/kotlin/completion/impl-shared/src/org/jetbrains/kotlin/idea/completion/implCommon/KeywordCompletion.kt
class FkKeywordCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(
            CompletionType.BASIC, psiElement().inFile(instanceOf(FkFile::class.java)),
            KeywordsCompletionProvider()
        )
    }

    class KeywordsCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            FkTokenTypeSets.KEY_WORDS.types.forEach {
                result.addElement(LookupElementBuilder.create(it.debugName).bold())
            }
        }
    }
}

