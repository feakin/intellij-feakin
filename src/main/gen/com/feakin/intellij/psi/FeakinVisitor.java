// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class FeakinVisitor extends PsiElementVisitor {

  public void visitContextDeclaration(@NotNull FeakinContextDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextMapDeclaration(@NotNull FeakinContextMapDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextMapName(@NotNull FeakinContextMapName o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextName(@NotNull FeakinContextName o) {
    visitPsiCompositeElement(o);
  }

  public void visitDeclaration(@NotNull FeakinDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitNameComponent(@NotNull FeakinNameComponent o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull FeakinNamedElement o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiCompositeElement(@NotNull FeakinPsiCompositeElement o) {
    visitElement(o);
  }

}
