package analizadorfinalmad;
import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}


/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"



/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}
Numero = -?[1-9][0-9]*.[[0-9]*[1-9]]?[e|E][-|+][1-9][0-9]* | -?[1-9][0-9]*[e|E][-|+][1-9][0-9]* | -?[1-9][0-9]*.[0-9]*[1-9] | -?[1-9][0-9]* | 0.0 | 0
Cadena = "\"" [^\"]* "\""
Caracter = "'" [^'\n\\'] "'"
letra=[a-zA-Z_]
digito=[-+]?[0-9]+|[-+]?[0-9]+
espacio=[ ,\t,\r]+
comillas=[\"]+
comillasS=[']+
simbolos=[\!,#,$,%,&,\(,\),*,\+,\,,\-,\.,/,:,;,<,=,>,?,@,\[,\],\^,_,`,{,|,},¿]+
ReservadasP= read|print|if|for|VERDADERO|FALSO|var


/*Tipos de Datos*/
ReservadasD= Entero|Bool|Char|Cadena

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

CaracterE = "'" [^'\n\\']* "'"
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }


/* Literal Cadena */
{Cadena} { return token(yytext(), "cadena", yyline, yycolumn);}

/* Literal Caracter */
{Caracter} { return token(yytext(), "char", yyline, yycolumn);}

{comillasS}({letra}|{digito}){comillasS} {return token(yytext(), "char", yyline, yycolumn);}
{comillas}({letra}|{digito}|{espacio}|{simbolos})*{comillas} {return token(yytext(), "cadena", yyline, yycolumn);}


/* Palabras Reservadas */
{ReservadasP} {return token(yytext(), "pReservada", yyline, yycolumn);}

/* Tipos de dato */
{ReservadasD} {return token(yytext(), "tipoDa", yyline, yycolumn);}

/* Identificador */
{Identificador} { return token(yytext(), "id", yyline, yycolumn);}

/* Numeros */
{Numero} { return token(yytext(), "num", yyline, yycolumn);}



/* c */
\( { return token(yytext(), "(", yyline, yycolumn); }
\) { return token(yytext(), ")", yyline, yycolumn); }
\{ { return token(yytext(), "{", yyline, yycolumn); }
\} { return token(yytext(), "}", yyline, yycolumn); }
\. { return token(yytext(), ".", yyline, yycolumn); }
\, { return token(yytext(), ",", yyline, yycolumn); }
\; { return token(yytext(), ";", yyline, yycolumn); }
\= { return token(yytext(), "=", yyline, yycolumn); }

[+] { return token(yytext(), "+", yyline, yycolumn); }
[-] { return token(yytext(), "-", yyline, yycolumn); }
[*] { return token(yytext(), "*", yyline, yycolumn); }
[/] { return token(yytext(), "/", yyline, yycolumn); }

[=][=] { return token(yytext(), "==", yyline, yycolumn); }
[!][=] { return token(yytext(), "!=", yyline, yycolumn); }
[-][-] { return token(yytext(), "--", yyline, yycolumn); }
[+][+] { return token(yytext(), "++", yyline, yycolumn); }
[<] { return token(yytext(), "<", yyline, yycolumn); }
[>] { return token(yytext(), ">", yyline, yycolumn); }
[<][=] { return token(yytext(), "<=", yyline, yycolumn); }
[>][=] { return token(yytext(), ">=", yyline, yycolumn); }

[&][&] { return token(yytext(), "@", yyline, yycolumn); }
[|][|] { return token(yytext(), "%", yyline, yycolumn); }
[!] { return token(yytext(), "?", yyline, yycolumn); }

[$] { return token(yytext(), "$", yyline, yycolumn);}


. { return token(yytext(), "ERROR", yyline, yycolumn); }
{Numero}{Identificador} { return token(yytext(), "ERROR", yyline, yycolumn); }
{Numero}{ReservadasP} { return token(yytext(), "ERROR", yyline, yycolumn); }
{Numero}{ReservadasD} { return token(yytext(), "ERROR", yyline, yycolumn); }

/* Numeros */
{CaracterE} { return token(yytext(), "ERROR", yyline, yycolumn);}




{comillas}({letra}|{digito}|{espacio}|{simbolos} | {ReservadasP}| {ReservadasD})* { return token(yytext(), "ERROR", yyline, yycolumn);}
{comillasS}({letra}|{digito})* { return token(yytext(), "ERROR", yyline, yycolumn);}