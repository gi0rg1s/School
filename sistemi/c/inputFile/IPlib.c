#include "IPlib.h"
#include <stdlib.h>
#include <stdio.h>

void generate_ip(ip_t *ip){
    //generate the first octect random, based on the ip class
    switch (ip->class){
        case 'A': case 'a':
            ip->ip = rand() % 128;
            break;
        case 'B': case 'b':
            ip->ip = (rand() % 64) + 128;
            break;
        case 'C': case 'c':
            ip->ip = (rand() % 32) + 192;
            break;
        case 'D': case 'd':
            ip->ip = (rand() % 192) + 32;
            break;
        default:
            ip->ip = rand() % 256;
            break;
    }
    //generate the other 3 octects
    for(int i = 1; i < 4; i++){
        ip->ip = (ip->ip << 8) | (rand() % 256);
    }
}
void determine_class(ip_t *ip){
    if((ip->ip & 0x80000000) == 0){ 
        ip->class = 'A';
        ip->netMask = 0xFF000000;
    }
    else if((ip->ip & 0xC0000000) == 0x80000000){
        ip->class = 'B';
        ip->netMask = 0xFFFF0000;
    }
    else if((ip->ip & 0xE0000000) == 0xC0000000){
        ip->class = 'C';
        ip->netMask = 0xFFFFFF00;
    }
    else{
        ip->class = 'D';
        ip->netMask = 0;
    }
}

void *read_and_print_ip_from_file(FILE *inp, FILE *outp){
    ip_t *ips;
    int num_ips = 0;
    uint8_t octects[4];
    char buf[16];
    while(fgets(buf, sizeof(buf), inp) != NULL){
        num_ips++;
        ips = realloc(ips, sizeof(ip_t) * (num_ips + 1));
        ips++;
        sscanf(buf, "%u.%u.%u.%u", &octects[0], &octects[1], &octects[2], &octects[3]);
        for(int i = 0; i < 4; i++) ips->ip |= octects[i] << (24 - i * 8);
        determine_class(ips);

        //print the results
        fprintf(outp, "\n******IP n.%d******", num_ips);
        fprintf(outp, "\n\tip --> %u.%u.%u.%u",(ips->ip >> 24) & 0xFF,
                      (ips->ip >> 16) & 0xFF,(ips->ip >> 8) & 0xFF,
                       ips->ip & 0xFF);
        fprintf(outp, "\n\tclass --> %c", ips->class);
        fprintf(outp, "\n\tnetID --> %u.%u.%u.%u\n",
                       (ips->netMask & ips->ip) >> 24 & 0xFF,
                       ((ips->netMask & ips->ip) >> 16) & 0xFF,
                       ((ips->netMask & ips->ip) >> 8) & 0xFF,
                       (ips->netMask & ips->ip) & 0xFF);
    }
}

