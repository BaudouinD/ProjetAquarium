#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <netdb.h>
#include <arpa/inet.h>

#include "view.h"

void error(char *msg)
{
    perror(msg);
    exit(0);
}

int init_connection_view(const char * address) {

    SOCKET sockfd;
    struct sockaddr_in view_addr;
    struct hostent *servinfo;

    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0)
        error("View: failed to create the socket");

    servinfo = gethostbyname(address);
    
    if (servinfo == NULL)
        error("View: failed to find server");

    view_addr.sin_addr = * (struct in_addr *) servinfo->h_addr;
    view_addr.sin_port = htons(PORT);
    view_addr.sin_family = AF_INET;

    if (connect(sockfd, (struct sockaddr *) &view_addr, sizeof(struct sockaddr)) < 0)
        error("View: failed to connect");

    return sockfd;
}

int main(int arc, char ** argv) {
    init_connection_view("localhost");
}