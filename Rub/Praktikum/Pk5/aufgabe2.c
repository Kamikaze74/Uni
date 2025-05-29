#include <stdio.h>
#include <signal.h>
#include <unistd.h>

int main(void) {
  
  for ( ; ; ) {
    pause();
    puts("main: Signal empfangen");
  }
}
