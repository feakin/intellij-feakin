/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FkElement
import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.*

open class FkPlaceholderStub<PsiT : FkElement>(parent: StubElement<*>?, elementType: IStubElementType<*, *>)
    : StubBase<PsiT>(parent, elementType) {

    open class Type<PsiT : FkElement>(
        debugName: String,
        private val psiCtor: (FkPlaceholderStub<*>, IStubElementType<*, *>) -> PsiT
    ) : FkStubElementType<FkPlaceholderStub<*>, PsiT>(debugName) {

        override fun shouldCreateStub(node: ASTNode): Boolean = createStubIfParentIsStub(node)

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkPlaceholderStub<PsiT>
            = FkPlaceholderStub(parentStub, this)

        override fun serialize(stub: FkPlaceholderStub<*>, dataStream: StubOutputStream) {
        }

        override fun createPsi(stub: FkPlaceholderStub<*>): PsiT = psiCtor(stub, this)

        override fun createStub(psi: PsiT, parentStub: StubElement<*>?): FkPlaceholderStub<PsiT>
            = FkPlaceholderStub(parentStub, this)
    }
}
