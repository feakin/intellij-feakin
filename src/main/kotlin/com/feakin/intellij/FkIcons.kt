package com.feakin.intellij

import com.intellij.openapi.util.IconLoader

object FkIcons {
    @JvmField
    val  FILE = IconLoader.getIcon("/icons/feakin.svg", FkIcons::class.java)
    val  RUN = IconLoader.getIcon("/icons/feakin-run.svg", FkIcons::class.java)
}