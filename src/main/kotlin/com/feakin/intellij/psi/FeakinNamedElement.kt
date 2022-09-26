package com.feakin.intellij.psi

import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiNamedElement

interface FeakinNamedElement : FeakinPsiCompositeElement, PsiNamedElement, NavigationItem