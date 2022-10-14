package com.feakin.intellij.stubs

import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.FkPath
import com.feakin.intellij.psi.impl.FkPathImpl
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkPathStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FkPath>(
    parent,
    elementType
), FkNamedStub {
    object Type : FkStubElementType<FkPathStub, FkPath>("PATH") {
        override fun serialize(stub: FkPathStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkPathStub {
            return FkPathStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createPsi(stub: FkPathStub) = FkPathImpl(stub, this)

        override fun createStub(
            psi: FkPath,
            parentStub: StubElement<out PsiElement>?
        ): FkPathStub {
            return FkPathStub(parentStub, this, psi.text)
        }
    }
}