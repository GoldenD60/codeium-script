grammar codeium;

WS: [ \t\r\n]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

prog: namespace* EOF;

namespace: Namespace Ident '{' nsblock* '}';

nsblock: funcDef;

decl: type Ident;
type: Int
	| String
	| Selector;

scope: '{' instruction* return? '}';

args: expr (',' expr)*;

funcDef: Int Load '(' ')' scope					# LoadFunction
	   | decl '(' (decl (',' decl)*)? ')' scope	# TypeFunction;

funcCall: SelectorLit ('.' Ident)+ '(' args? ')'	# SelectorCall
		| Kill '(' args? ')'						# KillCall
		| Tell '(' args? ')'						# TellCall
		| Ident ('.' Ident)+ '(' args? ')'			# TypeCall;

expr: '(' expr ')'								# ParenExpr
	| expr op=('>>' | '<<') expr				# MinMaxExpr
	| expr op=('*' | '/' | '%') expr			# MultExpr
	| expr op=('+' | '-') expr					# AddSubExpr
	| expr op=( '>=' | '<=' | '>' | '<' ) expr	# CompExpr
	| expr op=( '==' | '!=' ) expr				# EqExpr
	| SelectorLit								# SelectorLitExpr
	| IntLit									# IntLitExpr
	| StrLit									# StrLitExpr
	| Ident										# IdentExpr;

instruction: scope															# InstScope
		   | decl '=' expr ';'												# InstDeclaration
		   | Ident op=('+=' | '-=' | '*=' | '/=' | '%=' | '=')? expr ';'	# InstAssignment
		   | funcCall ';'													# InstCall;
return: Return expr? ';';

Namespace: 'namespace';
Int: 'int';
String: 'string';
Load: 'load';
Return: 'return';
Selector: 'selector';
If: 'if';
Else: 'else';

Kill: 'kill';
Tell: 'tell';

Ident: [a-zA-Z_][a-zA-Z0-9_]*;
SelectorLit: '@' ('a' | 'e' | 'n' | 'p' | 'r' | 's');
IntLit: [0-9]+
	  | ('+' | '-') [0-9]+;
StrLit: '"' ( ~[\\"\n\r] | '\\' [\\"] )* '"'  ;