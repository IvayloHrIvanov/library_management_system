package org.example;

import helper.Helper;
import utility.GlobalLogger;

public class Application {
    private static Book book = new Book(null, null, null);

    public static void run() {
        String title = "";
        String author = "";
        String description = "";
        String userRole = "";

        int choice = 0;

        boolean shouldRun = true;

        System.out.println("\nWelcome to the Library Management System!");
        Helper.chooseUser();

        while (shouldRun) {
            try {
                Helper.displayMenu();
                choice = Helper.getIntInput("\nPlease enter the number corresponding to the function you want: ");
                userRole = User.getRole();

                switch (choice) {
                    case 1:
                        if (userRole.equalsIgnoreCase("client")) {
                            System.out.println("A " + userRole + " cannot add books!");
                            break;
                        }

                        title = Helper.getStringInput("Enter title of the book: ");
                        author = Helper.getStringInput("Enter author of the book: ");
                        description = Helper.getStringInput("Enter description of the book: ");

                        book = new Book(title, author, description);
                        book.addBook(book);
                        break;
                    case 2:
                        if (userRole.equalsIgnoreCase("client") || userRole.equalsIgnoreCase("employee")) {
                            System.out.println("A " + User.getRole() + " cannot add books!");
                            break;
                        }

                        title = Helper.getStringInput("Enter title of the book: ");

                        book.removeBook(title);
                        break;
                    case 3:
                        book.listAllBooks();
                        break;
                    case 4:
                        title = Helper.getStringInput("Enter title of the book: ");

                        Book foundBook = book.searchBook(title);

                        if (foundBook == null) {
                            System.out.println("Book is not found!");
                            GlobalLogger.logInfoInFile("800", "Book is not found while searching!");
                            break;
                        }

                        System.out.println(foundBook.toString());
                        GlobalLogger.logInfoInFile("800", "Book is found while searching!");
                        break;
                    case 5:
                        title = Helper.getStringInput("Enter title of the book: ");

                        book.borrowBook(title);
                        break;
                    case 6:
                        title = Helper.getStringInput("Enter title of the book: ");

                        book.returnBook(title);
                        break;
                    case 7:
                        book.exportLibrary();
                        break;
                    case 8:
                        Helper.chooseUser();
                        System.out.println("User is changed to " + User.getRole());
                        break;
                    case 9:
                        book.exportLibrary();
                        System.out.println("\nThank you for using the Library Management System. Bye!");
                        shouldRun = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number corresponding to the function you want in the list!");
                        break;
                }

            } catch (Exception e) {
                System.err.println("\nUnexpected exception occurred: " + e.getMessage() + ". For further information check the Log file.");

                GlobalLogger.logExceptionsInFile("1000", e.getMessage(), e);

                System.out.println("\nAttempting to launch the program again!\n");
            }
        }
    }
}