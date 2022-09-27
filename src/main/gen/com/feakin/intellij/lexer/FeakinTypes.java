// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.feakin.intellij.psi.impl.FeakinElementType;
import com.feakin.intellij.psi.impl.*;

public interface FeakinTypes {

  IElementType CONTEXT_DECLARATION = new FeakinElementType("CONTEXT_DECLARATION");
  IElementType CONTEXT_MAP_DECLARATION = new FeakinElementType("CONTEXT_MAP_DECLARATION");
  IElementType CONTEXT_MAP_NAME = new FeakinElementType("CONTEXT_MAP_NAME");
  IElementType CONTEXT_NAME = new FeakinElementType("CONTEXT_NAME");
  IElementType DECLARATION = new FeakinElementType("DECLARATION");
  IElementType NAME_COMPONENT = new FeakinElementType("NAME_COMPONENT");

  IElementType BLOCK_COMMENT = new FeakinTokenType("BLOCK_COMMENT");
  IElementType COMMENT = new FeakinTokenType("COMMENT");
  IElementType CONTEXT_KEYWORD = new FeakinTokenType("Context");
  IElementType CONTEXT_MAP_KEYWORD = new FeakinTokenType("ContextMap");
  IElementType IDENTIFIER = new FeakinTokenType("IDENTIFIER");
  IElementType STRING_LITERAL = new FeakinTokenType("STRING_LITERAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CONTEXT_DECLARATION) {
        return new FeakinContextDeclarationImpl(node);
      }
      else if (type == CONTEXT_MAP_DECLARATION) {
        return new FeakinContextMapDeclarationImpl(node);
      }
      else if (type == CONTEXT_MAP_NAME) {
        return new FeakinContextMapNameImpl(node);
      }
      else if (type == CONTEXT_NAME) {
        return new FeakinContextNameImpl(node);
      }
      else if (type == DECLARATION) {
        return new FeakinDeclarationImpl(node);
      }
      else if (type == NAME_COMPONENT) {
        return new FeakinNameComponentImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
