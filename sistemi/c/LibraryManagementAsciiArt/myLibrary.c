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
            printf("\nExiting the program... Goodbye!\n");
            break;
        default:
            printf("\nInvalid choice. Please try again.\n");
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

        // Get user_id from username
        sqlite3_stmt *stmt;
        const char *sql = "SELECT id FROM members WHERE USER_NAME = ?;";
        sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
        sqlite3_bind_text(stmt, 1, username, -1, SQLITE_STATIC);
        
        int user_id = -1;
        if(sqlite3_step(stmt) == SQLITE_ROW) {
            user_id = sqlite3_column_int(stmt, 0);
        }
        sqlite3_finalize(stmt);
        
        // Proceed to the user's profile screen
        profileScreen(db, user_id);
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
    const char *sql = "SELECT id FROM members WHERE USER_NAME = ?;";
    sqlite3_stmt *stmt;     // Declare a statement object to hold the compiled SQL statement

    sqlite3_prepare_v2(db, sql, -1, &stmt, 0);

    sqlite3_bind_text(stmt, 1 ,username, -1, SQLITE_STATIC);

    int exists = (sqlite3_step(stmt) == SQLITE_ROW);
    int user_id = exists ? sqlite3_column_int(stmt, 0) : -1; // Get the user ID if the username exists, otherwise set to -1

    sqlite3_finalize(stmt);

    if(exists){
        //cls
        printf("\033[2J\033[H");
        printf("\nUsername already exists. Please choose a different username.\n");
        
        //call signUpScreen again
        signUpScreen(db);
    } else {
        // Create INSERT statement for new user
        time_t t = time(NULL);                                      // Get the current time
        struct tm *tm = localtime(&t);                              // Convert the time to local time structure
        char date_string[20];
        strftime(date_string, sizeof(date_string), "%Y-%m-%d", tm); // Form the local date
        
        const char *sql_insert = "INSERT INTO members(name, surname, USER_NAME, PASSWORD, membership_date) VALUES (?, ?, ?, ?, ?);";
        sqlite3_stmt *stmt_insert;
        
        sqlite3_prepare_v2(db, sql_insert, -1, &stmt_insert, 0);
        sqlite3_bind_text(stmt_insert, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt_insert, 2, surname, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt_insert, 3, username, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt_insert, 4, password, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt_insert, 5, date_string, -1, SQLITE_STATIC);
        
        if (sqlite3_step(stmt_insert) == SQLITE_DONE) {
            printf("\033[2J\033[H");
            printf("\nSign up successful! You can now log in with your new account.\n");
            sqlite3_finalize(stmt_insert);
            logInScreen(db);
        } else {
            printf("Error signing up: %s\n", sqlite3_errmsg(db));
            sqlite3_finalize(stmt_insert);
        }
    }
}

// +==================================================+
// |                Profile Screen function           |
// +==================================================+
//will show a display with some options 
//show renting books status, show my books status, show my personal information, log out

void profileScreen(sqlite3 *db, int user_id){
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
        showPersonalInfo(db, user_id);
        break;
    case 2:
        showMyBooks(db, user_id);
        break;
    case 3:
        printf("\033[2J\033[H");
        printf("\nLogging out... Goodbye!\n");
        menuScreen();
        break;

    default:
        printf("\033[2J\033[H");
        printf("\nInvalid choice. Please try again.\n");
        choice = 0; // Reset choice to avoid infinite loop
        profileScreen(db, user_id);
        break;
    }
}

// +==================================================+
// |             Show personal info function          |
// +==================================================+
//will show the user's personal information (name, surname, date of birth, username, date of membership)
void showPersonalInfo(sqlite3 *db, int user_id){
    printf("\033[2J\033[H");

    sqlite3_stmt *stmt; //create a pointer for the statement
                                                                      
    const char *sql = "SELECT name, surname, USER_NAME, membership_date FROM members WHERE id = ?;";  //query SQL
    sqlite3_prepare_v2(db, sql, -1, &stmt, 0);      //prepare SQL command to be executed

    sqlite3_bind_int(stmt, 1, user_id);   //substitute ? with the username and get all other datas

    if(sqlite3_step(stmt) == SQLITE_ROW){
        printf("+----------------------------+\n");
        printf("|     informazioni account   |\n");
        printf("+----------------------------+\n");
        printf("| ID:        %d\n", user_id);
        printf("| Nome:      %s\n", sqlite3_column_text(stmt, 0));
        printf("| Cognome:   %s\n", sqlite3_column_text(stmt, 1));
        printf("| User name: %s\n", sqlite3_column_text(stmt, 2));
        printf("| Password: *********\n");
        printf("| membership  date:     %s\n", sqlite3_column_text(stmt, 3));
    }
}

