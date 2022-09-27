// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface FeakinContextMapDeclaration extends FeakinNamedElement {

  @NotNull
  FeakinContextMapName getContextMapName();

  @NotNull
  List<FeakinContextName> getContextNameList();

  @NotNull
  List<FeakinContextNodeName> getContextNodeNameList();

  @Nullable
  String getName();

  @Nullable
  ItemPresentation getPresentation();

}
