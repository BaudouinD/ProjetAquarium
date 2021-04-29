#ifndef SERVER_H
#define SERVER_H

typedef int SOCKET;

#define PORT 8080
#define MAX_CLIENTS 10
#define BUF_SIZE 1024

int init_connection_server();
void end_connection_server();

#endif