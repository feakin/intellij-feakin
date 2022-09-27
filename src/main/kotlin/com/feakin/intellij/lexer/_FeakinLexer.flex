package com.feakin.intellij.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.feakin.intellij.lexer.FeakinTypes.*;

%%

%{
  public _FeakinLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _FeakinLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT="//"[^\r\n]*
BLOCK_COMMENT=[/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
IDENTIFIER=[_a-zA-Z][_a-zA-Z0-9]*
STRING_LITERAL=\"([^\\\"\r\n]|\\[^\r\n])*\"?

%%
<YYINITIAL> {
  {WHITE_SPACE}         { return WHITE_SPACE; }

  ","                   { return COMMA; }
  ":"                   { return COLON; }
  "::"                  { return DOUBLE_COLON; }
  ";"                   { return SEMICOLON; }
  "{"                   { return OPEN_BRACE; }
  "}"                   { return CLOSE_BRACE; }
  "="                   { return EQUAL; }
  "'"                   { return QUOTA; }
  "("                   { return LPAREN; }
  ")"                   { return RPAREN; }
  "->"                  { return RARROW; }
  "<-"                  { return LARROW; }
  "<->"                 { return DARROW; }
  "ContextMap"          { return CONTEXT_MAP_KEYWORD; }
  "Context"             { return CONTEXT_KEYWORD; }
  "Aggregate"           { return AGGREGATION_KEYWORD; }
  "Entity"              { return ENTITY_KEYWORD; }
  "ValueObject"         { return VALUE_OBJECT_KEYWORD; }
  "Struct"              { return STRUCT_KEYWORD; }

  {COMMENT}             { return COMMENT; }
  {BLOCK_COMMENT}       { return BLOCK_COMMENT; }
  {IDENTIFIER}          { return IDENTIFIER; }
  {STRING_LITERAL}      { return STRING_LITERAL; }

}

[^] { return BAD_CHARACTER; }
