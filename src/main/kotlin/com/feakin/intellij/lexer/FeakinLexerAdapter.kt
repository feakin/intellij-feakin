package com.feakin.intellij.lexer

import com.intellij.lexer.FlexAdapter

class FeakinLexerAdapter : FlexAdapter(com.feakin.intellij.lexer._FeakinLexer(null))