package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkEntityDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkEntityDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkEntityDeclStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkEntityDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkEntityDeclStub, FkEntityDeclaration>("ENTITY_DECLARATION") {
        override fun indexStub(stub: FkEntityDeclStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkEntityDeclStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkEntityDeclStub {
            return FkEntityDeclStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkEntityDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkEntityDeclStub {
            return FkEntityDeclStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkEntityDeclStub): FkEntityDeclarationImpl {
            return FkEntityDeclarationImpl(stub, this)
        }

    }
}
