package com.feakin.intellij.parser

import com.intellij.testFramework.ParsingTestCase

class FeakinParsingTest: ParsingTestCase("parser", "fkl", FeakinParserDefinition()) {
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    fun testBasicContextMap() {
        doTest(true)
    }
}