package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.FkValueObjectDeclaration
import com.feakin.intellij.psi.impl.FkValueObjectDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkValueObjectDeclStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkValueObjectDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkValueObjectDeclStub, FkValueObjectDeclaration>("VALUE_OBJECT_DECLARATION") {
        override fun indexStub(stub: FkValueObjectDeclStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkValueObjectDeclStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkValueObjectDeclStub {
            return FkValueObjectDeclStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkValueObjectDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkValueObjectDeclStub {
            return FkValueObjectDeclStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkValueObjectDeclStub): FkValueObjectDeclarationImpl {
            return FkValueObjectDeclarationImpl(stub, this)
        }

    }
}
