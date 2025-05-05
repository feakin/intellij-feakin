package com.feakin.intellij

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil

class FeakinPluginTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/folding"
    }

    fun testBasicPsi() {
        val psiFile = myFixture.configureByText(FkFileType, "ContextMap Demo { Reservation -> Cinema; }")
        myFixture.checkHighlighting();
        val fkFile = assertInstanceOf(psiFile, FkFile::class.java)

        assertFalse(PsiErrorElementUtil.hasErrors(project, fkFile.virtualFile))
    }
}
