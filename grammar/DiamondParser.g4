parser grammar DiamondParser;
options { tokenVocab=DiamondLexer; }

/* qualifiers */

qualElem :
  ID
| FRESH
;

qualElems :
  '{' qualElem (',' qualElem)* '}'
;

qual :
  FRESH
| ID
| qualElems?
;

/* types */

idQty : ID ':' qty;

arg :
  qty
| idQty
;

argList :
  arg (',' arg)*
;

namedArgList :
  idQty (',' idQty)*
;

funTy :
  ID '(' argList? ')' '=>' qty
| '(' argList? ')' '=>' qty
;

ty :
  ID // int, unit, top, bool, top, type variable
| funTy
| REF '(' qty ')'
| FORALL ID (ID '<:' ty) qty
| FORALL ID (ID '^' ID '<:' qty) qty
;

qty :
  ty
| ty '^' qual
;

/* expressions */

op2:
  AND
| OR
| EQ2
| NEQ
| '+'
| '-'
| '*'
| '/'
;

op1:
  NOT
;

value :
  TRUE
| FALSE
| UNIT
| INT
| lam
| tyLam
;

expr:
  ID
| value
| op1 expr
| expr op2 expr
| let
| alloc
| assign
| deref
| '(' expr ')'
| '{' expr '}'
// applications
| expr     '(' expr (',' expr)* ')'
| expr '@' '(' expr (',' expr)* ')'
// type applications
| expr     '[' qty ']'
| expr '@' '[' qty ']'
;

alloc :
  'ref' expr
;

assign :
  ID ':=' expr
;

deref :
  '!' expr
;

lam :
  LAM ID '(' namedArgList? ')' (':' qty)? '{' expr '}'
| LAM '(' namedArgList? ')' (':' qty)? '{' expr '}'
;

tyParam :
  ID
| ID '<:' ty
| ID '^' ID '<:' qty
;

tyParamList :
  tyParam (',' tyParam)*
;

tyLam :
  TYLAM ID '[' tyParamList ']' (':' qty)? '{' expr '}'
| TYLAM '[' tyParamList ']' (':' qty)? '{' expr '}'
;

let :
  VAL ID '=' expr ';' expr
| VAL idQty '=' expr ';' expr
;

/* definition */

def :
  DEF ID '(' namedArgList? ')' (':' qty)? EQ expr
| DEF ID '[' tyParamList ']' '(' namedArgList? ')' (':' qty)? EQ expr
;

/* top-level */

program : ( def | expr )* EOF;
