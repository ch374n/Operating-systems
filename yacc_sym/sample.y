%{
	#include <stdio.h>
	#include <string.h>
	void yyerror(char *);
	int yylex();

	struct symbol_table {
		char dtype[20];
		char id[20];
	}sym[20];


	char queue[20][20];
	int  q_ptr = 0, sym_ptr = 0;

	void insert(char *);
%}

%union {
	char value[20];
}
%token INT FLOAT BOOL DOUBLE CHAR
%token <value> ID
%start line 
%type <value> data_type identifier statement line
%token exit_command
%%
line : line statement 
	 | statement 
	 | line exit_command ';' {return 0;}
	 | exit_command  ';' {return 0;}
	 ;

statement : data_type identifier ';' {
									insert($1);	 
									q_ptr = 0;
								 }
		  ;

data_type : INT 	{strcpy($$, "int");}
	      | FLOAT 	{strcpy($$, "float");}
	      | BOOL 	{strcpy($$, "bool");}
	      | DOUBLE 	{strcpy($$, "double");}
	      | CHAR 	{strcpy($$, "char");}
	   	  ;

identifier : identifier ',' ID  {
									strcpy(queue[q_ptr++], $3);
								}
		   | ID  				{
		   							strcpy(queue[q_ptr++], $1);
		   						}
 		   ;	   	  
%%

void insert(char dtype[20]) {
	for(int i = 0; i < q_ptr; i++) {
		strcpy(sym[sym_ptr].dtype, dtype);
		strcpy(sym[sym_ptr].id, queue[i]);
		sym_ptr++;
	}
}

void print_table() {
	printf("\nDATA TYPE\t\tSYMBOL\n");
	for(int i = 0; i < sym_ptr; i++) {
		printf("%s\t\t%s\n", sym[i].dtype, sym[i].id);
	}
}

int main() {
	printf("\t\t\t\tinput : int a;\n\t\t\t\tfloat b, c; etc.\n");
	printf("\t\t\t\tenter 'exit;' to terminate...\n");
	yyparse();
	print_table();
}

void yyerror(char *s) {
	printf("%s", s);
}
