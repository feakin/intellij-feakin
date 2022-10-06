package com.feakin.intellij.psi

import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiNameIdentifierOwner

interface FeakinNamedElement : FeakinPsiCompositeElement, PsiNameIdentifierOwner, NavigationItem