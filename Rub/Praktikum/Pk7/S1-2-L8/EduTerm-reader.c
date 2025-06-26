#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <strings.h>
#include <string.h>
#include <time.h>
#include <err.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include "shared_memory.h"
#include "dataframe.h"

//IP Addresse des EduTerm wird als Argument übergeben
int main (int argc, char **argv){
    int error;
    //Komplettes UDP Socket setup
    //Muss nicht mehr bearbeitet werden!
    const char *address = argv[1];
    const char *port = "49152";
    struct addrinfo hints;
    struct addrinfo *result, *current_result;

    if (argc < 2 || strlen(argv[1]) < 1)
        errx(EXIT_FAILURE, "Missing address as argument");

    bzero(&hints, sizeof(hints));
    hints.ai_family = AF_UNSPEC;    /* Allow IPv4 or IPv6 */
    hints.ai_socktype = SOCK_DGRAM; /* Datagram socket */
    hints.ai_flags = 0;
    hints.ai_protocol = 0;          /* Any protocol */

    error = getaddrinfo(address, port, &hints, &result);
    if (error != 0)
        err(EXIT_FAILURE, "getaddrinfo: %s\n", gai_strerror(error));

    /* getaddrinfo() returns a list of address structures.  Try each address
     * until we successfully connect(2). If socket(2) (or connect(2)) fails,
     * we (close the socket and) try the next address. */
    int socket_fd;
    for (current_result = result; current_result != NULL; current_result = current_result->ai_next) {
        socket_fd = socket(current_result->ai_family, current_result->ai_socktype, current_result->ai_protocol);
        if (socket_fd == -1)
            continue;
        //Try connecting to the suggested socket
        if (connect(socket_fd, current_result->ai_addr, current_result->ai_addrlen) != -1)
            break;  /* Success */
        close(socket_fd);
    }
    if (current_result == NULL)  /* No address succeeded */
        errx(EXIT_FAILURE, "Could not connect");
    freeaddrinfo(result);  /* No longer needed */

    dataframe frame;
    //TODO: Shared Memory auslesen, frame(s) befüllen und absenden

    //Hier wird ein frame an das EduTerm gesendet!
    if (write(socket_fd, &frame, sizeof(frame)) != sizeof(frame))
        err(EXIT_FAILURE, "partial/failed write\n");

    //UDP Socket wieder schliessen
    close(socket_fd);
}
