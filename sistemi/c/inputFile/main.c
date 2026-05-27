#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "IPlib.h"

int main(int argc, char* argv[]){

    srand(time(NULL)); //initialize the timer
    
    FILE *outp = stdout;     //output file pointer
    FILE *inp = stdin;      //input file pointer
    int num = 1;    //default number of ips to generate
    char class = ' '; //initializing class variable
    int index = 1;  //index for argument parsing

    while(index < argc){
        //option -h for help
        if(!(strcmp(*argv, "-h"))){
            printf("raindip [-o OUTPUT] [-n NUM] [-c CLASS] [-h]");
            return 1;
        }
        //option -i --> input from a text file
        else if(!strcmp(*argv, "-i")){
            if((inp = fopen(*(++argv), "r")) == NULL){         //open the file in reading mode
                perror("error in the file opening!");
                return 1;
            }
            read_and_print_ip_from_file(inp, outp);
            return 0;
        }

        //first option --> -o = file output
        else if(!(strcmp(*argv, "-o"))){
            if((outp = fopen(*(++argv), "w")) == NULL){         //open the file in writing mode
                perror("error in the file opening!");
                return 1;
            }
            index += 2;
        }

        //-n --> specify the number of IPs to generate
        else if(!(strcmp(*argv, "-n"))){
            num = atoi(*(++argv));                              //convert the string to integer
            index += 2;
        }
        
        //-c --> specify the IP class
        else if(!(strcmp(*argv, "-c"))) {
            class = *(*(++argv));                           //get the character representing the class
            index += 2;
        }
        argv++;

    }
    
    ip_t *ips = malloc(sizeof(ip_t) * num); //allocate the memory for the ips array
    ip_t *ips_start = ips; //keep the pointer to the start of the array

    for(int i = 0; i < num; i++){
        //generate IPs and determinate each class and netMask
        ips->class = class;
        generate_ip(ips);   //generate the ip
        determine_class(ips);   //determinate the class and netMask

        //print the results
        fprintf(outp, "\n******IP n.%d******", i + 1);
        fprintf(outp, "\n\tip --> %u.%u.%u.%u",(ips->ip >> 24) & 0xFF,
                      (ips->ip >> 16) & 0xFF,(ips->ip >> 8) & 0xFF,
                       ips->ip & 0xFF);
        fprintf(outp, "\n\tclass --> %c", ips->class);
        fprintf(outp, "\n\tnetID --> %u.%u.%u.%u\n",((ips->ip >> 24) & 0xFF) & ((ips->netMask >> 24) & 0xFF),   //calculate and print the netID with bitwise operations
                      ((ips->ip >> 16) & 0xFF) & ((ips->netMask >> 16) & 0xFF), ((ips->ip >> 8) & 0xFF) & ((ips->netMask >> 8) & 0xFF),
                       (ips->ip & 0xFF) & (ips->netMask & 0xFF));
        
        ips++;  //move to the next ip
    }
    free(ips_start); //free the allocated memory
    if(outp != stdout) fclose(outp); //close the file if it's not stdout

    return 0;
}