#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]){

    // Controlla che il programma abbia ricevuto almeno 3 argomenti (programma, flag, nomefile)
    if(argc < 3 ){
        perror("errore nel passaggio parametri!");
        return 1;
    }
    
    // Verifica che il primo argomento sia "-o" usando strcmp 
    if(strcmp(argv[1], "-o") == 0){
        FILE *file_handle;
        
        // Apre il file passato come secondo argomento in lettura
        if(!(file_handle = fopen(argv[2], "r"))){
            perror("errore nel passaggio dei parametri!");
            return 1;
        }
        
        // Buffer per memorizzare ogni riga del file 
        char line[200];

        // Legge il file riga per riga
        while(fgets(line, sizeof(line), file_handle)){
            // strtok divide la stringa usando lo spazio come separatore
            char *word = strtok(line, " ");
            
            // Continua finché ci sono parole da tokenizzare
            while(word != NULL){
                printf("%s\n", word);
                // Passa NULL a strtok per continuare con la stessa stringa
                word = strtok(NULL, " ");
            }
        }
        
        // Chiude il file
        fclose(file_handle);
    }
    
    return 0;

}