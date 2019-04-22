%{
		int yylex();
		void yyerror(char *);
		#include "y.tab.h"
		#include <string.h>
%}
digit [0-9]
letter [a-zA-z]
%%
"exit" 		{return exit_command;}
"int"		{return INT;}
"float" 	{return FLOAT;}
"boolean" 	{return BOOL;}
"double"	{return DOUBLE;}
"char"		{return CHAR;}
{letter}({digit}|{letter})* { strcpy(yylval.value, yytext); return ID;}
";"|"," 	{return yytext[0];}
%%
int yywrap() {
	return 1;
}

