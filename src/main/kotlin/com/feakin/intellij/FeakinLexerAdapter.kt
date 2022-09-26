package com.feakin.intellij

import com.feakin.intellij.lexer._FeakinLexer
import com.intellij.lexer.FlexAdapter

class FeakinLexerAdapter : FlexAdapter(_FeakinLexer(null))