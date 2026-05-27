#ifndef IPLIB_H_INCLUDED
#define IPLIB_H_INCLUDED
#include <stdint.h>

//ip structure
typedef struct{
    uint32_t ip;        //ip address
    char class;         //ip class
    uint32_t netMask;   //network mask
} ip_t;

void generate_ip(ip_t *ip);
void determine_class(ip_t *ip);

#endif