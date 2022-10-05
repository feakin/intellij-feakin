package com.feakin.intellij.parser.ide

import com.feakin.intellij.parser.FeakinParserDefinition
import com.intellij.testFramework.ParsingTestCase

class FeakinParsingTest : ParsingTestCase("parser", "fkl", FeakinParserDefinition()) {
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    fun testBasicContextMap() {
        doTest(true)
    }

    fun testContextMapAggregate() {
        doTest(true)
    }

    fun testEntityStructList() {
        doTest(true)
    }

    fun testEmptyBody() {
        doTest(true)
    }

    fun testRequestImpl() {
        doTest(true)
    }

    fun testImplFlow() {
        doTest(true)
    }
}