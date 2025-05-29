/* Datei: hello.c */
#include <stdio.h>
/* Definition der Funktion vor dem ersten Aufruf */
void hello(char name[])
{
printf("Hello %s\n", name);
}
int main(void)
{
hello("World");
}