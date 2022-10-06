package com.feakin.intellij.parser

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkLanguage
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.lexer.FkLexer
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
        val COMMENTS = TokenSet.create(FkElementTypes.COMMENT)
        val FILE = IFileElementType(FkLanguage)
    }

    override fun createLexer(project: Project): Lexer {
        return FkLexer()
    }

    override fun createParser(project: Project): PsiParser {
        return com.feakin.intellij.parser.FkParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

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