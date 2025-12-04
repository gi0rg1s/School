#ifndef IPLIB_H
#define IPLIB_H

#include <stdint.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

typedef struct {
    char *ipStr;
    uint32_t ip;
    uint32_t netmask;
    char classe;
    char* type;
}ip_t;

void parse_cli (int argc, char* argv[]);

void determinate_class(ip_t ip);

void determinate_type(ip_t ip);

ip_t generate_ip (ip_t ip);

void printIPlist(ip_t* ipList);

#endif 