// +==================================================+
// |            Show my books function                |
// +==================================================+
// Enter the personal area
void showMyBooks(sqlite3 *db, int user_id){

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
        showMyBookshelf(db, user_id);
        break;
    case 2:
        addNewBook(db, user_id);
        break;
    case 3:
        searchBookScreen(db, user_id);
        break;
    
    default:
        printf("\033[2J\033[H");
        printf("Invalid choice. Please try again.\n");
        choice = 0; // Reset choice to avoid infinite loop
        profileScreen(db, user_id);
        break;
    }
}

// +==================================================+
// |            Show my books bookshelf               |
// +==================================================+
//will show the user's books (borrowed, read, want to read, currently reading)
void showMyBookshelf(sqlite3 *db, int user_id){
    printf("\033[2J\033[H");

    //Prepare the statement to get book details with proper JOIN
    const char *sql_book = 
        "SELECT loans.id, books.title, authors.name, authors.surname, loans.loan_date, loans.return_date "
        "FROM loans "
        "JOIN books ON loans.book_id = books.id "
        "JOIN authors ON books.author_id = authors.id "
        "WHERE loans.member_id = ?";
    sqlite3_stmt *stmt_book;
    sqlite3_prepare_v2(db, sql_book, -1, &stmt_book, 0);
    sqlite3_bind_int(stmt_book, 1, user_id); // Bind the member_id to the query
    
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
    profileScreen(db, user_id);
}

// +==================================================+
// |         Add a new book to the database           |
// +==================================================+
// Adds a new book to the db
void addNewBook(sqlite3* db, int user_id){
    printf("\033[2J\033[H");
    
    printf("+-----------------------------+\n");
    printf("|         Add a new book      |\n");
    printf("+-----------------------------+\n");
    char title[40], author_name[20], author_surname[20], genre[20], isbn[20];
    int publication_year;
    printf("| Enter the title of the book: ");
    scanf(" %[^\n]", title); // Read the title with spaces
    printf("| Enter the author's name: ");
    scanf(" %[^\n]", author_name);
    printf("| Enter the author's surname: ");
    scanf(" %[^\n]", author_surname);
    printf("| Enter the genre of the book: ");
    scanf(" %[^\n]", genre);
    printf("| Enter the ISBN of the book: ");
    scanf(" %[^\n]", isbn);
    printf("| Enter the publication year: ");
    scanf("%d", &publication_year);

    int author_id = -1;
    sqlite3_stmt *author_stmt;
    const char *sql_find_author = "SELECT id FROM authors WHERE name = ? AND surname = ?;";

    sqlite3_prepare_v2(db, sql_find_author, -1, &author_stmt, 0);
    sqlite3_bind_text(author_stmt, 1, author_name, -1, SQLITE_STATIC);
    sqlite3_bind_text(author_stmt, 2, author_surname, -1, SQLITE_STATIC);

    if (sqlite3_step(author_stmt) == SQLITE_ROW) {
        author_id = sqlite3_column_int(author_stmt, 0);
        sqlite3_finalize(author_stmt);
    } else {
        sqlite3_finalize(author_stmt);

        sqlite3_stmt *stmt_insert_author;
        const char *sql_insert_author = "INSERT INTO authors(name, surname) VALUES (?, ?);";
        sqlite3_prepare_v2(db, sql_insert_author, -1, &stmt_insert_author, 0);
        sqlite3_bind_text(stmt_insert_author, 1, author_name, -1, SQLITE_STATIC);
        sqlite3_bind_text(stmt_insert_author, 2, author_surname, -1, SQLITE_STATIC);

        // Insert the new author into the database
        if (sqlite3_step(stmt_insert_author) == SQLITE_DONE) {
            author_id = (int)sqlite3_last_insert_rowid(db);
        } else {
            printf("\nError during author insertion: %s\n", sqlite3_errmsg(db));
            sqlite3_finalize(stmt_insert_author);
            printf("\nPress Enter to go back...");
            getchar();
            getchar();
            profileScreen(db, user_id);
            return;
        }
        sqlite3_finalize(stmt_insert_author);
    }

    const char *sql_insert_book = "INSERT INTO books(title, publication_year, genre, isbn, author_id) VALUES (?, ?, ?, ?, ?);";
    sqlite3_stmt *stmt_insert_book;
    sqlite3_prepare_v2(db, sql_insert_book, -1, &stmt_insert_book, 0);
    sqlite3_bind_text(stmt_insert_book, 1, title, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt_insert_book, 2, publication_year);
    sqlite3_bind_text(stmt_insert_book, 3, genre, -1, SQLITE_STATIC);
    sqlite3_bind_text(stmt_insert_book, 4, isbn, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt_insert_book, 5, author_id);

    if (sqlite3_step(stmt_insert_book) == SQLITE_DONE) {
            printf("\033[2J\033[H");
            printf("\nBook added successfully!\n");
            sqlite3_finalize(stmt_insert_book);
        } else {
            printf("\nError during book insertion: %s\n", sqlite3_errmsg(db));
            sqlite3_finalize(stmt_insert_book);
        }

    printf("\033[2J\033[H");
    printf("Press Enter to go back...");
    getchar(); // consume leftover newline
    getchar(); // wait for user input

    profileScreen(db, user_id);

}


