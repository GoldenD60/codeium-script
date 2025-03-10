grammar codeium;

WS: [ \t\r\n]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
QUOTED_STR: '"' ( ~[\\"\n\r] | '\\' [\\"] )* '"'  ;
IDENT: [a-zA-Z_][a-zA-Z0-9_]*;
SELECTOR: '@' ('s' | 'a');
INT: [0-9]+
   | ('+' | '-') [0-9]+;

declaration: IDENT IDENT;

prog: namespace*
	| fileNamespace toplevel*;

expr:   expr ('*'|'/') expr # MULDIV
    |   expr ('+'|'-') expr # ADDSUB
    |   INT					# INT
    |   QUOTED_STR			# STR
    |   IDENT				# IDENT
    |	SELECTOR			# SELECTOR
    |   '(' expr ')'		# PAREN;

fileNamespace: 'namespace' IDENT ';';
namespace: 'namespace' IDENT '{' toplevel* '}';
toplevel: funcDef;

funcDef: declaration '(' (declaration (',' declaration)*)? ')' '{' instruction* '}';

instruction: (IDENT | SELECTOR) ('.' IDENT)* '(' (expr (',' expr)*)? ')' ';';