#include "ipLib.h"

void parse_cli (int argc, char* argv[]){
    ip_t ip;
    char *temp = argv[1];
    if(argc == 1){ 
        generate_ip(1, ip);
    }
    else if(argc == 2){
        if(temp[0] == '-'){
            if(temp[1] == 'n'){
                if(temp[2] >= '0' && temp[2] <= '9') generate_ip((int)temp[2] - '0', ip);
            }
        }
    }
    else if(argc >= 2){
        temp = argv[2];
        if(temp[2] == 'c'){
            if (temp[3] >= 'A' && temp <= 'E'){
                ip.classe = temp[2];
            }
        }
        else if(temp[2] == 'i'){
            uint8_t oct[4];
            uint32_t mask;
            sscanf(temp++, "%u.%u.%u.%u/%u", oct[0], oct[1], oct[2], oct[3], mask);
            uint32_t netAdd = (oct[0] << 24) | (oct[1] << 16) | (oct[2] << 8) | (oct[3]);
            ip.netmask = netAdd & (0xFFFFFFFFFFFFFFFF << (32 - mask));
            
            mask = ~mask;
            if(mask > 1){
                uint32_t random_host = (rand() % (mask - 1)) + 1;
                ip.ip = ip.netmask | random_host;
                ip.classe = NULL;
            }
        }
    }
    determinate_class(ip);
    determinate_type(ip);

}

void determinate_type(ip_t ip){

    if ((ip.ip && 0xFF000000) == 0x00000000) ip.type = "ris";      // 0.0.0.0/8 - Current network
    else if ((ip.ip && 0xFF000000) == 0x7F000000) ip.type = "ris"; // 127.0.0.0/8 - Loopback
    else if ((ip.ip && 0xFFFF0000) == 0xA9FE0000) ip.type = "ris"; // 169.254.0.0/16 - Link-local (APIPA)
    else if ((ip.ip && 0xF0000000) == 0xE0000000) ip.type = "ris"; // 224.0.0.0/4 - Multicast (Classe D)
    else if ((ip.ip && 0xF0000000) == 0xF0000000) ip.type = "ris"; // 240.0.0.0/4 - Reserved (Classe E)
    else if ((ip.ip && 0xFF000000) == 0x0A000000) ip.type = "pri"; // 10.0.0.0/8
    else if ((ip.ip && 0xFFF00000) == 0xAC100000) ip.type = "pri"; // 172.16.0.0/12 (172.16.0.0 - 172.31.255.255)
    else if ((ip.ip && 0xFFFF0000) == 0xC0A80000) ip.type = "pri"; // 192.168.0.0/16
    else ip.type = "pub";
}

void determinate_class(ip_t ip){
    if((ip.ip && 0x8000000000000000) == 0) ip.classe = 'A';
    else if((ip.ip && 0xC000000000000000) == 0x8000000000000000) ip.classe = 'B';
    else if((ip.ip && 0xE000000000000000) == 0xC000000000000000) ip.classe = 'C';
    else if((ip.ip && 0xF000000000000000) == 0xE000000000000000) ip.classe = 'D';
    else ip.classe = 'E';
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
}