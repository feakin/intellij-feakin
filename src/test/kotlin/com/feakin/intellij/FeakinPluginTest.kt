package com.feakin.intellij

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class FeakinPluginTest : BasePlatformTestCase() {
    fun testXMLFile() {
        val psiFile = myFixture.configureByText(FeakinFileType, "ContextMap Demo {}")
        val fkFile = assertInstanceOf(psiFile, FeakinFile::class.java)

        assertFalse(PsiErrorElementUtil.hasErrors(project, fkFile.virtualFile))
    }
}
