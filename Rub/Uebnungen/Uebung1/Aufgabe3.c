#include <stdio.h>
#include <string.h>
int main(void) {

char name[10];
printf("Wie heisst Du? ");
scanf("%9s", name);

if (strcmp(name, "Luke") == 0)
{
    printf("Hallo Luke, die Macht sei mit Dir!\n");
}
int groesse_cm;
printf("Wie gross bist Du (in cm)? ");
scanf("%d", &groesse_cm);

float groesse_m = groesse_cm / 100.0;
printf("Ah, das sind exakt %.2f m!", groesse_m);
return 0;
}