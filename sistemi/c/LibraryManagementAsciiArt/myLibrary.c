#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
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
            choice = 0; // Reset choice to avoid infinite loop
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
        choice = 0; // Reset choice to avoid infinite loop
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
    printf("| 2. Add a book               |\n");
    printf("| 3. Rent a new book          |\n");
    printf("+-----------------------------+\n");
    printf("--> ");
    scanf("%d", &choice);

    switch (choice)
    {
    case 1:
        showMyBookshelf(db, username);
        break;
    case 2:
        addNewBook(db, username);
        break;
    case 3:
        searchBookScreen(db, username);
        break;
    
    default:
        printf("\033[2J\033[H");
        printf("Invalid choice. Please try again.\n");
        choice = 0; // Reset choice to avoid infinite loop
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

    sqlite3_stmt *stmt_username; //create a pointer for the statement
    const char *sql_username = "SELECT id FROM members WHERE USER_NAME = ?;";  //query SQL to get the member_id from username
    sqlite3_prepare_v2(db, sql_username, -1, &stmt_username, 0);      //prepare SQL command to be executed
    sqlite3_bind_text(stmt_username, 1, username, -1, SQLITE_STATIC);   //substitute ? with the username and get the member_id+
    
    // Execute the query to get the member_id
    if(sqlite3_step(stmt_username) != SQLITE_ROW){
        printf("Error: User not found.\n");
        sqlite3_finalize(stmt_username);
        return;
    }
    
    int member_id = sqlite3_column_int(stmt_username, 0);   // Get the member_id from the query result
    sqlite3_finalize(stmt_username);

    //Prepare the statement to get book details with proper JOIN
    const char *sql_book = 
        "SELECT loans.id, books.title, authors.name, authors.surname, loans.loan_date, loans.return_date "
        "FROM loans "
        "JOIN books ON loans.book_id = books.id "
        "JOIN authors ON books.author_id = authors.id "
        "WHERE loans.member_id = ?";
    sqlite3_stmt *stmt_book;
    sqlite3_prepare_v2(db, sql_book, -1, &stmt_book, 0);
    sqlite3_bind_int(stmt_book, 1, member_id); // Bind the member_id to the query
    
    printf("+--------------------------+\n");
    printf("|      Your bookshelf      |\n");
    printf("+--------------------------+\n");
    
    int book_count = 0;
    while(sqlite3_step(stmt_book) == SQLITE_ROW){
        book_count++;
        printf("+ %d. -------------------\n", book_count);
        printf("| Loan ID: %d\n", sqlite3_column_int(stmt_book, 0));
        printf("| Title: %s\n", sqlite3_column_text(stmt_book, 1));
        printf("| Author: %s %s\n", sqlite3_column_text(stmt_book, 2), sqlite3_column_text(stmt_book, 3));
        printf("| Loan date: %s\n", sqlite3_column_text(stmt_book, 4));
        printf("| Return date: %s\n", sqlite3_column_text(stmt_book, 5));
        printf("+--------------------------+\n");
    }
    
    if(book_count == 0){
        printf("| No books borrowed yet.   |\n");
        printf("+--------------------------+\n");
    }
    
    sqlite3_finalize(stmt_book);
    
    printf("\nPress Enter to go back...");
    getchar(); // consume leftover newline
    getchar(); // wait for user input
    
    printf("\033[2J\033[H");
    profileScreen(db, username);
}

// +==================================================+
// |         Add a new book to the bookshelf          |
// +==================================================+
// Adds a new book to the db
void addNewBook(sqlite3* db, char* username){
    printf("\033[2J\033[H");
    printf("not implemented yet");
}
// +==================================================+
// |         Rent a new book to the bookshelf         |
// +==================================================+
// Rents a new book to the user's bookshelf
void rentNewBook(sqlite3* db, char* title, char* username){
    printf("\033[2J\033[H");
    printf("not implemented yet");
}

