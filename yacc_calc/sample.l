%{
		#include "y.tab.h"
		int yylex();
		void yyerror();
%}

digit [0-9]
%%

"sin"	{return SIN;}
"cos"	{return COS;}
"+"		{return '+';}
"-"		{return '-';}
"/"		{return '/';}
"*"		{return '*';}
"sqrt"  {return SQRT;}
"fact"  {return FACT;}
"^"		{return '^';}
{digit}+  {yylval.number = atoi(yytext); return INTEGER;}
{digit}*\.{digit}+   	{yylval.value = atof(yytext); return FLOAT;}
"exit" 	{return exit_command;}
";" 	{return ';';}
. {;}

%%
int yywrap() {
	return 0;
}
