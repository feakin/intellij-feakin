package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.psi.impl.FkLayerDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkLayerDeclarationStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkLayerDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkLayerDeclarationStub, FkLayerDeclaration>("LAYER_DECLARATION") {
        override fun indexStub(stub: FkLayerDeclarationStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkLayerDeclarationStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkLayerDeclarationStub {
            return FkLayerDeclarationStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createPsi(stub: FkLayerDeclarationStub) = FkLayerDeclarationImpl(stub, this)

        override fun createStub(
            psi: FkLayerDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkLayerDeclarationStub {
            return FkLayerDeclarationStub(parentStub, this, psi.text)
        }
    }
}