//+==================================================+
//|         Show the search book screen              |
//+==================================================+
//will show a display with some options
void searchBookScreen(sqlite3* db, char* username){

    printf("\033[2J\033[H");
    char titleOrAuthor[50];

    printf("+-----------------------------+\n");
    printf("|       Search for a book     |\n");
    printf("+-----------------------------+\n");
    printf("| Please select an option     |\n");
    printf("| 1. Search by title          |\n");
    printf("| 2. Search by author         |\n");
    printf("+-----------------------------+\n");
    printf("--> ");
    int choice;
    scanf("%d", &choice);

    switch (choice)
    {    
    case 1:
        searchByTitle(db, username);
        break;
    case 2:
        searchByAuthor(db, username);
        break;
    default:
        printf("Invalid choice. Please try again.\n");
        searchBookScreen(db, username);
        break;

    }
}

// +==================================================+
// |         Search by title                          |
// +==================================================+
//will show a display with some options
void searchByTitle(sqlite3* db, char* username){
    printf("\033[2J\033[H");

    char title[40];
    printf("Enter the title of the book: ");
    scanf(" %[^\n]", title); // Read the title with spaces

    printf("\n+--------------------------------\n");
    
    // search into books title
    const char *sql_title = "SELECT id FROM books WHERE title = ?;";
    sqlite3_stmt *title_stmt;   //pointer for the statement

    sqlite3_prepare_v2(db, sql_title, -1, &title_stmt, 0);
    sqlite3_bind_text(title_stmt, 1, title, -1, SQLITE_STATIC);    //substitute ? with the title and get book id

    if(sqlite3_step(title_stmt) != SQLITE_ROW){
        sqlite3_finalize(title_stmt);

        printf("| No books found with the title: %s\n", title);
        printf("| please select an option: \n");
        printf("| 1. Search again\n");
        printf("| 2. Add a new book with this title\n");
        printf("| 3. Go back to the profile screen\n");
        printf("+--------------------------------\n");
        printf("--> ");
        int choice;
        scanf("%d", &choice);

        // Clear the title variable to avoid confusion in the next steps
        strcpy(title, "");

        switch (choice)
        {        
        case 1:
            searchByTitle(db, username);
            break;
        case 2:
            addNewBook(db, username);
            break;
        case 3:
            profileScreen(db, title);
            break;
        default:    
            printf("\033[2J\033[H");
            printf("Invalid choice. Please try again.\n");
            choice = 0; // Reset choice to avoid infinite loop
            searchByTitle(db, username);
            break;
        }
    }

    int book_id = sqlite3_column_int(title_stmt, 0);   // Get the book_id from the query result
    sqlite3_finalize(title_stmt);

    //print book details
    const char *sql_book = "SELECT books.title, authors.name, authors.surname, books.genre, books.isbn, books.publication_year "
                            "FROM books "
                            "JOIN authors ON books.author_id = authors.id "
                            "WHERE books.id = ?;";
    sqlite3_stmt *book_stmt;
    sqlite3_prepare_v2(db, sql_book, -1, &book_stmt, 0);
    sqlite3_bind_int(book_stmt, 1, book_id); // Bind the book_id to the query

    if(sqlite3_step(book_stmt) == SQLITE_ROW){
        printf("| Title: %s\n", sqlite3_column_text(book_stmt, 0));
        printf("| Author: %s %s\n", sqlite3_column_text(book_stmt, 1), sqlite3_column_text(book_stmt, 2));
        printf("| Genre: %s\n", sqlite3_column_text(book_stmt, 3));
        printf("| ISBN: %.2f\n", sqlite3_column_text(book_stmt, 4));
        printf("| Publication Year: %d\n", sqlite3_column_int(book_stmt, 5));
        printf("+--------------------------------\n");

        sqlite3_finalize(book_stmt);

        printf("Please select an option: \n");
        printf("1. Rent this book\n");
        printf("2. Go back to the profile screen\n");
        printf("--> ");
        int choice;
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("\033[2J\033[H");
            rentNewBook(db, title, username);
            break;

        case 2:
            printf("\033[2J\033[H");
            profileScreen(db, username);
            break;
        
        default:
            printf("\033[2J\033[H");
            printf("invalid choice, please try again.");
            choice = 0; // Reset choice to avoid infinite loop
            searchByTitle(db, username);
            break;
        }
    } else {
        printf("| No details found for the book with title: %s\n", title);
        printf("+--------------------------------\n");
    }

}

