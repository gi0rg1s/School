#include <stdio.h>
#include <stdlib.h>

typedef struct{
    FILE* fp;
    unsigned int index;
    int kernel_handle;
} file_open_t;

typedef struct{
    unsigned int aperture_riuscite;
    unsigned int aperture_fallite;
    FILE* files;
    size_t size;
} open_info_t;

int main(int argc, char *argv[]){

    if(argc != 2){
        perror("argc");
        return 1;
    }    
    open_info_t info = {0, 0, NULL, 0};
    file_open_t *files = malloc(sizeof(file_open_t));

    while(1){
        file_open_t f;
        if(!(f.fp = fopen(argv[1], "r"))){
            perror("fopen");
            info.aperture_fallite++;
            break;
        }
        
        realloc(files, sizeof(file_open_t) + sizeof(files));
        files[info.aperture_riuscite - 1] = f;
    }

    fprintf(stdout, "*************FILE INFO*************");
    fprintf(stdout, "\naperture riuscite : %u", info.aperture_riuscite);
    fprintf(stdout, "\naperture riuscite : %u", info.aperture_riuscite);

    return 0;
}