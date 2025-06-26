#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void handler(int signo) {
  if (signo == SIGINT) {
        printf("Empfangen: SIGINT (Strg+C)\n");
    } else if (signo == SIGTERM) {
        printf("Empfangen: SIGTERM\n");
    }
}

int main(void) {
  
  signal(SIGTERM, handler);  // abfangen von signalen
  signal(SIGINT, handler);

  printf("PID: %d – Sende SIGINT mit kill -SIGINT %d oder `kill -SIGTERM %d`\n", getpid(), getpid(), getpid());

  for ( ; ; ) {
    pause();
    puts("main: Signal empfangen");
  }
}
