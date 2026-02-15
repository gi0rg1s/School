#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "myLibrary.h"

// ===========================================================
//                  Menu and Screen Functions                |
// ===========================================================
void menuScreen(){
    int choice;
    sqlite3 *db = NULL;

    if (sqlite3_open("library_db.db", &db) != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

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

    printf("\033[2J\033[H");

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
    sqlite3_stmt *stmt;     // Declare a statement object to hold the compiled SQL statement
    const char *sql = "SELECT id FROM members WHERE USER_NAME = ? AND PASSWORD = ?;"; // SQL query to check if a user with the given username and password exists
    
    sqlite3_prepare_v2(db, sql, -1, &stmt, 0); // Compile the SQL query and prepare it for execution, storing the compiled statement in the stmt variable

    // Bind the username/password parameters to the first placeholder in the SQL query
    sqlite3_bind_text(stmt, 1, username, -1, SQLITE_STATIC);
    sqlite3_bind_text(stmt, 2, password, -1, SQLITE_STATIC);
    
    int success = (sqlite3_step(stmt) == SQLITE_ROW); // Execute the SQL query and check if a row is returned
    sqlite3_finalize(stmt); // Finalize the statement to release resources associated with it
    
    return success;
}

//==========================================================
//                  Sign up screen function                 |
//==========================================================
//check if the username already exists 
//if not, it adds user's information to the db
void signUpScreen(sqlite3 *db){

    // Get user input for username and password
    char name[15];
    char surname[15];
    char username[21];
    char password[21];
    printf(" +------------------------------+\n");
    printf(" |         Sign Up Screen       |\n");
    printf(" +------------------------------+\n");
    printf(" |  Welcome to the new library! |\n");
    printf(" |  Please enter your name:     |\n");
    printf(" |  --> ");
    scanf("%s", name);
    printf(" |  Please enter your surname:  |\n");
    printf(" |  --> ");
    scanf("%s", surname);
    printf(" |  Please enter your username: |\n");
    printf(" |  --> ");
    scanf("%s", username);
    printf(" |  Please enter your password: |\n");
    printf(" |  --> ");
    scanf("%s", password);

    // Clear the console screen
    printf("\033[2J\033[H");

    //check if the username already exists in the db
    sqlite3_stmt *stmt;
    const char *sql = "SELECT id FROM members WHERE USER_NAME = ?;";

    sqlite3_prepare_v2(db, sql, -1, &stmt, 0);

    sqlite3_bind_text(stmt, 1 ,username, -1, SQLITE_STATIC);

    int exists = (sqlite3_step(stmt) == SQLITE_ROW);
    sqlite3_finalize(stmt);

    if(exists){
        //cls
        printf("\033[2J\033[H");

        printf("Username already exists. Please choose a different username.\n");
        
        //call signUpScreen again
        signUpScreen(db);
    } else {
        // Insert the new user's information into the database
        const char *insert_sql = "INSERT INTO members (name, surname, USER_NAME, PASSWORD, membership_date) VALUES (?, ?, ?, ?, ?);";
        sqlite3_prepare_v2(db, insert_sql, -1, &stmt, 0);
        sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt, 2, surname, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt, 3, username, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt, 4, password, -1, SQLITE_STATIC);

        time_t t = time(NULL);                                      // Get the current time
        struct tm *tm = localtime(&t);                              // Convert the time to local time structure
        char date_string[20];
        strftime(date_string, sizeof(date_string), "%Y-%m-%d", tm); // Form the local date
        sqlite3_bind_text(stmt, 5, date_string, -1, SQLITE_STATIC); // Placeholder date of membership
        
        if (sqlite3_step(stmt) == SQLITE_DONE) {
            printf("Sign up successful! You can now log in with your new account.\n");
        } else {
            printf("Error signing up. Please try again.\n");
        }
        
        sqlite3_finalize(stmt);
    }

}