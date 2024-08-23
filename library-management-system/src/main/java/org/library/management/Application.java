package org.library.management;

import utility.Helper;
import utility.GlobalLogger;

public class Application {
    // Book object initialized with null values, serves as a placeholder for future book operations.
    private static Book book = new Book(null, null, null);

    public static void run() {
        // Variables for storing book details and user role
        String title = "";
        String author = "";
        String description = "";
        String userRole = "";

        int choice = 0; // Stores user choice from the menu

        boolean shouldRun = true; // Controls the main loop of the application

        System.out.println("\nWelcome to the Library Management System!");
        Helper.chooseUserRole(); // Asks the user to choose their role

        while (shouldRun) {
            try {
                Helper.displayMenu(); // Displays the menu options
                choice = Helper.getIntInput("\nPlease enter the number corresponding to the function you want: ");
                userRole = User.getRole(); // Saves the current user's role

                switch (choice) {
                    case 1:
                        // Check if the user is allowed to add books (only Admin or Employee)
                        if (userRole.equalsIgnoreCase("client")) {
                            System.out.println("A " + userRole + " cannot add books!");
                            break;
                        }

                        // Gather book details from user input
                        title = Helper.getStringInput("Enter title of the book: ");
                        author = Helper.getStringInput("Enter author of the book: ");
                        description = Helper.getStringInput("Enter description of the book: ");

                        // Create a new book and add it to the Library
                        book = new Book(title, author, description);
                        book.addBook(book);
                        break;
                    case 2:
                        // Check if the user is allowed to remove books (only Admin)
                        if (userRole.equalsIgnoreCase("client") || userRole.equalsIgnoreCase("employee")) {
                            System.out.println("A " + User.getRole() + " cannot add books!");
                            break;
                        }

                        // Get the title of the book and remove it from the Library
                        title = Helper.getStringInput("Enter title of the book: ");
                        book.removeBook(title);
                        break;
                    case 3:
                        // List all books in the Library
                        book.listAllBooks();
                        break;
                    case 4:
                        // Search for a book by its title
                        title = Helper.getStringInput("Enter title of the book: ");
                        Book foundBook = book.searchBook(title);

                        // Check if the book was found and log the result
                        if (foundBook == null) {
                            System.out.println("Book is not found!");
                            GlobalLogger.logInfoInFile("800", "Book is not found while searching!");
                            break;
                        }

                        System.out.println(foundBook.toString());
                        GlobalLogger.logInfoInFile("800", "Book is found while searching!");
                        break;
                    case 5:
                        // Borrow a book by title
                        title = Helper.getStringInput("Enter title of the book: ");
                        book.borrowBook(title);
                        break;
                    case 6:
                        // Return a borrowed book by title
                        title = Helper.getStringInput("Enter title of the book: ");
                        book.returnBook(title);
                        break;
                    case 7:
                        // Export the current library state to a file
                        book.exportLibrary();
                        break;
                    case 8:
                        // Change the current user role
                        Helper.chooseUserRole();
                        System.out.println("User is changed to " + User.getRole());
                        break;
                    case 9:
                        // Exit the application after exporting the library state
                        book.exportLibrary();
                        System.out.println("\nThank you for using the Library Management System. Bye!");
                        shouldRun = false;
                        break;
                    default:
                        // Handle invalid menu choices
                        System.out.println("Please enter a valid number corresponding to the function you want in the list!");
                        break;
                }

            } catch (Exception e) {
                // Catch any unexpected exceptions to prevent the program from crashing
                System.err.println("\nUnexpected exception occurred: " + e.getMessage() + ". For further information check the Log file.");
                GlobalLogger.logExceptionsInFile("1000", e.getMessage(), e);

                // Attempt to restart the main loop after an exception
                System.out.println("\nAttempting to launch the program again!\n");
            }
        }
    }
}