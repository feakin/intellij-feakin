package com.feakin.intellij.indexing

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.indexing.IndexableSetContributor

class FkIndexableSetContributor : IndexableSetContributor() {
    override fun getAdditionalRootsToIndex(): MutableSet<VirtualFile> = mutableSetOf()
}
