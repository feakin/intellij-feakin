package com.feakin.intellij.inspections.lints

import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkEntityDeclaration
import com.feakin.intellij.psi.FkVisitor
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor

class FkDuplicateDeclInspection : LocalInspectionTool() {
    override fun getDisplayName() = "Duplicate declaration"
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return FkDuplicateContextDeclVisitor(holder)
    }

    private class FkDuplicateContextDeclVisitor(val holder: ProblemsHolder): FkVisitor() {
        override fun visitContextDeclaration(decl: FkContextDeclaration) {
            decl.identifier.let { name ->
                FkNamedElementIndex.findElementsByName(name.text, decl.project).filterIsInstance<FkContextDeclaration>().let { decls ->
                    if (decls.size > 1) {
                        holder.registerProblem(decl, "Duplicate context declaration")
                    }
                }
            }
        }

        override fun visitAggregateDeclaration(o: FkAggregateDeclaration) {
            o.identifier.let { name ->
                FkNamedElementIndex.findElementsByName(name.text, o.project).filterIsInstance<FkAggregateDeclaration>().let { decls ->
                    if (decls.size > 1) {
                        holder.registerProblem(o, "Duplicate aggregate declaration")
                    }
                }
            }
        }

        override fun visitEntityDeclaration(o: FkEntityDeclaration) {
            o.identifier.let { name ->
                FkNamedElementIndex.findElementsByName(name.text, o.project).filterIsInstance<FkEntityDeclaration>().let { decls ->
                    if (decls.size > 1) {
                        holder.registerProblem(o, "Duplicate entity declaration")
                    }
                }
            }
        }
    }
}