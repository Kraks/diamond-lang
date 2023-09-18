parser grammar DiamondParser;
options { tokenVocab=DiamondLexer; }

/* qualifiers */

qualElem :
  ID
| FRESH
;

qualElems :
  qualElem (',' qualElem)*
;

qual :
  FRESH
| ID
| '{' qualElems? '}'
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
  ID '(' paramList? ')' '=>' qty effs?
| '(' paramList? ')' '=>' qty effs?
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

/* effects */

eff :
  '@' ID '(' ID (',' ID)* ')' ;

effs :
  '@' ID
| eff+ ;

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

wrapExpr :
  '(' expr ')'
| '{' expr '}'
;

expr:
  ID
| value
| alloc
| deref
| move
| wrapExpr
// applications
| expr     '(' args? ')'
| expr '@' '(' args? ')'    // use at-sign to enforce fresh application
// type applications
| expr     '[' tyArgs ']'
| expr '@' '[' tyArgs ']'   // use at-sign to enforce fresh type application
// unary/binary operations
| op1 expr
| expr ('*' | '/') expr
| expr ('+' | '-') expr
| expr boolOp2 expr
// binding
| let
| funDef ';' expr
// assign
| expr ':=' expr
// conditional
| 'if' '(' expr ')' expr 'else' expr
;

alloc :
  'Ref' expr
;

deref :
  '!' expr
;

move :
  'move' expr
;

lam :
  ID '(' namedParamList? ')' (':' qty effs?)? RIGHTARROW '{' expr '}'
| '(' namedParamList? ')' (':' qty effs?)? RIGHTARROW '{' expr '}'
;

tyLam :
  ID '[' tyParamList ']' (':' qty)? RIGHTARROW '{' expr '}'
| '[' tyParamList ']' (':' qty)? RIGHTARROW '{' expr '}'
;

valDecl : VAL | TOPVAL;

let :
  valDecl ID '=' expr ';' expr
| valDecl idQty '=' expr ';' expr
;

/* definition */

funDef :
  DEF ID '(' namedParamList? ')' (':' qty effs?)? EQ expr                  #MonoFunDef
| DEF ID '[' tyParamList ']' '(' namedParamList? ')' (':' qty)? EQ expr    #PolyFunDef
;

/* top-level */

program : expr* EOF;
