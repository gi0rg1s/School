#include <sqlite3.h>
#include <stdio.h>
#include "myLibrary.h"

// ===========================================================
//                  Menu and Screen Functions                |
// ===========================================================
void menuScreen(){
    int choice;
    sqlite3 *db;

    do{
    printf(" +------------------------------+\n");
    printf(" |         Library Menu         |\n");
    printf(" +------------------------------+\n");
    printf(" |  welcome to the new library! |\n");
    printf(" |  Please select an option:    |\n");
    printf(" |  1. Log In                   |\n");
    printf(" |  2. Sign Up                  |\n");
    printf(" |  3. Exit                     |\n");
    printf(" +------------------------------+\n");
    printf("--> ");
    scanf("%d", &choice);

    printf("\033[2J\033[H");
        switch (choice)
        {
        case 1:
            logInScreen(db);
            break;
        case 2:
            signUpScreen(db);
            break;
        case 3:
            printf("Exiting the program... Goodbye!\n");
            break;
        default:
            printf("Invalid choice. Please try again.\n");
            break;
        }
    }while(choice < 1 || choice > 3);

    sqlite3_close(db);
}

void logInScreen(sqlite3 *db){
    char username[21];
    char password[21];

    printf(" +------------------------------+\n");
    printf(" |          Log In Screen       |\n");
    printf(" +------------------------------+\n");
    printf(" |  Please enter your username: |\n");
    printf(" |  --> ");
    scanf("%s", username);
    printf(" |  Please enter your password: |\n");
    printf(" |  --> ");
    scanf("%s", password);

    if(checkLogIn(db, username, password)){
        printf("Login successful! Welcome, %s!\n", username);
        // Proceed to the user's library or main menu
    } else {
        printf("Invalid username or password. Please try again.\n");
        // Optionally, you can call logInScreen() again for another attempt
    }
}

//==========================================================
//                  Check log in function                  |
//==========================================================

int checkLogIn(sqlite3 *db, char *username, char *password){
    
    if (sqlite3_open("library_db.sql", &db) != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        return -1;
    }
    char *sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
    sqlite3_stmt *stmt;


    return 0;
}

void signUpScreen(sqlite3 *db){
    return;
}