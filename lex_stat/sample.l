%{
		int yylex();
		void yyerror(char *);
%}
sentence ['a-zA-Z \t,]*
%%
{sentence}"for"{sentence}"." 	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"and"{sentence}"."	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"nor"{sentence}"."	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"but"{sentence}"."	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"or"{sentence}"."		{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"yet"{sentence}"."	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"so"{sentence}"."		{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"still"{sentence}"."	{printf("\ncompound sentence : %s\n", yytext);}
{sentence}"not only"{sentence}"but also"{sentence} {printf("compound sentence : %s", yytext);}
{sentence}"neither"{sentence}"nor"{sentence} 	   {printf("compound sentence : %s", yytext);}
{sentence}"either"{sentence}"or"{sentence} 		   {printf("compound sentence : %s", yytext);}
.*												   {printf("\nsimple sentence : %s\n", yytext);}
%%
int yywrap() {
	return 1;
}

