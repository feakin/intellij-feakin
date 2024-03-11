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
INLINE_DOC       = [\"][\"][^\"]*[\"]+([^\"]*[\"][\"]+)*

SUFFIX        = {IDENTIFIER}
EXPONENT      = [eE] [-+]? [0-9_]+

INT_LITERAL = ( {DEC_LITERAL}
              | {HEX_LITERAL}
              | {OCT_LITERAL}
              | {BIN_LITERAL} ) {EXPONENT}? {SUFFIX}?

DEC_LITERAL = [0-9] [0-9_]*
HEX_LITERAL = "0x" [a-fA-F0-9_]*
OCT_LITERAL = "0o" [0-7_]*
BIN_LITERAL = "0b" [01_]*


%%
<YYINITIAL> {
  {WHITE_SPACE}         { return WHITE_SPACE; }
  {INLINE_DOC}          { return INLINE_DOC; }

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
  "struct"              { return STRUCT_KEYWORD; }
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
  "DomainEvent"         { return DOMAIN_EVENT_KEYWORD; }
  "SourceSet"           { return SOURCE_SET_KEYWORD; }
  "when"                { return WHEN_KEYWORD; }
  "is"                  { return IS_KEYWORD; }
  "var"                 { return VAR_KEYWORD; }
  "def"                 { return DEF_KEYWORD; }
  "include"             { return INCLUDE_KEYWORD; }
  "env"                 { return ENV_KEYWORD; }
  "datasource"          { return DATASOURCE_KEYWORD; }
  "server"              { return SERVER_KEYWORD; }

  "true"|"false"        { return BOOL_LITERAL; }
  {INT_LITERAL}         { return INTEGER_LITERAL; }

  {COMMENT}             { return COMMENT; }
  {BLOCK_COMMENT}       { return BLOCK_COMMENT; }
  {IDENTIFIER}          { return IDENTIFIER; }
  {STRING_LITERAL}      { return STRING_LITERAL; }

}

[^] { return BAD_CHARACTER; }
