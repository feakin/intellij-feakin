// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FeakinDeclaration extends FeakinPsiCompositeElement {

  @Nullable
  FeakinAggregationDeclaration getAggregationDeclaration();

  @Nullable
  FeakinContextDeclaration getContextDeclaration();

  @Nullable
  FeakinContextMapDeclaration getContextMapDeclaration();

  @Nullable
  FeakinEntityDeclaration getEntityDeclaration();

  @Nullable
  FeakinValueObjectDeclaration getValueObjectDeclaration();

}
