#include <sqlite3.h>

typedef struct{
    char title[50];
    char author[30];
    char genre[20];
    unsigned char rank;
    double price;
    unsigned char borrowed;     //0 --> available, 1 --> borrowed
} Book_t;

typedef struct{
    Book_t* boorrowedBooks;
    Book_t* read;
    Book_t* wantToRead;
    Book_t* currentlyReading;
} UserLibrary_t;

typedef struct{
    char name[15];
    char surname[15];
    char dateOfBirth[11];   //max 10 chars + terminator char
    char username[21];
    char password[21];
    UserLibrary_t library;
} User_t;

int checkLogIn(sqlite3 *db, char* username, char* password);

void menuScreen();

void logInScreen(sqlite3 *db);

void signUpScreen(sqlite3 *db);

void profileScreen(sqlite3 *db, char* username);

void showPersonalInfo(sqlite3 *db, char* username);

void showMyBooks(sqlite3 *db, char* username);

void showRentingStatus(sqlite3 *db, char* username);