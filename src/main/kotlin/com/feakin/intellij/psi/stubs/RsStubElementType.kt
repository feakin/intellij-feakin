/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.psi.stubs

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.psi.FkElement
import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.IndexSink
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IStubFileElementType

abstract class FkStubElementType<StubT : StubElement<*>, PsiT : FkElement>(
    debugName: String
) : IStubElementType<StubT, PsiT>(debugName, FkLanguage) {

    final override fun getExternalId(): String = "fkl.${super.toString()}"

    override fun indexStub(stub: StubT, sink: IndexSink) {}
}

fun createStubIfParentIsStub(node: ASTNode): Boolean {
    val parent = node.treeParent
    val parentType = parent.elementType
    return (parentType is IStubElementType<*, *> && parentType.shouldCreateStub(parent)) ||
        parentType is IStubFileElementType<*>
}
