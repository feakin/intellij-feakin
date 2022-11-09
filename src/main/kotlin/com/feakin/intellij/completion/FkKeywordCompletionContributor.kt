/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.completion


import com.feakin.intellij.FkFile
import com.feakin.intellij.lexer.FkElementTypes.*
import com.feakin.intellij.psi.FkDeclaration
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PatternCondition
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.patterns.StandardPatterns
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext


// refs: https://github.com/JetBrains/intellij-community/blob/master/plugins/kotlin/completion/impl-shared/src/org/jetbrains/kotlin/idea/completion/implCommon/KeywordCompletion.kt
class FkKeywordCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(
            CompletionType.BASIC, declarationPattern(),
            KeywordsCompletionProvider("ContextMap", "Context", "Aggregate", "Entity", "ValueObject", "Struct")
        )
    }

    class KeywordsCompletionProvider(private vararg val keywords: String) :
        CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            keywords.forEach { keyword ->
                result.addElement(createKeywordLookupElement(keyword))
            }
        }


        private fun createKeywordLookupElement(keyword: String): LookupElement {
            return PrioritizedLookupElement.withPriority(LookupElementBuilder.create(keyword).bold(), 1.0)
        }
    }

    fun onStatementBeginning(vararg startWords: String): PsiElementPattern.Capture<PsiElement> =
        psiElement().with(OnStatementBeginning(*startWords))

    private class OnStatementBeginning(vararg startWords: String) :
        PatternCondition<PsiElement>("on statement beginning") {
        private val STATEMENT_BOUNDARIES = TokenSet.create(SEMICOLON, LBRACE, RBRACE)
        val myStartWords = startWords
        override fun accepts(t: PsiElement, context: ProcessingContext?): Boolean {
            val prev = t.prevVisibleOrNewLine
            return if (myStartWords.isEmpty())
                prev == null || prev is PsiWhiteSpace || prev.node.elementType in STATEMENT_BOUNDARIES
            else
                prev != null && prev.node.text in myStartWords
        }
    }

    private fun identifierStatementBeginningPattern(vararg startWords: String): PsiElementPattern.Capture<PsiElement> =
        psiElement(IDENTIFIER).and(onStatementBeginning(*startWords))

    fun declarationPattern(): PsiElementPattern.Capture<PsiElement> =
        baseDeclarationPattern().and(identifierStatementBeginningPattern())

    fun baseDeclarationPattern(): PsiElementPattern.Capture<PsiElement> =
        psiElement()
            .withParent(StandardPatterns.or(psiElement<FkFile>()))

}

inline fun <reified I : PsiElement> psiElement(): PsiElementPattern.Capture<I> {
    return psiElement(I::class.java)
}

val PsiElement.leftLeaves: Sequence<PsiElement>
    get() = generateSequence(this, PsiTreeUtil::prevLeaf).drop(1)
val PsiElement.prevVisibleOrNewLine: PsiElement?
    get() = leftLeaves
        .filterNot { it is PsiComment || it is PsiErrorElement }
        .filter { it !is PsiWhiteSpace || it.textContains('\n') }
        .firstOrNull()
