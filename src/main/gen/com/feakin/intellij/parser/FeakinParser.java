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
  // CONTEXT_KEYWORD name_component '{' contextBody '}'
  public static boolean contextDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextDeclaration")) return false;
    if (!nextTokenIs(b, CONTEXT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTEXT_KEYWORD);
    r = r && name_component(b, l + 1);
    r = r && consumeToken(b, "{");
    r = r && contextBody(b, l + 1);
    r = r && consumeToken(b, "}");
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
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, ":");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, ";");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CONTEXT_MAP_KEYWORD name_component '{' contextMapEntry* '}'
  public static boolean contextMapDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapDeclaration")) return false;
    if (!nextTokenIs(b, CONTEXT_MAP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTEXT_MAP_KEYWORD);
    r = r && name_component(b, l + 1);
    r = r && consumeToken(b, "{");
    r = r && contextMapDeclaration_3(b, l + 1);
    r = r && consumeToken(b, "}");
    exit_section_(b, m, CONTEXT_MAP_DECLARATION, r);
    return r;
  }

  // contextMapEntry*
  private static boolean contextMapDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapDeclaration_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextMapEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contextMapDeclaration_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER ';'
  static boolean contextMapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapEntry")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, ":");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, ";");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // contextDeclaration
  //                | contextMapDeclaration
  public static boolean declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration")) return false;
    if (!nextTokenIs(b, "<declaration>", CONTEXT_KEYWORD, CONTEXT_MAP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECLARATION, "<declaration>");
    r = contextDeclaration(b, l + 1);
    if (!r) r = contextMapDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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

}
