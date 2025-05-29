#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAXLINE 1024  // Define buffer size for input

int main(void)
{
char buf[MAXLINE];
pid_t pid;
int status;

printf("%% "); /* print prompt (printf requires %% to print %) */
while (fgets(buf, MAXLINE, stdin) != NULL) {
    buf[strlen(buf) - 1] = 0; /* replace newline with null */
    
    if ((pid = fork()) < 0) {
        perror("fork error");
        exit(1);
}     else if (pid == 0) { /* child */
        execlp(buf, buf, (char *) 0);
        printf("couldn't execute: %s\n", buf);
        exit(127);
}

    if ((pid = waitpid(pid, &status, 0)) < 0) { /* parent */
        perror("waitpid error");
        exit(1);
    }
    printf("%% ");
}
exit(0);
}