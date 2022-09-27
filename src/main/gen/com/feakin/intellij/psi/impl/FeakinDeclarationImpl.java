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

public class FeakinDeclarationImpl extends FeakinPsiCompositeElementImpl implements FeakinDeclaration {

  public FeakinDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FeakinVisitor visitor) {
    visitor.visitDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FeakinVisitor) accept((FeakinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FeakinAggregationDeclaration getAggregationDeclaration() {
    return findChildByClass(FeakinAggregationDeclaration.class);
  }

  @Override
  @Nullable
  public FeakinContextDeclaration getContextDeclaration() {
    return findChildByClass(FeakinContextDeclaration.class);
  }

  @Override
  @Nullable
  public FeakinContextMapDeclaration getContextMapDeclaration() {
    return findChildByClass(FeakinContextMapDeclaration.class);
  }

  @Override
  @Nullable
  public FeakinEntityDeclaration getEntityDeclaration() {
    return findChildByClass(FeakinEntityDeclaration.class);
  }

  @Override
  @Nullable
  public FeakinValueObjectDeclaration getValueObjectDeclaration() {
    return findChildByClass(FeakinValueObjectDeclaration.class);
  }

}
