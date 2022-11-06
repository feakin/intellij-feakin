package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkEntityDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkEntityDeclarationImpl
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkEntityDeclarationStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkEntityDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkEntityDeclarationStub, FkEntityDeclaration>("ENTITY_DECLARATION") {
        override fun indexStub(stub: FkEntityDeclarationStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkEntityDeclarationStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkEntityDeclarationStub {
            return FkEntityDeclarationStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkEntityDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkEntityDeclarationStub {
            return FkEntityDeclarationStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkEntityDeclarationStub): FkEntityDeclarationImpl {
            return FkEntityDeclarationImpl(stub, this)
        }

    }
}
