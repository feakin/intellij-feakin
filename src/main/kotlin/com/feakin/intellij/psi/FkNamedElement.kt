package com.feakin.intellij.psi

import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiNameIdentifierOwner

interface FkNamedElement : FkElement, PsiNameIdentifierOwner, NavigationItem