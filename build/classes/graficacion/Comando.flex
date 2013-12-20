package graficacion;

import java_cup.runtime.*;

%%  
%class Autocad
%unicode
%cup
%line
%column



LineTerminator =  \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

%%

[cC][oO][mM][aA][nN][dD][oO][sS]            { return new Symbol (sym.COMANDO, yyline, yycolumn );}

[tT][eE][xX][tT][oO]                        { return new Symbol (sym.TEXTO, yyline, yycolumn );}
[tT][rR][iI][aA][nN][gG][uU][lL][oO]        { return new Symbol (sym.TRIANGULO, yyline, yycolumn );}
[lL][iI][nN][eE][aA]                        { return new Symbol (sym.LINEA, yyline, yycolumn );}
[cC][iI][rR][cC][uU][lL][oO]                { return new Symbol (sym.CIRCULO, yyline, yycolumn );}
[cC][uU][aA][dD][rR][aA][dD][oO]            { return new Symbol (sym.CUADRADO, yyline, yycolumn );}
[pP][uU][nN][tT][oO]                        { return new Symbol (sym.PUNTO, yyline, yycolumn );}
[iI][mM][aA][gG][eE][nN]                    { return new Symbol (sym.IMAGEN, yyline, yycolumn );}

[lL][iI][sS][tT][aA]                        { return new Symbol (sym.LISTA, yyline, yycolumn );}
[vV][iI][sS][tT][aA]                        { return new Symbol (sym.VISTA, yyline, yycolumn );}

[sS][eE][lL][eE][cC][cC][iI][oO][nN][aA][rR] { return new Symbol (sym.SELECCIONAR, yyline, yycolumn );}
[rR][oO][tT][aA][rR]                         { return new Symbol (sym.ROTAR, yyline, yycolumn );}
[tT][rR][aA][sS][lL][aA][dD][aA][rR]         { return new Symbol (sym.TRASLADAR, yyline, yycolumn );}
[eE][sS][cC][aA][lL][aA][rR]                 { return new Symbol (sym.ESCALAR, yyline, yycolumn );}
[aA][gG][rRuU][pP][aA][rR]                   { return new Symbol (sym.AGRUPAR, yyline, yycolumn );}
[dD][eE][sS][aA][gG][rR][uU][pP][aA][rR]       { return new Symbol (sym.DESAGRUPAR, yyline, yycolumn );}
[dD][eE][sS][hH][aA][cC][eE][rR]             { return new Symbol (sym.DESHACER, yyline, yycolumn );}
[kK][oO][cC][hH]                             { return new Symbol (sym.KOCH, yyline, yycolumn );}
[sS][iI][eE][rR][pP][iI][nN][sS][kK][iI]     { return new Symbol (sym.SIERPINSKI, yyline, yycolumn );}
Sierpinski

[cC][oO][Ll][oO][rR]                         { return new Symbol (sym.COLOR, yyline, yycolumn );}
[aA][zZ][uU][lL]                             { return new Symbol (sym.AZUL, yyline, yycolumn );}
[rR][oO][jJ][oO]                             { return new Symbol (sym.ROJO, yyline, yycolumn );}
[nN][eE][gG][rR][oO]                         { return new Symbol (sym.NEGRO, yyline, yycolumn );}
[nN][aA][rR][aA][nN][jJ][aA]                 { return new Symbol (sym.NARANJA, yyline, yycolumn );}
[gG][rR][iI][sS]                             { return new Symbol (sym.GRIS, yyline, yycolumn );}
[rR][oO][sS][aA]                             { return new Symbol (sym.ROSA, yyline, yycolumn );}
[bB][lL][aA][nN][cC][oO]                     { return new Symbol (sym.BLANCO, yyline, yycolumn );}
[aA][mM][aA][rR][iI][lL][lL][oO]             { return new Symbol (sym.AMARILLO, yyline, yycolumn );}
    
[vV]                                         { return new Symbol (sym.V, yyline, yycolumn );}
[nN][vV]                                     { return new Symbol (sym.NV, yyline, yycolumn );}
-?[0-9][0-9]*(\.[0-9][0-9]*)?                {return new Symbol(sym.NUMERO, yyline, yycolumn, new Double(yytext())  );}
\".*\"                                       { return new Symbol(sym.CADENA, yyline, yycolumn, new String(yytext().substring(1, yytext().length() - 1))); }
\, { return new Symbol (sym.COMA, yyline, yycolumn );}

{WhiteSpace} {}
. {}
