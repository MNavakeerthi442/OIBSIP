// c program to write a library management system
#include <stdio.h>
#include <string.h>

#define MAX_BOOKS 100

// Structure to store book details
struct Book {
    int id;
    char title[50];
    char author[50];
    int isAvailable;  // 1 = Available, 0 = Borrowed
};

// Global array of books
struct Book library[MAX_BOOKS];
int bookCount = 0;

// Function to add a new book
void addBook() {
    if (bookCount >= MAX_BOOKS) {
        printf("Library is full! Cannot add more books.\n");
        return;
    }
    
    printf("Enter Book ID: ");
    scanf("%d", &library[bookCount].id);
    getchar();  // Consume newline character
    
    printf("Enter Book Title: ");
    fgets(library[bookCount].title, 50, stdin);
    library[bookCount].title[strcspn(library[bookCount].title, "\n")] = '\0'; // Remove newline
    
    printf("Enter Author Name: ");
    fgets(library[bookCount].author, 50, stdin);
    library[bookCount].author[strcspn(library[bookCount].author, "\n")] = '\0'; // Remove newline
    
    library[bookCount].isAvailable = 1;  // Mark book as available
    bookCount++;

    printf("Book added successfully!\n");
}

// Function to display all books
void displayBooks() {
    if (bookCount == 0) {
        printf("No books in the library.\n");
        return;
    }

    printf("\nLibrary Books:\n");
    printf("-----------------------------------------------------\n");
    printf("ID   | Title                  | Author              | Status\n");
    printf("-----------------------------------------------------\n");

    for (int i = 0; i < bookCount; i++) {
        printf("%d | %s | %s | %s\n", library[i].id, library[i].title, 
               library[i].author, library[i].isAvailable ? "Available" : "Borrowed");
    }
}

// Function to borrow a book
void borrowBook() {
    int id;
    printf("Enter Book ID to borrow: ");
    scanf("%d", &id);

    for (int i = 0; i < bookCount; i++) {
        if (library[i].id == id) {
            if (library[i].isAvailable) {
                library[i].isAvailable = 0;  // Mark as borrowed
                printf("You have borrowed: %s\n", library[i].title);
                return;
            } else {
                printf("Sorry, this book is already borrowed.\n");
                return;
            }
        }
    }
    printf("Book with ID %d not found.\n", id);
}

// Function to return a book
void returnBook() {
    int id;
    printf("Enter Book ID to return: ");
    scanf("%d", &id);

    for (int i = 0; i < bookCount; i++) {
        if (library[i].id == id) {
            if (!library[i].isAvailable) {
                library[i].isAvailable = 1;  // Mark as available
                printf("Book returned successfully: %s\n", library[i].title);
                return;
            } else {
                printf("This book was not borrowed.\n");
                return;
            }
        }
    }
    printf("Book with ID %d not found.\n", id);
}

// Main menu function
int main() {
    int choice;

    do {
        printf("\n===== Library Management System =====\n");
        printf("1. Add Book\n");
        printf("2. Display Books\n");
        printf("3. Borrow Book\n");
        printf("4. Return Book\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: addBook(); 
            break;
            case 2: displayBooks(); 
            break;
            case 3: borrowBook(); 
            break;
            case 4: returnBook(); 
            break;
            case 5: printf("Exiting program...\n"); 
            break;
            default: printf("Invalid choice! Please try again.\n");
        }
    } while (choice != 5);

    return 0;
}





output:
===== Library Management System =====
1. Add Book
2. Display Books
3. Borrow Book
4. Return Book
5. Exit
Enter your choice: 1
Enter Book ID: 2
Enter Book Title: tata
Enter Author Name: ratan
Book added successfully!

===== Library Management System =====
1. Add Book
2. Display Books
3. Borrow Book
4. Return Book
5. Exit
Enter your choice: 2

Library Books:
-----------------------------------------------------
ID   | Title                  | Author              | Status
-----------------------------------------------------
2 | tata | ratan | Available

===== Library Management System =====
1. Add Book
2. Display Books
3. Borrow Book
4. Return Book
5. Exit
Enter your choice: 3
Enter Book ID to borrow: 2
You have borrowed: tata

===== Library Management System =====
1. Add Book
2. Display Books
3. Borrow Book
4. Return Book
5. Exit
Enter your choice: 4
Enter Book ID to return: 2
Book returned successfully: tata

===== Library Management System =====
1. Add Book
2. Display Books
3. Borrow Book
4. Return Book
5. Exit
Enter your choice: 5
Exiting program...


=== Code Execution Successful ===
