%{
	#include <stdio.h>
	#include <math.h>
	int yylex();
	void yyerror();
%}
%union {
	int number;
	float value;
}
%start line
%token <number> INTEGER 
%token <value> FLOAT
%type <value> f s1 line
%token exit_command SIN COS SQRT FACT
%type <number> s2 i
%left '+' '-'
%left '*' '/'
%right '^'
%%
line : line s1 ';' {printf("result : %f\n", $2);}
	 | line s2 ';' {printf("result : %d\n", $2);}
     | s1 ';'	{printf("result : %f\n", $1);}
     | exit_command ';' {return 0;}
     | line exit_command ';' {return 0;}
     | s2 ';' {printf("result : %d\n", $1);}
     ;

s1 : f '+' f  {$$ = $1 + $3;}
		  | f '-' f  {$$ = $1 - $3;}
		  | f '*' f  {$$ = $1 * $3;}
		  | f '/' f  {$$ = $1 / $3;}
		  | f '^' f  {$$ = pow($1, $3);}
		  | SQRT f   {$$ = sqrt($2);}
		  | FACT f   {
		  				int fact = 1;
		  				for(int i = 1; i < $2; i++) {
		  					fact *= i;
		  				}	
		  				$$ = fact;
		  			 }
		  | SIN f        {$$ = sin($2 * 1.414 / 180);}
		  | COS f	     {$$ = cos($2 * 1.414 / 180);}
		  ;

s2 : i '+' i  {$$ = $1 + $3;}
		  | i '-' i  {$$ = $1 - $3;}
		  | i '*' i  {$$ = $1 * $3;}
		  | i '/' i  {$$ = $1 / $3;}
		  | i '^' i  {$$ = pow($1, $3);}
		  | SQRT i   {$$ = sqrt($2);}
		  | FACT i   {
		  				int fact = 1;
		  				for(int i = 1; i <= $2; i++) {
		  					fact *= i;
		  				}	
		  				$$ = fact;
		  			 }

		  | SIN i        {$$ = sin($2 * 1.414 / 180);}
		  | COS i	     {$$ = cos($2 * 1.414 / 180);}
		  ;

f : FLOAT 
  ;		  

i : INTEGER
  ;
%%
int main() {
	printf("\t\t\t\tinput : sin 20;\n\t\t\t\t1.5 - 0.5; etc.\n");
	printf("\t\t\t\tenter 'exit;' to terminate...\n");

	return yyparse();
}

void yyerror(char *s) {
	printf("%s", s);
}