// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.feakin.intellij.psi.impl.FeakinElementType;
import com.feakin.intellij.psi.impl.*;

public interface FeakinTypes {

  IElementType AGGREGATION_DECLARATION = new FeakinElementType("AGGREGATION_DECLARATION");
  IElementType AGGREGATION_NAME = new FeakinElementType("AGGREGATION_NAME");
  IElementType CONTEXT_DECLARATION = new FeakinElementType("CONTEXT_DECLARATION");
  IElementType CONTEXT_MAP_DECLARATION = new FeakinElementType("CONTEXT_MAP_DECLARATION");
  IElementType CONTEXT_MAP_NAME = new FeakinElementType("CONTEXT_MAP_NAME");
  IElementType CONTEXT_NAME = new FeakinElementType("CONTEXT_NAME");
  IElementType CONTEXT_NODE_NAME = new FeakinElementType("CONTEXT_NODE_NAME");
  IElementType DECLARATION = new FeakinElementType("DECLARATION");
  IElementType ENTITY_DECLARATION = new FeakinElementType("ENTITY_DECLARATION");
  IElementType ENTITY_NAME = new FeakinElementType("ENTITY_NAME");
  IElementType NAME_COMPONENT = new FeakinElementType("NAME_COMPONENT");
  IElementType VALUE_OBJECT_DECLARATION = new FeakinElementType("VALUE_OBJECT_DECLARATION");
  IElementType VALUE_OBJECT_NAME = new FeakinElementType("VALUE_OBJECT_NAME");

  IElementType AGGREGATION_KEYWORD = new FeakinTokenType("Aggregate");
  IElementType BLOCK_COMMENT = new FeakinTokenType("BLOCK_COMMENT");
  IElementType CLOSE_BRACE = new FeakinTokenType("}");
  IElementType COLON = new FeakinTokenType(":");
  IElementType COMMA = new FeakinTokenType(",");
  IElementType COMMENT = new FeakinTokenType("COMMENT");
  IElementType CONTEXT_KEYWORD = new FeakinTokenType("Context");
  IElementType CONTEXT_MAP_KEYWORD = new FeakinTokenType("ContextMap");
  IElementType DARROW = new FeakinTokenType("<->");
  IElementType DOUBLE_COLON = new FeakinTokenType("::");
  IElementType ENTITY_KEYWORD = new FeakinTokenType("Entity");
  IElementType EQUAL = new FeakinTokenType("=");
  IElementType IDENTIFIER = new FeakinTokenType("IDENTIFIER");
  IElementType LARROW = new FeakinTokenType("<-");
  IElementType LPAREN = new FeakinTokenType("(");
  IElementType OPEN_BRACE = new FeakinTokenType("{");
  IElementType QUOTA = new FeakinTokenType("'");
  IElementType RARROW = new FeakinTokenType("->");
  IElementType RPAREN = new FeakinTokenType(")");
  IElementType SEMICOLON = new FeakinTokenType(";");
  IElementType STRING_LITERAL = new FeakinTokenType("STRING_LITERAL");
  IElementType STRUCT_KEYWORD = new FeakinTokenType("Struct");
  IElementType VALUE_OBJECT_KEYWORD = new FeakinTokenType("ValueObject");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == AGGREGATION_DECLARATION) {
        return new FeakinAggregationDeclarationImpl(node);
      }
      else if (type == AGGREGATION_NAME) {
        return new FeakinAggregationNameImpl(node);
      }
      else if (type == CONTEXT_DECLARATION) {
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
      else if (type == CONTEXT_NODE_NAME) {
        return new FeakinContextNodeNameImpl(node);
      }
      else if (type == DECLARATION) {
        return new FeakinDeclarationImpl(node);
      }
      else if (type == ENTITY_DECLARATION) {
        return new FeakinEntityDeclarationImpl(node);
      }
      else if (type == ENTITY_NAME) {
        return new FeakinEntityNameImpl(node);
      }
      else if (type == NAME_COMPONENT) {
        return new FeakinNameComponentImpl(node);
      }
      else if (type == VALUE_OBJECT_DECLARATION) {
        return new FeakinValueObjectDeclarationImpl(node);
      }
      else if (type == VALUE_OBJECT_NAME) {
        return new FeakinValueObjectNameImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
