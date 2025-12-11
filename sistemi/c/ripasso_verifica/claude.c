#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[50];
    int score;
} Student;

void readStudents(const char *filename, Student *students, int *count) {
    FILE *fp = fopen(filename, "r");
    if (fp == NULL) return;
    
    *count = 0;
    while (fscanf(fp, "%s %d", students[*count].name, 
                  &students[*count].score) == 2) {
        (*count)++;
    }
    fclose(fp);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return 1;
    }
    
    Student students[100];
    int count;
    
    readStudents(argv[1], students, &count);
    
    for (int i = 0; i < count; i++) {
        printf("%s: %d\n", students[i].name, students[i].score);
    }
    
    return 0;
}