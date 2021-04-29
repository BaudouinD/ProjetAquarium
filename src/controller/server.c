#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#include "server.h"

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int init_connection_server(void) {

    SOCKET sockfd;
    struct sockaddr_in serv_addr;

    sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd < 0)
        error("Server: failed to create the socket");

    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(PORT);
    serv_addr.sin_family = AF_INET;

    if ( bind(sockfd, ((const struct sockaddr *) &serv_addr), sizeof(serv_addr)) < 0 )
        error("Server: failed to bind\n");

    if ( listen(sockfd, MAX_CLIENTS) < 0 )
        error("Server: failed to listen\n");

    /*

    SOCKET sockfd_view;
    struct sockaddr_in view_addr;
    int c = sizeof(struct sockaddr_in);

    while ( (sockfd_view = accept(sockfd, (struct sockaddr *) &view_addr, (socklen_t *) &c)) ) {
        printf("Connection success");
    }
    */

    return sockfd;
}


int main(int argc, char ** argv) 
{
    SOCKET sockserv = init_connection_server();
    
    char buffer[BUF_SIZE];
    /* the index for the array */
    int actual = 0;
    int max = sock;
    /* an array for all clients */
    Client clients[MAX_CLIENTS];

    fd_set rdfs;

    while(1)
    {
        int i = 0;
        FD_ZERO(&rdfs);

        /* add STDIN_FILENO */
        FD_SET(STDIN_FILENO, &rdfs);

        /* add the connection socket */
        FD_SET(sock, &rdfs);

        /* add socket of each client */
        for(i = 0; i < actual; i++)
        {
            FD_SET(clients[i].sock, &rdfs);
        }

        if(select(max + 1, &rdfs, NULL, NULL, NULL) == -1)
        {
            perror("select()");
            exit(errno);
        }

        /* something from standard input : i.e keyboard */
        if(FD_ISSET(STDIN_FILENO, &rdfs))
        {
            /* stop process when type on keyboard */
            break;
        }
        else if(FD_ISSET(sock, &rdfs))
        {
            /* new client */
            SOCKADDR_IN csin = { 0 };
            size_t sinsize = sizeof csin;
            int csock = accept(sock, (SOCKADDR *)&csin, &sinsize);
            if(csock == SOCKET_ERROR)
            {
                perror("accept()");
                continue;
            }

            /* after connecting the client sends its name */
            if(read_client(csock, buffer) == -1)
            {
                /* disconnected */
                continue;
            }

            /* what is the new maximum fd ? */
            max = csock > max ? csock : max;

            FD_SET(csock, &rdfs);

            Client c = { csock };
            strncpy(c.name, buffer, BUF_SIZE - 1);
            clients[actual] = c;
            actual++;
        }
        else
        {
            int i = 0;
            for(i = 0; i < actual; i++)
            {
                /* a client is talking */
                if(FD_ISSET(clients[i].sock, &rdfs))
                {
                Client client = clients[i];
                int c = read_client(clients[i].sock, buffer);
                /* client disconnected */
                if(c == 0)
                {
                    closesocket(clients[i].sock);
                    remove_client(clients, i, &actual);
                    strncpy(buffer, client.name, BUF_SIZE - 1);
                    strncat(buffer, " disconnected !", BUF_SIZE - strlen(buffer) - 1);
                    send_message_to_all_clients(clients, client, actual, buffer, 1);
                }
                else
                {
                    send_message_to_all_clients(clients, client, actual, buffer, 0);
                }
                break;
                }
            }
        }
    }

    clear_clients(clients, actual);
    end_connection(sock);
    }
}