#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(void)
{
char text[] = "Hello World"; // Definition einer Zeichenkette
// Groesse wird automatisch bestimmt
printf("%s\n", text);
printf("Das erste C-Programm heisst: %s\n", text);
printf("%c%c\n", text[0], text[6]); // Ausgabe eines Zeichens
printf("Die Länge von '%s' ist %d\n", text, strlen(text));
printf("Die Speicheradresse ist %p\n", text);

return EXIT_SUCCESS;
}