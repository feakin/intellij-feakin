// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class FeakinVisitor extends PsiElementVisitor {

  public void visitAggregationDeclaration(@NotNull FeakinAggregationDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitAggregationName(@NotNull FeakinAggregationName o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextDeclaration(@NotNull FeakinContextDeclaration o) {
    visitNamedElement(o);
  }

  public void visitContextMapDeclaration(@NotNull FeakinContextMapDeclaration o) {
    visitNamedElement(o);
  }

  public void visitContextMapName(@NotNull FeakinContextMapName o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextName(@NotNull FeakinContextName o) {
    visitPsiCompositeElement(o);
  }

  public void visitContextNodeName(@NotNull FeakinContextNodeName o) {
    visitPsiCompositeElement(o);
  }

  public void visitDeclaration(@NotNull FeakinDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitEntityDeclaration(@NotNull FeakinEntityDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitEntityName(@NotNull FeakinEntityName o) {
    visitPsiCompositeElement(o);
  }

  public void visitNameComponent(@NotNull FeakinNameComponent o) {
    visitNamedElement(o);
  }

  public void visitValueObjectDeclaration(@NotNull FeakinValueObjectDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitValueObjectName(@NotNull FeakinValueObjectName o) {
    visitPsiCompositeElement(o);
  }

  public void visitNamedElement(@NotNull FeakinNamedElement o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiCompositeElement(@NotNull FeakinPsiCompositeElement o) {
    visitElement(o);
  }

}
