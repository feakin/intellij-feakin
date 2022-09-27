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
import com.intellij.navigation.ItemPresentation;

public class FeakinContextMapDeclarationImpl extends FeakinNamedElementImpl implements FeakinContextMapDeclaration {

  public FeakinContextMapDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FeakinVisitor visitor) {
    visitor.visitContextMapDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FeakinVisitor) accept((FeakinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public FeakinContextMapName getContextMapName() {
    return findNotNullChildByClass(FeakinContextMapName.class);
  }

  @Override
  @NotNull
  public List<FeakinContextName> getContextNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FeakinContextName.class);
  }

  @Override
  @NotNull
  public List<FeakinContextNodeName> getContextNodeNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FeakinContextNodeName.class);
  }

}
