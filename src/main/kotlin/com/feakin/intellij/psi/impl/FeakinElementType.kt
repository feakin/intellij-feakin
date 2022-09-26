package com.feakin.intellij.psi.impl

import com.feakin.intellij.FeakinLanguage
import com.intellij.psi.tree.IElementType

class FeakinElementType(debugName: String) : IElementType(debugName, FeakinLanguage.INSTANCE)
