#ifndef MYLIBRARY_H
#define MYLIBRARY_H

#include <sqlite3.h>

int checkLogIn(sqlite3 *db, char* username, char* password);

void menuScreen();

void logInScreen(sqlite3 *db);

void signUpScreen(sqlite3 *db);

void profileScreen(sqlite3 *db, int user_id);

void showPersonalInfo(sqlite3 *db, int user_id);

void showMyBooks(sqlite3 *db, int user_id);

void showMyBookshelf(sqlite3 *db, int user_id);

void addNewBook(sqlite3* db, int user_id);

void rentNewBook(sqlite3* db, int title_id, int user_id);

void searchBookScreen(sqlite3* db, int user_id);

void searchByTitle(sqlite3* db, int user_id);

void searchByAuthor(sqlite3 *db, int user_id);

#endif