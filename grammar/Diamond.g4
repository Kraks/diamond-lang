grammar DiamondParser;

program : def* expr* EOF;

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
| EQ
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
// applications
| expr  '(' expr (',' expr)* ')'
| expr '@(' expr (',' expr)* ')'
// type applications
| expr  '[' qty ']'
| expr '@[' qty ']'
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

tyLam :
  TYLAM ID '[' ID ']' (':' qty)? '{' expr '}'
| TYLAM '[' ID ']' (':' qty)? '{' expr '}'
| TYLAM ID '[' ID '<:' ty ']' (':' qty)? '{' expr '}'
| TYLAM '[' ID '<:' ty ']' (':' qty)? '{' expr '}'
| TYLAM ID '[' ID '^' ID '<:' qty ']' (':' qty)? '{' expr '}'
| TYLAM '[' ID '^' ID '<:' qty ']' (':' qty)? '{' expr '}'
;

let :
  VAL ID '=' expr ';' expr
| VAL idQty '=' expr ';' expr
;

/* definition */

def :
  DEF ID '(' namedArgList? ')' (':' qty)? '{' expr '}'
| DEF ID '[' ID ']' '(' namedArgList? ')' (':' qty)? '{' expr '}'
| DEF ID '[' ID '<:' ty ']' '(' namedArgList? ')' (':' qty)? '{' expr '}'
| DEF ID '[' ID '^' ID '<:' qty ']' '(' namedArgList? ')' (':' qty)? '{' expr '}'
;

/* lexer */

EXCLA : '!';
COLONEQ : ':=';
HAT : '^';
FRESH : '<>' | '♦';
COMMA : ',' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;
LBRACKET : '[';
RBRACKET : ']';
RIGHTARROW : '=>';

TRUE : 'true';
FALSE : 'false';

VAL : 'val';
IN : 'in';
DEF : 'def';
REF : 'ref';
TOP : 'top';
FORALL : 'forall';
SUBTYPE : '<:';
UNIT : '()';
LAM : 'lambda' ;
TYLAM : 'Lambda';

AND : '&&' ;
OR : '||' ;
NOT : '~' ;
EQ : '=' ;
NEQ : '!=' ;

INT : [0-9]+ ;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
WS: [ \t\n\r\f]+ -> skip ;
