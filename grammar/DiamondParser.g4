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

param :
  qty
| idQty
;

paramList :
  param (',' param)*
;

namedParamList :
  idQty (',' idQty)*
;

funTy :
  ID '(' paramList? ')' '=>' qty
| '(' paramList? ')' '=>' qty
;

tyParam :
  ID
| ID '<:' ty
| ID '^' ID '<:' qty
;

tyParamList :
  tyParam (',' tyParam)*
;

tyFunTy :
  FORALL '[' tyParamList ']' '=>' qty
| FORALL ID '[' tyParamList ']' '=>' qty
;

ty :
  ID // Int, Unit, Top, Bool, type variable
| REF '[' qty ']'
| funTy
| tyFunTy
| '(' ty ')'
;

qty :
  ty
| ty '^' qual
;

/* expressions */

boolOp2:
  AND
| OR
| EQ2
| NEQ
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

args :
  expr (',' expr)*
;

tyArgs :
  qty (','  qty)*
;

expr:
  ID
| value
| op1 expr
| expr ('*' | '/') expr
| expr ('+' | '-') expr
| expr boolOp2 expr
| alloc
| deref
| '(' expr ')'
| '{' expr '}'
// applications
| expr     '(' args? ')'
| expr '@' '(' args? ')'
// type applications
| expr     '[' tyArgs ']'
| expr '@' '[' tyArgs ']'
// binding
| let
| funDef ';' expr
// assign
| expr ':=' expr
;

alloc :
  'Ref' expr
;

deref :
  '!' expr
;

lam :
  ID '(' namedParamList? ')' (':' qty)? RIGHTARROW '{' expr '}'
| '(' namedParamList? ')' (':' qty)? RIGHTARROW '{' expr '}'
;

tyLam :
  ID '[' tyParamList ']' (':' qty)? '{' expr '}'
| '[' tyParamList ']' (':' qty)? '{' expr '}'
;

valDecl : VAL | TOPVAL;

let :
  valDecl ID '=' expr ';' expr
| valDecl idQty '=' expr ';' expr
;

/* definition */

funDef :
  DEF ID '(' namedParamList? ')' (':' qty)? EQ expr                     #MonoFunDef
| DEF ID '[' tyParamList ']' '(' namedParamList? ')' (':' qty)? EQ expr #PolyFunDef
;

/* top-level */

program : expr* EOF;
