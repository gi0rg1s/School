#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>
#include "myLibrary.h"

static int ensureDatabase(sqlite3 *db);

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

    if (ensureDatabase(db) != 0) {
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
/** 
static int ensureDatabase(sqlite3 *db) {
    const char *checkSql = "SELECT name FROM sqlite_master WHERE type='table' AND name='members';";
    sqlite3_stmt *stmt = NULL;
    int rc = sqlite3_prepare_v2(db, checkSql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to check schema: %s\n", sqlite3_errmsg(db));
        return -1;
    }

    int hasMembersTable = (sqlite3_step(stmt) == SQLITE_ROW);
    sqlite3_finalize(stmt);

    if (hasMembersTable) {
        return 0;
    }

    FILE *sqlFile = fopen("library_db.sql", "rb");
    if (!sqlFile) {
        fprintf(stderr, "Cannot open SQL schema file library_db.sql\n");
        return -1;
    }

    fseek(sqlFile, 0, SEEK_END);
    long size = ftell(sqlFile);
    rewind(sqlFile);

    char *sql = (char *)malloc((size_t)size + 1);
    if (!sql) {
        fclose(sqlFile);
        fprintf(stderr, "Out of memory while reading SQL file\n");
        return -1;
    }

    size_t readSize = fread(sql, 1, (size_t)size, sqlFile);
    sql[readSize] = '\0';
    fclose(sqlFile);

    char *errMsg = NULL;
    rc = sqlite3_exec(db, sql, NULL, NULL, &errMsg);
    free(sql);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to initialize database: %s\n", errMsg ? errMsg : "Unknown error");
        sqlite3_free(errMsg);
        return -1;
    }

    return 0;
}
*/
void signUpScreen(sqlite3 *db){
    return;
}