#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
int main(void)

{
pid_t pid;
int status;
printf("Programm gestartet\n");
pid = fork(); /* Kindprozess erzeugen */
if (pid < 0) { /* Fehler in fork aufgetreten */
perror("fork error"); /* Fehlermeldung ausgeben */
exit(1); /* Programm mit Exitstatus 1 beenden */
}
else if (pid == 0) { /* Kindprozess */
sleep(1); /* 1 sec schlafen */
printf("Kindprozess mit PID %d\n", getpid());
exit(100);
}