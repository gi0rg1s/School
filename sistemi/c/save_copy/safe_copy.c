#include <stdio.h>
#include <stdint.h>

typedef struct{
    unsigned int copied_bytes;
    unsigned int read_errors;
    unsigned int write_errors;
}copy_t;

int main(int argc, char *argv[]){

    if(argc != 2){
        printf("input non valido\n\n");
        return 1;
    }

    FILE *file_handle = fopen(argv[0], "r");
    if (file_handle == NULL) {
        perror("fopen");
        return 1;
    }

    FILE *dest_handle = fopen(argv[1], "w");
    if (dest_handle == NULL) {
        perror("fopen");
        return 1;
    }

    copy_t copy_info = {0, 0, 0};
    uint8_t buffer[256];
    size_t bytes_read = 0;

    while(bytes_read) {
        bytes_read = fread(buffer, 1, sizeof(buffer), file_handle);
        if(!bytes_read){
            copy_info.read_errors++;
            perror("fread");
        }
        
        size_t bytes_written = fwrite(buffer, 1, bytes_read, dest_handle);
        
        if (bytes_written < bytes_read){
            copy_info.write_errors++;
            perror("fwrite");
        }
        else 
            copy_info.copied_bytes += bytes_written;
    }

    fprintf(stdout, "******COPY INFO*********\ncopied bytes = %u\nreading errors = %u\nwriting errors = %u\n", copy_info.copied_bytes, copy_info.read_errors, copy_info.write_errors);

    fclose(file_handle);
    fclose(dest_handle);
    return 0;
}