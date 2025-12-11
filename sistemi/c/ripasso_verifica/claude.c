#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[50];
    int score;
} Student;

void readStudents(const char *filename, Student *students, int *count) {    
    FILE *fp = fopen(filename, "r");            //opens the file for reading
    if (fp == NULL) return;
    
    *count = 0;                                 // initialize pointer
    while (fscanf(fp, "%s %d", students[*count].name,           //get the name of the students with their score
                  &students[*count].score) == 2) {
        (*count)++;
    }
    fclose(fp);                                 // close the file
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return 1;
    }
    
    Student students[100];                      //array of 100 students (structs)
    int count;
    
    readStudents(argv[1], students, &count);    // read students from file
    
    for (int i = 0; i < count; i++) {
        printf("%s: %d\n", students[i].name, students[i].score); // print name and score
    }
    
    return 0;
}