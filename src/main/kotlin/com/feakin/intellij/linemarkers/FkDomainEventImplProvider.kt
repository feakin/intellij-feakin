package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkDomainEvent
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.psi.PsiElement

// todo: not sure if this is the right way to do this
class FkDomainEventImplProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(el: PsiElement, result: MutableCollection<in RelatedItemLineMarkerInfo<*>>) {
        if (el !is FkDomainEvent) return

//        var icon = AllIcons.Gutter.ImplementingMethod
//        val builder = NavigationGutterIconBuilder
//            .create(icon)
//            .setTargets(listOf(superItem))
//            .setTooltipText("$action $type in `${trait.name}`")
//
//        result.add(builder.createLineMarkerInfo(element))
    }
}