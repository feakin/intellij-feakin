package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkIncludeDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FkIncludeDeclarationImpl
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkIncludeStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkIncludeDeclaration>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkIncludeStub, FkIncludeDeclaration>("INCLUDE") {
        override fun serialize(stub: FkIncludeStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkIncludeStub {
            return FkIncludeStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createPsi(stub: FkIncludeStub) = FkIncludeDeclarationImpl(stub, this)

        override fun createStub(
            psi: FkIncludeDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkIncludeStub {
            return FkIncludeStub(parentStub, this, psi.text)
        }
    }
}