#include "ipLib.h"

void parse_cli (int argc, char* argv[]){
    ip_t ip;
    if(argc == 0) generate_ip(1, ip);

    else if(argc >= 1){
        for (int i = 0; i < argc; i++){
            char* str = argv[i];        //temporary var

            if(str[0] == '-') { //parser for -n<number>

                switch (str[1])
                {
                case 'n':
                    if(str[2] >= '0' && str[2] <= '9') generate_ip((int)str[2] - '0', ip);
                    break;
                
                case 'c':
                    if(str[2] >= 'A' && str[2] <= 'E') 
                        ip.classe = str[2];
                    break;

                case 'i':
                    sscanf(str++, "%u.%u.%u.%u", ip.netmask << 24, ip.netmask << 16, ip.netmask << 8, ip.netmask);
                    ip.classe = NULL;
                    break;

                case 'h':
                    fprintf(stdout, "rand ip \n[-n NUM] --> numer of ips to generate\n[-c CLASSE] --> specify ip class (A, B, C, D, E)\n[-i NETCIDR] --> specify the netmask\n[-h] --> help\n");
                    break;
                default:
                    perror("Unknown option\n");
                    break;
                }
            }
        }
            
    }
}


void generate_ip (int n, ip_t ip){

    switch (ip.classe)
    {
    case 'A':
        ip.ip |= (rand() % 128) << 24;
        break;
    case 'B':
        ip.ip |= (rand() % (192 - 128) + 128) << 24;
        break;
    case 'C':
        ip.ip |= (rand() % (224 - 192) + 192) << 24;
        break;
    case 'D':
        ip.ip |= (rand() % (240 - 224) + 224) << 24;
        break;
    case 'E':
        ip.ip |= (rand() % (256 - 240) + 240) << 24;
        break;
    default:
        ip.ip |= (rand() % 256) << 24;
        break;
    }

    for(int i = 2; i >= 0; i--){
        ip.ip |= (rand() % 256) << (i * 8);
    }
    ip.ip &= ip.netmask; 
}