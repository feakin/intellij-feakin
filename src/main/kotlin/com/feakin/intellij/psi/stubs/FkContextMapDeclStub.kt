/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FkNamedStub
import com.feakin.intellij.psi.impl.FeakinContextMapDeclarationImpl
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*

class FkContextMapDeclStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    override val name: String?
) : StubBase<FeakinContextMapDeclaration>(parent, elementType), FkNamedStub {
    object Type : FkStubElementType<FkContextMapDeclStub, FeakinContextMapDeclaration>("CONTEXT_MAP") {
        override fun serialize(stub: FkContextMapDeclStub, dataStream: StubOutputStream) {
            return with(dataStream) {
                writeName(stub.name)
            }

        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkContextMapDeclStub {
            return FkContextMapDeclStub(parentStub, this, dataStream.readName()?.string)
        }

        override fun createStub(
            psi: FeakinContextMapDeclaration,
            parentStub: StubElement<out PsiElement>?
        ): FkContextMapDeclStub {
            return FkContextMapDeclStub(parentStub, this, psi.name)
        }

        override fun createPsi(stub: FkContextMapDeclStub): FeakinContextMapDeclarationImpl {
            return FeakinContextMapDeclarationImpl(stub, this)
        }

    }
}
