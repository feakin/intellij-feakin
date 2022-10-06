package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FkContextMapDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkContextMapDeclarationImpl
import com.feakin.intellij.psi.stubs.index.FkNamedElementIndex
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkContextMapDeclStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkContextMapDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkContextMapDeclStub, FkContextMapDeclaration>("CONTEXT_MAP_DECLARATION") {
        override fun indexStub(stub: FkContextMapDeclStub, sink: IndexSink) {
            stub.name?.let { sink.occurrence(FkNamedElementIndex.KEY, it) }
        }

        override fun serialize(stub: FkContextMapDeclStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkContextMapDeclStub {
            return FkContextMapDeclStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FkContextMapDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkContextMapDeclStub {
            return FkContextMapDeclStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkContextMapDeclStub): FkContextMapDeclarationImpl {
            return FkContextMapDeclarationImpl(stub, this)
        }

    }
}
