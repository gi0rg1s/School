#include <stdio.h>
#include <stdlib.h>

static int i = 0; 

typedef struct {
    FILE *fp;
    int index;
    int kernel_handle;
} file_open_t;

typedef struct {
    unsigned int aperture_riuscite;
    unsigned int aperture_fallite;
    file_open_t *last_file;
    unsigned int list_size;     
} open_info_t;

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <nome_file>\n", argv[0]);
        return 1;
    }

    open_info_t info;
    info.aperture_fallite = 0;
    info.aperture_riuscite = 0;
    info.list_size = 0;
    
    int capacity = 100;
    info.last_file = (file_open_t *)malloc(capacity * sizeof(file_open_t));
    
    if (!info.last_file) {
        perror("malloc");
        return 1;
    }

    while (1) {
        FILE *fp = fopen(argv[1], "r");
        
        if (!fp) {
            perror("fopen");
            info.aperture_fallite++;
            break;
        }

        if (info.aperture_riuscite >= capacity) {
            capacity *= 2;
            file_open_t *temp = (file_open_t *)realloc(info.last_file, capacity * sizeof(file_open_t));
            
            if (!temp) {
                perror("realloc");
                fclose(fp); 
                break;
            }
            info.last_file = temp;
        }

        info.last_file[info.aperture_riuscite].fp = fp;
        info.last_file[info.aperture_riuscite].index = ++i;
        info.last_file[info.aperture_riuscite].kernel_handle = fileno(fp);
        
        info.aperture_riuscite++;
        info.list_size = info.aperture_riuscite;

        if (info.aperture_riuscite % 100 == 0) {
            printf("Aperture: %u (fd: %d)\n", 
                   info.aperture_riuscite,
                   info.last_file[info.aperture_riuscite - 1].kernel_handle);
        }
    }

    printf("\nChiusura di %u file...\n", info.aperture_riuscite);

    // Chiudi tutti i file aperti
    int errori_chiusura = 0;
    for (unsigned int j = 0; j < info.aperture_riuscite; j++) {
        if (fclose(info.last_file[j].fp) != 0) {
            fprintf(stderr, "Errore chiusura file [%d] (fd=%d)\n",
                    info.last_file[j].index,
                    info.last_file[j].kernel_handle);
            errori_chiusura++;
        }
    }

    // Stampa report
    fprintf(stdout, "\n*********** FILE INFO ***********\n");
    fprintf(stdout, "Aperture fallite:  %u\n", info.aperture_fallite);
    fprintf(stdout, "Aperture riuscite: %u\n", info.aperture_riuscite);
    fprintf(stdout, "Dimensione lista:  %u\n", info.list_size);
    
    if (errori_chiusura > 0) {
        fprintf(stderr, "Errori chiusura:   %d\n", errori_chiusura);
    } else {
        fprintf(stdout, "Tutti i file chiusi correttamente\n");
    }
    
    fprintf(stdout, "*********************************\n");

    // Libera memoria
    free(info.last_file);

    return 0;
}