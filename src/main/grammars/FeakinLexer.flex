package com.feakin.intellij.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.feakin.intellij.lexer.FkElementTypes.*;

import static com.feakin.intellij.parser.FkParserDefinition.*;

%%

%{
  public _FkLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _FkLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%s INNER_BLOCK_DOC


%{}
  /**
    * '#+' stride demarking start/end of raw string/byte literal
    */
  private int zzShaStride = -1;

  /**
    * Dedicated storage for starting position of some previously successful
    * match
    */
  private int zzPostponedMarkedPos = -1;

  /**
    * Dedicated nested-comment level counter
    */
  private int zzNestedCommentLevel = 0;
%}

%{
  IElementType imbueBlockComment() {
      assert(zzNestedCommentLevel == 0);
      yybegin(YYINITIAL);

      zzStartRead = zzPostponedMarkedPos;
      zzPostponedMarkedPos = -1;

      System.out.println((yylength()) + " " + yytext());
      if (yylength() >= 3) {
          if (yycharat(2) == '"') {
              return INNER_BLOCK_DOC_COMMENT;
          }
      }

      return BLOCK_COMMENT;
  }
%}

EOL=\R
WHITE_SPACE=\s+

COMMENT="//"[^\r\n]*
BLOCK_COMMENT=[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
IDENTIFIER=[_a-zA-Z][_a-zA-Z0-9]*
STRING_LITERAL=\"([^\\\"\r\n]|\\[^\r\n])*\"?

EOL_WS           = \n | \r | \r\n
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+

%%
<YYINITIAL> {
  {WHITE_SPACE}         { return WHITE_SPACE; }
  "\"\"\""              { yybegin(INNER_BLOCK_DOC); yypushback(3); }

  ","                   { return COMMA; }
  ":"                   { return COLON; }
  "::"                  { return DOUBLE_COLON; }
  ";"                   { return SEMICOLON; }
  "{"                   { return LBRACE; }
  "}"                   { return RBRACE; }
  "="                   { return EQUAL; }
  "'"                   { return QUOTA; }
  "("                   { return LPAREN; }
  ")"                   { return RPAREN; }
  "<"                   { return LT; }
  ">"                   { return GT; }
  "."                   { return DOT; }
  "->"                  { return RARROW; }
  "<-"                  { return LARROW; }
  "<->"                 { return DARROW; }
  "--"                  { return CONNECTION; }
  "ContextMap"          { return CONTEXT_MAP_KEYWORD; }
  "Context"             { return CONTEXT_KEYWORD; }
  "Aggregate"           { return AGGREGATE_KEYWORD; }
  "Entity"              { return ENTITY_KEYWORD; }
  "ValueObject"         { return VALUE_OBJECT_KEYWORD; }
  "Struct"              { return STRUCT_KEYWORD; }
  "impl"                { return IMPL_KEYWORD; }
  "endpoint"            { return ENDPOINT_KEYWORD; }
  "request"             { return REQUEST_KEYWORD; }
  "response"            { return RESPONSE_KEYWORD; }
  "aggregate"           { return AGGREGATE_S_KEYWORD; }
  "entity"              { return ENTITY_S_KEYWORD; }
  "GET"                 { return GET_KEYWORD; }
  "POST"                { return POST_KEYWORD; }
  "PUT"                 { return PUT_KEYWORD; }
  "DELETE"              { return DELETE_KEYWORD; }
  "PATCH"               { return PATCH_KEYWORD; }
  "HEAD"                { return HEAD_KEYWORD; }
  "OPTIONS"             { return OPTIONS_KEYWORD; }
  "authorization"       { return AUTH_KEYWORD; }
  "flow"                { return FLOW_KEYWORD; }
  "via"                 { return VIA_KEYWORD; }
  "receive"             { return RECEIVE_KEYWORD; }
  "send"                { return SEND_KEYWORD; }
  "to"                  { return TO_KEYWORD; }
  "from"                { return FROM_KEYWORD; }
  "layered"             { return LAYERED_KEYWORD; }
  "layer"               { return LAYER_KEYWORD; }
  "dependency"          { return DEPENDENCY_KEYWORD; }
  "package"             { return PACKAGE_KEYWORD; }

  {COMMENT}             { return COMMENT; }
  {BLOCK_COMMENT}       { return BLOCK_COMMENT; }
  {IDENTIFIER}          { return IDENTIFIER; }
  {STRING_LITERAL}      { return STRING_LITERAL; }

}

<INNER_BLOCK_DOC> {
  "\"\"\""    { if (zzNestedCommentLevel++ == 0)
                zzPostponedMarkedPos = zzStartRead;
              }

  "\"\"\""    { if (--zzNestedCommentLevel == 0)
                return imbueBlockComment();
              }

  <<EOF>> { zzNestedCommentLevel = 0; return imbueBlockComment(); }

  [^]     { }
}

[^] { return BAD_CHARACTER; }
