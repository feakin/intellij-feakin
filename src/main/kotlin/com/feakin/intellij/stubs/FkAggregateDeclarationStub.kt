package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkAggregateDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkAggregateDeclarationStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkAggregateDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkAggregateDeclarationStub, FkAggregateDeclaration>("AGGREGATE_DECLARATION") {
        override fun indexStub(stub: FkAggregateDeclarationStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkAggregateDeclarationStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkAggregateDeclarationStub {
            return FkAggregateDeclarationStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkAggregateDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkAggregateDeclarationStub {
            return FkAggregateDeclarationStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkAggregateDeclarationStub): FkAggregateDeclarationImpl {
            return FkAggregateDeclarationImpl(stub, this)
        }

    }
}
