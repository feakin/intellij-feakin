// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.feakin.intellij.psi.impl.FeakinElementType;
import com.feakin.intellij.psi.impl.*;

public interface FeakinTypes {

  IElementType DECLARATION = new FeakinElementType("DECLARATION");

  IElementType BLOCK_COMMENT = new FeakinTokenType("BLOCK_COMMENT");
  IElementType COMMENT = new FeakinTokenType("COMMENT");
  IElementType CONTEXT_KEYWORD = new FeakinTokenType("Context");
  IElementType CONTEXT_MAP_KEYWORD = new FeakinTokenType("ContextMap");
  IElementType IDENTIFIER = new FeakinTokenType("IDENTIFIER");
  IElementType STRING_LITERAL = new FeakinTokenType("STRING_LITERAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DECLARATION) {
        return new FeakinDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
