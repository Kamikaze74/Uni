/* Programm zum berechnen der Fakultät einer Zahl
  Aufruf: ./prog 7
  Zu erwartende Ausgabe: 5040
*/

#include <stdio.h>
#include <stdlib.h>

long fakul(long);

int main(int argc, char **args) {
  long zahl = 0;
  printf("Ergebnis: %ld \n", zahl);

  /* long = atol(char*)
    atol (array to long) wandelt eine Zeichenkette in C
    (entspricht einem char-pointer) in eine Zahl um.
  */
  
  zahl = atol(args[1]);
  printf("Ergebnis: %lu\n", zahl);

  zahl = fakul(zahl);

  printf("Ergebnis: %lu\n", zahl);

  return EXIT_SUCCESS;
}

long fakul(long zahl) {
  if (zahl == 0)
    return 1;
  else
    return fakul(zahl - 1) * zahl;
}