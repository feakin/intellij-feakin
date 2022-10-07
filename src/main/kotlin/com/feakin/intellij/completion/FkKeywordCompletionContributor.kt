package com.feakin.intellij.completion


import com.feakin.intellij.FkFile
import com.feakin.intellij.highlight.FkTokenTypeSets
import com.feakin.intellij.psi.FkDeclaration
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.patterns.StandardPatterns
import com.intellij.patterns.StandardPatterns.instanceOf
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
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
            val position = parameters.position
            if (PsiTreeUtil.getParentOfType(position, PsiComment::class.java) != null) {
                return
            }

            val withParent = psiElement()
                .withParent(StandardPatterns.or(psiElement<FkDeclaration>(), psiElement<FkFile>()))

            if (withParent.accepts(position)) {
                FkTokenTypeSets.KEY_WORDS.types.forEach {
                    result.addElement(LookupElementBuilder.create(it.debugName).bold())
                }
            }
        }
    }
}

inline fun <reified I : PsiElement> psiElement(): PsiElementPattern.Capture<I> {
    return psiElement(I::class.java)
}