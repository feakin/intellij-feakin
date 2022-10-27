package com.feakin.intellij.parser.ide

import com.feakin.intellij.parser.FkParserDefinition
import com.intellij.testFramework.ParsingTestCase

class FeakinParsingTest : ParsingTestCase("parser", "fkl", FkParserDefinition()) {
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

    fun testLayered() {
        doTest(true)
    }

    fun testLayeredWithImpl() {
        doTest(true)
    }

    fun testInlineDoc() {
        doTest(true)
    }

    fun testSourceSet() {
        doTest(true)
    }

    fun testTypeDef() {
        doTest(true)
    }

    fun testEnvDatasource() {
        doTest(true)
    }
}