// +==================================================+
// |         Search by author                         |
// +==================================================+
//will show a display with some options

void searchByAuthor(sqlite3 *db, char *username){

    printf("+-----------------------------+\n");
    printf("|       Search by author      |\n");
    printf("+-----------------------------+\n");
    char author_name[20], author_surname[20];

    printf("| Enter the author's name: \n");
    getchar(); // Consume the newline
    scanf("%19[^\n]", author_name);
    
    printf("| Enter the author's surname: \n");
    getchar(); // Consume the newline
    scanf("%19[^\n]", author_surname);
    

    // First, find the author's ID by name and surname
    sqlite3_stmt *stmt_find_author;   //pointer for finding author
    const char *sql_find_author = "SELECT id FROM authors WHERE name = ? AND surname = ?;";
    
    sqlite3_prepare_v2(db, sql_find_author, -1, &stmt_find_author, 0);
    sqlite3_bind_text(stmt_find_author, 1, author_name, -1, SQLITE_STATIC);
    sqlite3_bind_text(stmt_find_author, 2, author_surname, -1, SQLITE_STATIC);
    
    if(sqlite3_step(stmt_find_author) != SQLITE_ROW){
        printf("\033[2J\033[H");
        printf("| No books found for the author: %s %s\n", author_name, author_surname);
        printf("+------------------------------+\n");
        printf("| Please select an option:     |\n");
        printf("| 1. Search again              |\n");
        printf("| 2. Go back to the profile screen |\n");
        printf("+------------------------------+\n");
        printf("--> ");
        int choice;
        scanf("%d", &choice);
        
        sqlite3_finalize(stmt_find_author);
        
        switch (choice)
        {        
        case 1:
            searchByAuthor(db, username);
            break;
        case 2:
            profileScreen(db, username);
            break;
        default:
            printf("\033[2J\033[H");
            printf("Invalid choice. Please try again.\n");
            choice = 0; // Reset choice to avoid infinite loop
            searchByAuthor(db, username);
            break;
        }
        return;
    }
    
    int author_id = sqlite3_column_int(stmt_find_author, 0);
    sqlite3_finalize(stmt_find_author);

    // Now query books by this author ID
    sqlite3_stmt *stmt_author;   //pointer for the statement
    const char *sql_author = "SELECT books.title, authors.name, authors.surname, books.genre, books.isbn, books.publication_year "
                             "FROM books "
                             "JOIN authors ON books.author_id = authors.id "
                             "WHERE authors.id = ?;";

    sqlite3_prepare_v2(db, sql_author, -1, &stmt_author, 0);
    sqlite3_bind_int(stmt_author, 1, author_id);

    printf("\033[2J\033[H");
    printf("Found some books by the author %s %s:\n", author_name, author_surname);

    int book_count = 0;
    while(sqlite3_step(stmt_author) == SQLITE_ROW){
        book_count++;
        printf("+ %d. -------------------\n", book_count);
        printf("| Title: %s\n", sqlite3_column_text(stmt_author, 0));
        printf("| Author: %s %s\n", sqlite3_column_text(stmt_author, 1), sqlite3_column_text(stmt_author, 2));
        printf("| Genre: %s\n", sqlite3_column_text(stmt_author, 3));
        printf("| ISBN: %.2f\n", sqlite3_column_text(stmt_author, 4));
        printf("| Publication Year: %d\n", sqlite3_column_int(stmt_author, 5));
        printf("+--------------------------+\n");
    }

    sqlite3_finalize(stmt_author);

}