#include <stdio.h>
#include <stdlib.h>

// Structure with single file information
typedef struct{
    FILE* fp;
    unsigned int index;
    int kernel_handle;
} file_open_t;

//structure with general information
typedef struct{
    unsigned int aperture_riuscite;
    unsigned int aperture_fallite;
    file_open_t *files;
    size_t size;
} open_info_t;

int main(int argc, char *argv[]){

// argument check
    if(argc != 2){
        perror("argc");
        return 1;
    }    

    open_info_t info = {0, 0, NULL, 0};               // files information initialization 
    info.files= malloc(sizeof(file_open_t)); // array of files initialization
    if (!info.files) {
        perror("malloc");
        return 1;
    }

    while(1){
        file_open_t f = {NULL, 0, 0};                 // single file information initialization 
        f.fp = fopen(argv[1], "r");
        if (!f.fp) {                                   // file opening check
            perror("fopen");
            info.aperture_fallite++;
            break;
        }

        info.aperture_riuscite++;
        info.files = realloc(info.files, sizeof(file_open_t) * info.aperture_riuscite);
        
        f.index = info.aperture_riuscite - 1;   
        f.kernel_handle = fileno(f.fp);                         // adding file at the file array 

        info.files[f.index] = f;
    }
    // Clean up and prints final file information
    for (size_t i = 0; i < info.aperture_riuscite; i++) {
        if (info.files[i].fp) fclose(info.files[i].fp);
    }
    
    free(info.files);                                            //free memory allocation

    fprintf(stdout, "*************FILE INFO*************");         //printing file information
    fprintf(stdout, "\naperture fallite : %u", info.aperture_fallite);
    fprintf(stdout, "\naperture riuscite : %u", info.aperture_riuscite);

    return 0;
}