#include <stdio.h>

int main(void){

struct datum {
    int tag;
    int monat;
    int jahr;
    };
struct datum datum_a = {26, 3, 2019};
typedef struct datum Datum; //Definition eines Typs
Datum datum_b = {27, 3, 2019};
    
    printf("Datum: %d.%d.%d", datum_a.tag, datum_a.monat,datum_a.jahr);
}