#include <stdio.h>
int main(void)
{
char capt_firstname[] = "Jean Luc";
char capt_name[] = "Picard";
int ship_serial = 1701;
char ship_mod = 'D';
float max_speed = 8.0f;
// printf(...)
printf("NCC-%i-%c Warp %.1f Captain %s %s\n", ship_serial, ship_mod, max_speed, capt_firstname, capt_name);
}