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
    char type[3];
}ip_t;

void parse_cli (int argc, char* argv[]);

void generate_ip (int n);

#endif 
