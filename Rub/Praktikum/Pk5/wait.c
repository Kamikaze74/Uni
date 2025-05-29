#include <stdio.h>
#include <unistd.h>
#include <signal.h>

int main() {
    
    sleep(3);

    printf("Sende SIGTERM an den eigenen Prozess...\n");
    kill(getpid(), SIGTERM);

    return 0;
}
