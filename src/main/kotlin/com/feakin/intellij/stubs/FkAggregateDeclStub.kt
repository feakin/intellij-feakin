package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkAggregateDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkAggregateDeclStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkAggregateDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkAggregateDeclStub, FkAggregateDeclaration>("AGGREGATE_DECLARATION") {
        override fun indexStub(stub: FkAggregateDeclStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkAggregateDeclStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkAggregateDeclStub {
            return FkAggregateDeclStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkAggregateDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkAggregateDeclStub {
            return FkAggregateDeclStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkAggregateDeclStub): FkAggregateDeclarationImpl {
            return FkAggregateDeclarationImpl(stub, this)
        }

    }
}
