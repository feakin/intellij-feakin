package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.FkStructDeclaration
import com.feakin.intellij.psi.impl.FkStructDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkStructDeclarationStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkStructDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkStructDeclarationStub, FkStructDeclaration>("STRUCT_DECLARATION") {
        override fun indexStub(stub: FkStructDeclarationStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkStructDeclarationStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkStructDeclarationStub {
            return FkStructDeclarationStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkStructDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkStructDeclarationStub {
            return FkStructDeclarationStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkStructDeclarationStub): FkStructDeclarationImpl {
            return FkStructDeclarationImpl(stub, this)
        }

    }
}
