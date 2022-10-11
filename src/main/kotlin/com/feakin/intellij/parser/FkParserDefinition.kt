package com.feakin.intellij.parser

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileStub
import com.feakin.intellij.FkLanguage
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.lexer.FkLexer
import com.feakin.intellij.lexer.FkTokenType
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class FkParserDefinition : ParserDefinition {
    companion object {
        @JvmField val INLINE_DOC = FkTokenType("inner_block_doc")

        val COMMENTS = TokenSet.create(FkElementTypes.COMMENT, INLINE_DOC)
        val FILE = IFileElementType(FkLanguage)
    }

    override fun createLexer(project: Project): Lexer {
        return FkLexer()
    }

    override fun createParser(project: Project): PsiParser {
        return com.feakin.intellij.parser.FkParser()
    }

    override fun getFileNodeType(): IFileElementType = FkFileStub.Type

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode): PsiElement {
        return FkElementTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return FkFile(viewProvider)
    }
}