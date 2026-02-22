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
        printf("\nLogin successful! Welcome, %s!\n", username);

        // Proceed to the user's prifile screen
        profileScreen(db, username);
    } else {
        printf("\nInvalid username or password. Please try again.\n");
        logInScreen(db);
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
        time_t t = time(NULL);                                      // Get the current time
        struct tm *tm = localtime(&t);                              // Convert the time to local time structure
        char date_string[20];
        strftime(date_string, sizeof(date_string), "%Y-%m-%d", tm); // Form the local date
        sqlite3_bind_text(stmt, 5, date_string, -1, SQLITE_STATIC); // Placeholder date of membership
        
        if (sqlite3_step(stmt) == SQLITE_DONE) {
            printf("\033[2J\033[H");
            printf("Sign up successful! You can now log in with your new account.\n");
            logInScreen(db);
        } else {
            printf("Error signing up. Please try again.\n");
        }
        
        sqlite3_finalize(stmt);
    }
}

// +==================================================+
// |                Profile Screen function           |
// +==================================================+
//will show a display with some options 
//show renting books status, show my books status, show my personal information, log out

void profileScreen(sqlite3 *db, char* username){
    int choice = 0;

    printf(" +------------------------------+\n");
    printf(" |         Profile Screen       |\n");
    printf(" +------------------------------+\n");
    printf(" |  Welcome to your profile!    |\n");
    printf(" |  Please select an option:    |\n");
    printf(" |  1. Show my personal info    |\n");
    printf(" |  2. Show my books            |\n");
    printf(" |  3. Log out                  |\n");
    printf(" +------------------------------+\n");
    printf("--> ");
    scanf("%d", &choice);

    switch (choice)
    {
    case 1:
        showPersonalInfo(db, username);
        break;
    case 2:
        showMyBooks(db, username);
        break;

    case 3:
        printf("\033[2J\033[H");
        printf("Logging out... Goodbye!\n");
        menuScreen();
        break;

    default:
        printf("\033[2J\033[H");
        printf("Invalid choice. Please try again.\n");
        profileScreen(db, username);
        break;
    }
}

// +==================================================+
// |             Show personal info function          |
// +==================================================+
//will show the user's personal information (name, surname, date of birth, username, date of membership)
void showPersonalInfo(sqlite3 *db, char* username){
    printf("\033[2J\033[H");

    sqlite3_stmt *stmt; //create a pointer for the statement
                                                                      
    const char *sql = "SELECT id, name, surname, membership_date FROM members WHERE USER_NAME = ?;";  //query SQL
    sqlite3_prepare_v2(db, sql, -1, &stmt, 0);      //prepare SQL command to be executed

    sqlite3_bind_text(stmt, 1, username, -1, SQLITE_STATIC);   //substitute ? with the username and get all other datas

    if(sqlite3_step(stmt) == SQLITE_ROW){
        printf("+----------------------------+\n");
        printf("|     informazioni account   |\n");
        printf("+----------------------------+\n");
        printf("| ID:        %d\n", sqlite3_column_int(stmt, 0));
        printf("| Nome:      %s\n", sqlite3_column_text(stmt, 1));
        printf("| Cognome:   %s\n", sqlite3_column_text(stmt, 2));
        printf("| User name: %s\n", username);
        printf("| Password: *********\n");
        printf("| membership  date:     %s\n", sqlite3_column_text(stmt, 3));
    }
}

// +==================================================+
// |            Show my books function                |
// +==================================================+
// Enter the personal area
void showMyBooks(sqlite3 *db, char* username){

    int choice;

    printf("\033[2J\033[H");        
    printf("+-----------------------------+\n");
    printf("|        My books area        |\n");
    printf("+-----------------------------+\n");
    printf("| Please select an option:    |\n");
    printf("| 1. Show my bookshelf        |\n");
    printf("| 2. Add a new book           |\n");
    printf("+-----------------------------+\n");
    printf("--> ");
    scanf("%d", &choice);

    switch (choice)
    {
    case 1:
        showMyBookshelf(db, username);
        break;
    case 2:
        addNewBook(db);
        break;
    
    default:
        printf("\033[2J\033[H");
        printf("Invalid choice. Please try again.\n");
        profileScreen(db, username);
        break;
    }
}

// +==================================================+
// |            Show my books bookshelf               |
// +==================================================+
//will show the user's books (borrowed, read, want to read, currently reading)
void showMyBookshelf(sqlite3 *db, char* username){
    printf("\033[2J\033[H");

    // First, get the member_id from username
    sqlite3_stmt *stmt_member;
    const char *sql_member = "SELECT id FROM members WHERE USER_NAME = ?;";
    
    // Prepare the statement to get member_id
    sqlite3_prepare_v2(db, sql_member, -1, &stmt_member, 0);
    sqlite3_bind_text(stmt_member, 1, username, -1, SQLITE_STATIC);
    
    // Execute the statement and get member_id
    int member_id = -1;
    if(sqlite3_step(stmt_member) == SQLITE_ROW){
        member_id = sqlite3_column_int(stmt_member, 0);
    }
    sqlite3_finalize(stmt_member);
    

    // Now query the loans with proper JOIN to authors table
    const char *sql = 
        "SELECT "
        "    l.id AS loan_id, "
        "    b.title, "
        "    a.name, "
        "    a.surname, "
        "    l.loan_date, "
        "    l.return_date "
        "FROM loans l "
        "JOIN books b ON l.book_id = b.id "
        "JOIN authors a ON b.author_id = a.id "
        "WHERE l.member_id = ? "
        "ORDER BY l.loan_date DESC;";

    sqlite3_stmt *stmt; //create a pointer for the statement
    
    sqlite3_prepare_v2(db, sql, -1, &stmt, 0);      //prepare SQL command to be executed
    sqlite3_bind_int(stmt, 1, member_id);   //substitute ? with the member_id

    printf("+--------------------------+\n");
    printf("|      Your bookshelf      |\n");
    printf("+--------------------------+\n");
    
    int book_count = 0;
    while(sqlite3_step(stmt) == SQLITE_ROW){
        book_count++;
        printf("| Loan ID: %d\n", sqlite3_column_int(stmt, 0));
        printf("| Title: %s\n", sqlite3_column_text(stmt, 1));
        printf("| Author: %s %s\n", sqlite3_column_text(stmt, 2), sqlite3_column_text(stmt, 3));
        printf("| Loan date: %s\n", sqlite3_column_text(stmt, 4));
        printf("| Return date: %s\n", sqlite3_column_text(stmt, 5));
        printf("+--------------------------+\n");
    }
    
    if(book_count == 0){
        printf("| No books borrowed yet.   |\n");
        printf("+--------------------------+\n");
    }
    
    sqlite3_finalize(stmt);
    
    printf("\nPress Enter to go back...");
    getchar(); // consume leftover newline
    getchar(); // wait for user input
    
    printf("\033[2J\033[H");
    profileScreen(db, username);
}

// +==================================================+
// |         Add a new book to the bookshelf          |
// +==================================================+
// Adds a new book to the user's bookshelf
void addNewBook(sqlite3* db){
    printf("\033[2J\033[H");
    printf("not implemented yet");
}