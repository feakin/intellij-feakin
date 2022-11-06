package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkContextDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkContextDeclarationStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkContextDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkContextDeclarationStub, FkContextDeclaration>("CONTEXT_DECLARATION") {
        override fun indexStub(stub: FkContextDeclarationStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkContextDeclarationStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkContextDeclarationStub {
            return FkContextDeclarationStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkContextDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkContextDeclarationStub {
            return FkContextDeclarationStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkContextDeclarationStub): FkContextDeclarationImpl {
            return FkContextDeclarationImpl(stub, this)
        }

    }
}
