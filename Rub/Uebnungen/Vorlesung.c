#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
int main() {
    int v1 = 0, v2 = 0, v3 = 0, pid1, pid2, pid3;
    pid1 = fork();
    pid2 = getpid();
    v2++;
    pid3 = fork();
    if (pid1 == pid3) {
    v1++;
    v2++;
    } else if (pid2 == getppid()) {
    v1++;
    v3++;
    sleep(1);
    } else if (pid2 == getpid()) {
    v2++;
    wait(NULL);
    if (pid1 > 0) {
    v3++;
    wait(NULL);
    }
    }
    printf("%d %d %d\n", v1, v2, v3);
    }