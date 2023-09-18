lexer grammar DiamondLexer;

/* lexer */

AT : '@';
EXCLA : '!';
COLON : ':';
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
TOPVAL : 'topval';
IN : 'in';
DEF : 'def';
REF : 'Ref';
FORALL : 'forall';
SUBTYPE : '<:';
UNIT : 'unit';
LAM : 'lam' ;
TYLAM : 'Lam';
IF : 'if';
ELSE : 'else';
MOVE : 'move';

AND : '&&' ;
OR : '||' ;
NOT : '~' ;
EQ : '=' ;
EQ2 : '==';
NEQ : '!=' ;
ADD : '+';
MINUS : '-';
MULT : '*';
DIV : '/';

INT : [0-9]+ ;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
WS: [ \t\n\r\f]+ -> skip ;

COMMENT: '//' ~[\r\n]* -> skip;
