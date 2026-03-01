#ifndef MYLIBRARY_H
#define MYLIBRARY_H

#include <sqlite3.h>

int checkLogIn(sqlite3 *db, char* username, char* password);

void menuScreen();

void logInScreen(sqlite3 *db);

void signUpScreen(sqlite3 *db);

void profileScreen(sqlite3 *db, char* username);

void showPersonalInfo(sqlite3 *db, char* username);

void showMyBooks(sqlite3 *db, char* username);

void showMyBookshelf(sqlite3 *db, char*username);

void addNewBook(sqlite3* db, char* username);

void rentNewBook(sqlite3* db, char* title, char* username);

void searchBookScreen(sqlite3* db, char* username);

void searchByTitle(sqlite3* db, char* username);

void searchByAuthor(sqlite3 *db, char *username);

#endif