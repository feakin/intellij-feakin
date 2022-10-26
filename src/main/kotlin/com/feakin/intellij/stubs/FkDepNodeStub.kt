package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkDepNode
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkDepNodeImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkDepNodeStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkDepNode>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkDepNodeStub, FkDepNode>("DEP_NODE") {
        override fun serialize(stub: FkDepNodeStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkDepNodeStub {
            return FkDepNodeStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkDepNode,
            parentStub: StubElement<out PsiElement>?
        ): FkDepNodeStub {
            return FkDepNodeStub(parentStub, this, psi.identifier!!.text)
        }

        override fun createPsi(stub: FkDepNodeStub): FkDepNodeImpl {
            return FkDepNodeImpl(stub, this)
        }

    }
}
