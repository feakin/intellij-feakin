// This is a generated file. Not intended for manual editing.
package com.feakin.intellij.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.feakin.intellij.lexer.FeakinTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class FeakinParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return compilationUnit(b, l + 1);
  }

  /* ********************************************************** */
  // aggregationEntry*
  static boolean aggregationBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregationBody")) return false;
    while (true) {
      int c = current_position_(b);
      if (!aggregationEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "aggregationBody", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // AGGREGATION_KEYWORD aggregationName OPEN_BRACE aggregationBody CLOSE_BRACE
  public static boolean aggregationDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregationDeclaration")) return false;
    if (!nextTokenIs(b, AGGREGATION_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AGGREGATION_KEYWORD);
    r = r && aggregationName(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && aggregationBody(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, AGGREGATION_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER ';'
  //   | IDENTIFIER ':' IDENTIFIER '[' IDENTIFIER ']'
  static boolean aggregationEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregationEntry")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, IDENTIFIER, COLON, IDENTIFIER, SEMICOLON);
    if (!r) r = aggregationEntry_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER ':' IDENTIFIER '[' IDENTIFIER ']'
  private static boolean aggregationEntry_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregationEntry_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, IDENTIFIER);
    r = r && consumeToken(b, "[");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "]");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean aggregationName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregationName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, AGGREGATION_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // declaration*
  static boolean compilationUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit")) return false;
    while (true) {
      int c = current_position_(b);
      if (!declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "compilationUnit", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // contextEntry*
  static boolean contextBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextBody")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contextBody", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CONTEXT_KEYWORD contextName OPEN_BRACE contextBody CLOSE_BRACE
  public static boolean contextDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextDeclaration")) return false;
    if (!nextTokenIs(b, CONTEXT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTEXT_KEYWORD);
    r = r && contextName(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && contextBody(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, CONTEXT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER ';'
  static boolean contextEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextEntry")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, IDENTIFIER, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CONTEXT_MAP_KEYWORD contextMapName OPEN_BRACE (contextNodeDecl | contextNodeRel)* CLOSE_BRACE
  public static boolean contextMapDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapDeclaration")) return false;
    if (!nextTokenIs(b, CONTEXT_MAP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTEXT_MAP_KEYWORD);
    r = r && contextMapName(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && contextMapDeclaration_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, CONTEXT_MAP_DECLARATION, r);
    return r;
  }

  // (contextNodeDecl | contextNodeRel)*
  private static boolean contextMapDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapDeclaration_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextMapDeclaration_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contextMapDeclaration_3", c)) break;
    }
    return true;
  }

  // contextNodeDecl | contextNodeRel
  private static boolean contextMapDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapDeclaration_3_0")) return false;
    boolean r;
    r = contextNodeDecl(b, l + 1);
    if (!r) r = contextNodeRel(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean contextMapName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, CONTEXT_MAP_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean contextName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, CONTEXT_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // CONTEXT_KEYWORD contextName  (COMMA contextName)*
  static boolean contextNodeDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeDecl")) return false;
    if (!nextTokenIs(b, CONTEXT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTEXT_KEYWORD);
    r = r && contextName(b, l + 1);
    r = r && contextNodeDecl_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA contextName)*
  private static boolean contextNodeDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeDecl_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextNodeDecl_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contextNodeDecl_2", c)) break;
    }
    return true;
  }

  // COMMA contextName
  private static boolean contextNodeDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeDecl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && contextName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean contextNodeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, CONTEXT_NODE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // contextNodeName (DARROW | LARROW | RARROW) contextNodeName SEMICOLON
  static boolean contextNodeRel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeRel")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = contextNodeName(b, l + 1);
    r = r && contextNodeRel_1(b, l + 1);
    r = r && contextNodeName(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // DARROW | LARROW | RARROW
  private static boolean contextNodeRel_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextNodeRel_1")) return false;
    boolean r;
    r = consumeToken(b, DARROW);
    if (!r) r = consumeToken(b, LARROW);
    if (!r) r = consumeToken(b, RARROW);
    return r;
  }

  /* ********************************************************** */
  // contextMapDeclaration
  //                | contextDeclaration
  //                | aggregationDeclaration
  //                | entityDeclaration
  //                | valueObjectDeclaration
  public static boolean declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECLARATION, "<declaration>");
    r = contextMapDeclaration(b, l + 1);
    if (!r) r = contextDeclaration(b, l + 1);
    if (!r) r = aggregationDeclaration(b, l + 1);
    if (!r) r = entityDeclaration(b, l + 1);
    if (!r) r = valueObjectDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // entityEntry*
  static boolean entityBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBody")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entityEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entityBody", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ENTITY_KEYWORD entityName OPEN_BRACE entityBody CLOSE_BRACE
  public static boolean entityDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityDeclaration")) return false;
    if (!nextTokenIs(b, ENTITY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENTITY_KEYWORD);
    r = r && entityName(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && entityBody(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, ENTITY_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER ';'
  static boolean entityEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityEntry")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, IDENTIFIER, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean entityName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, ENTITY_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean name_component(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "name_component")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, NAME_COMPONENT, r);
    return r;
  }

  /* ********************************************************** */
  // valueObjectEntry*
  static boolean valueObjectBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "valueObjectBody")) return false;
    while (true) {
      int c = current_position_(b);
      if (!valueObjectEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "valueObjectBody", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // VALUE_OBJECT_KEYWORD valueObjectName OPEN_BRACE valueObjectBody CLOSE_BRACE
  public static boolean valueObjectDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "valueObjectDeclaration")) return false;
    if (!nextTokenIs(b, VALUE_OBJECT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VALUE_OBJECT_KEYWORD);
    r = r && valueObjectName(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && valueObjectBody(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, VALUE_OBJECT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER ';'
  static boolean valueObjectEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "valueObjectEntry")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, IDENTIFIER, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // name_component
  public static boolean valueObjectName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "valueObjectName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_component(b, l + 1);
    exit_section_(b, m, VALUE_OBJECT_NAME, r);
    return r;
  }

}
