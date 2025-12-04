#include "ipLib.h"
#include <stdlib.h>
#include <stdio.h>

void parse_cli(int argc, char* argv[]) {
    ip_t ip = {NULL, 0, 0, NULL, NULL};                     // Initialize to zero
    ip_t* ipList = NULL;
    int count = 1;                                          // Default: generate 1 IP
    
    if(argc == 1) { 
        // Generate a single random IP if no arguments are provided
        ip = generate_ip(ip);
        ipList = malloc(sizeof(ip_t));
        ipList[0] = ip;
    }
    else if(argc == 2) {
        char *temp = argv[1];
        if(temp[0] == '-' && temp[1] == 'n' && temp[2] >= '0' && temp[2] <= '9') {
            count = temp[2] - '0';
            ipList = malloc(sizeof(ip_t) * count);                          //allocate memory for ip list to be generated
            for (int i = 0; i < count; i++) {
                ipList[i] = generate_ip(ip);                                // generate n random IPs
            }
        }
    }
    else if(argc >= 3) {
        char *temp = argv[1];
        
        // Parse count from -nX argument
        if(temp[0] == '-' && temp[1] == 'n' && temp[2] >= '0' && temp[2] <= '9') {
            count = temp[2] - '0';
        }
        
        ipList = malloc(sizeof(ip_t) * count);
        temp = argv[2];
        
        if(temp[0] == '-' && temp[1] == 'c') {
            if (temp[2] >= 'A' && temp[2] <= 'E') {                                 //check for the class specification
                ip.classe = temp[2];
                for (int i = 0; i < count; i++) {
                    ipList[i] = generate_ip(ip);
                }
            }
        }
        else if(temp[0] == '-' && temp[1] == 'i') {                                 //check for the ip/mask specification
            uint32_t oct[4];
            uint32_t mask;
            sscanf(temp + 2, "%u.%u.%u.%u/%u", &oct[0], &oct[1], &oct[2], &oct[3], &mask); //parse every single octect to a big 32 bit integer
            
            uint32_t netAdd = (oct[0] << 24) | (oct[1] << 16) | (oct[2] << 8) | oct[3];
            ip.netmask = (0xFFFFFFFF << (32 - mask));                             //calculate the netaddress             
            
            for (int i = 0; i < count; i++) {
                if((ip.netmask & netAdd) > 1) {
                    uint32_t random_host = (rand() % ((ip.netmask & netAdd) - 1)) + 1;      //generate a random host part within the subnet range
                    ipList[i].ip = ip.netmask | random_host;
                    ipList[i].classe = '\0';
                    determinate_class(&ipList[i]);
                    determinate_type(&ipList[i]);
                }
            }
        }
    }
    
    // Determine class and type for all IPs
    for (int i = 0; i < count; i++) {
        determinate_class(&ipList[i]);
        determinate_type(&ipList[i]);
    }
    
    printIPlist(ipList, count);
    free(ipList);
}
//determinate the type of the ip address
void determinate_type(ip_t* ip) {
    if ((ip->ip & 0xFF000000) == 0x00000000) ip->type = "ris";      // 0.0.0.0/8
    else if ((ip->ip & 0xFF000000) == 0x7F000000) ip->type = "ris"; // 127.0.0.0/8
    else if ((ip->ip & 0xFFFF0000) == 0xA9FE0000) ip->type = "ris"; // 169.254.0.0/16
    else if ((ip->ip & 0xF0000000) == 0xE0000000) ip->type = "ris"; // 224.0.0.0/4 (Class D)
    else if ((ip->ip & 0xF0000000) == 0xF0000000) ip->type = "ris"; // 240.0.0.0/4 (Class E)
    else if ((ip->ip & 0xFF000000) == 0x0A000000) ip->type = "pri"; // 10.0.0.0/8
    else if ((ip->ip & 0xFFF00000) == 0xAC100000) ip->type = "pri"; // 172.16.0.0/12
    else if ((ip->ip & 0xFFFF0000) == 0xC0A80000) ip->type = "pri"; // 192.168.0.0/16
    else ip->type = "pub";
}

//determinate the class of the ip address
void determinate_class(ip_t* ip) {
    if((ip->ip & 0x80000000) == 0) ip->classe = 'A';
    else if((ip->ip & 0xC0000000) == 0x80000000) ip->classe = 'B';
    else if((ip->ip & 0xE0000000) == 0xC0000000) ip->classe = 'C';
    else if((ip->ip & 0xF0000000) == 0xE0000000) ip->classe = 'D';
    else ip->classe = 'E';
}

// generate a random ip address based on the class
ip_t generate_ip(ip_t ip) {
    switch (ip.classe) {
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
    
    for(int i = 2; i >= 0; i--)
        ip.ip |= (rand() % 256) << (i * 8);
    
    return ip;
}

// print the list of IP addresses
void printIPlist(ip_t* ipList, int count) {
    for(int i = 0; i < count; i++) {
        printf("IP: %u.%u.%u.%u Classe: %c Type: %s\n", 
               (ipList[i].ip >> 24) & 0xFF,         //print every single octect
               (ipList[i].ip >> 16) & 0xFF,
               (ipList[i].ip >> 8) & 0xFF, 
               ipList[i].ip & 0xFF, 
               ipList[i].classe,         //print the class
               ipList[i].type);          //print the type
    }
}