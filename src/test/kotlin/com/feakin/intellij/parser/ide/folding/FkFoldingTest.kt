package com.feakin.intellij.parser.ide.folding

import com.intellij.testFramework.fixtures.BasePlatformTestCase

open class FkFoldingTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/folding"
    }

    fun testBasicFolding() = doTest()


    private val testName: String
        get() = getTestName(false)

    protected val fileName: String
        get() = "$testName.fkl"

    private fun doTest() {
        myFixture.testFolding("$testDataPath/$fileName")
    }
}