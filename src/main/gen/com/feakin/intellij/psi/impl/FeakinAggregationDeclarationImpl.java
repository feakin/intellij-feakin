// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.feakin.intellij.lexer.FeakinTypes.*;
import com.feakin.intellij.psi.*;
import com.feakin.intellij.parser.FeakinPsiImplUtil;

public class FeakinAggregationDeclarationImpl extends FeakinPsiCompositeElementImpl implements FeakinAggregationDeclaration {

  public FeakinAggregationDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FeakinVisitor visitor) {
    visitor.visitAggregationDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FeakinVisitor) accept((FeakinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public FeakinAggregationName getAggregationName() {
    return findNotNullChildByClass(FeakinAggregationName.class);
  }

}
