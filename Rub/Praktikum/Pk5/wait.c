#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

void handler(int signo) {

    if (signo == SIGTERM) {
        printf("SIGTERM empfangen. Beende das Programm.\n");
        exit(0);  // beendet das programm
    }
}

int main() {
    signal(SIGTERM, handler);  // wenn das programm SIGTERM erhält führe handler aus

    sleep(3);

    printf("Sende SIGTERM an mich selbst (PID %d)...\n", getpid());
    kill(getpid(), SIGTERM);

    printf("Signal nicht behandelt!\n");
    return 1;
}