// +==================================================+
// |         Rent a new book to the bookshelf         |
// +==================================================+
// Rents a new book to the user's bookshelf
void rentNewBook(sqlite3* db, int title_id, int user_id){
    printf("\033[2J\033[H");
    
    // Get today's date
    time_t t = time(NULL);
    struct tm *tm = localtime(&t);
    char loan_date[20];
    strftime(loan_date, sizeof(loan_date), "%Y-%m-%d", tm);
    
    sqlite3_stmt *stmt_insert; //create a pointer for the statement
    const char *sql_insert = "INSERT INTO loans(member_id, book_id, loan_date) VALUES (?, ?, ?);"; //query SQL to insert a new loan
    sqlite3_prepare_v2(db, sql_insert, -1, &stmt_insert, 0);    //prepare SQL command to be executed
    sqlite3_bind_int(stmt_insert, 1, user_id); // Bind the member_id to the query
    sqlite3_bind_int(stmt_insert, 2, title_id);  // Bind the book_id to the query
    sqlite3_bind_text(stmt_insert, 3, loan_date, -1, SQLITE_STATIC); // Bind the loan date
    
    if(sqlite3_step(stmt_insert) == SQLITE_DONE) {
        printf("Book rented successfully!\n");
    } else {
        printf("Error renting book: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt_insert);
    
    printf("\nPress Enter to go back...");
    getchar(); // consume leftover newline
    getchar(); // wait for user input
    printf("\033[2J\033[H");
    profileScreen(db, user_id);
}


//+==================================================+
//|         Show the search book screen              |
//+==================================================+
//will show a display with some options
void searchBookScreen(sqlite3* db, int user_id){

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

    printf("\033[2J\033[H");

    switch (choice)
    {    
    case 1:
        searchByTitle(db, user_id);
        break;
    case 2:
        searchByAuthor(db, user_id);
        break;
    default:
        printf("Invalid choice. Please try again.\n");
        searchBookScreen(db, user_id);
        break;

    }
}

// +==================================================+
// |         Search by title                          |
// +==================================================+
//will show a display with some options
void searchByTitle(sqlite3* db, int user_id){

    char title[40];
    printf("\nEnter the title of the book: ");
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
        printf("\033[2J\033[H");

        switch (choice)
        {        
        case 1:
            searchByTitle(db, user_id);
            break;
        case 2:
            addNewBook(db, user_id);
            break;
        case 3:
            profileScreen(db, user_id);
            break;
        default:   
            printf("Invalid choice. Please try again.\n");
            choice = 0; // Reset choice to avoid infinite loop
            searchByTitle(db, user_id);
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

        printf("\033[2J\033[H");

        switch (choice)
        {
        case 1:
            rentNewBook(db, book_id, user_id);
            break;

        case 2:
            profileScreen(db, user_id);
            break;
        
        default:
            printf("invalid choice, please try again.");
            choice = 0; // Reset choice to avoid infinite loop
            searchByTitle(db, user_id);
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

void searchByAuthor(sqlite3 *db, int user_id){

    printf("+-----------------------------+\n");
    printf("|       Search by author      |\n");
    printf("+-----------------------------+\n");
    char author_name[20], author_surname[20];

    printf("| Enter the author's name: ");
    getchar(); // Consume the newline
    scanf("%19[^\n]", author_name);
    
    printf("| Enter the author's surname: ");
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
        printf("+----------------------------------+\n");
        printf("| Please select an option:         |\n");
        printf("| 1. Search again                  |\n");
        printf("| 2. Go back to the profile screen |\n");
        printf("+----------------------------------+\n");
        printf("--> ");
        int choice;
        scanf("%d", &choice);
        
        sqlite3_finalize(stmt_find_author);
        
        switch (choice)
        {        
        case 1:
            searchByAuthor(db, user_id);
            break;
        case 2:
            profileScreen(db, user_id);
            break;
        default:
            printf("\033[2J\033[H");
            printf("Invalid choice. Please try again.\n");
            choice = 0; // Reset choice to avoid infinite loop
            searchByAuthor(db, user_id);
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
