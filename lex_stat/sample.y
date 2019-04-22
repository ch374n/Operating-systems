%{
	#include <stdio.h>
	int yylex();
	void yyerror(char *);
%}
%%

%%
void yyerror(char *s) {
	printf("%s",s);
}