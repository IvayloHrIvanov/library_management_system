package utility;

import org.library.management.User;

import java.util.Scanner;

public class Helper {
    // Scanner is used for reading user input throughout the application
    private static final Scanner SCANNER = new Scanner(System.in);
    // Array of valid user roles to validate input against
    private static final String[] VALID_ROLES = {"admin", "employee", "client"};

    // Displays the main menu options for the Library Management System
    public static void displayMenu() {
        System.out.println("\n1. Add a book");
        System.out.println("2. Remove a book");
        System.out.println("3. List all books");
        System.out.println("4. Search for a book by Title");
        System.out.println("5. Borrow a book");
        System.out.println("6. Return a book");
        System.out.println("7. Export books to a .txt file");
        System.out.println("8. Change user");
        System.out.println("9. Exit");
    }

    // Prompts the user to choose their role and validates the input
    public static void chooseUserRole() {
        String userInput = "";

        System.out.print("\nPlease enter your role (Admin, Employee, Client): ");

        while (true) {
            userInput = SCANNER.nextLine().trim(); // Reads the input and removes any leading/trailing whitespace

            if (isValidRole(userInput)) {
                User.setRole(userInput);
                break;
            } else {
                System.out.print("Please provide a valid role (Admin, Employee, Client): ");
            }
        }
    }

    // Checks if the provided role is valid by comparing it against the list of valid roles
    private static boolean isValidRole(String role) {
        for (String validRole : VALID_ROLES) {
            if (validRole.equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    // Get valid integer input for the function
    public static int getIntInput(String prompt) {
        int userInput = 0;

        while (true) {
            System.out.print(prompt);

            // Check if the next input is an integer
            if (SCANNER.hasNextInt()) {
                userInput = SCANNER.nextInt();
                SCANNER.nextLine(); // Clear the buffer
                return userInput;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                SCANNER.next(); // Clear the invalid input
            }
        }
    }

    // Prompts the user for a string input, ensures it's not empty and within a character limit
    public static String getStringInput(String prompt) {
        String userInput = "";

        while (true) {
            System.out.print(prompt);
            userInput = SCANNER.nextLine().trim(); // Reads the input and removes any leading/trailing whitespace

            // Check if input is not empty
            if (userInput.isEmpty()) {
                System.out.println("Input cannot be empty. Please provide a valid input.");
            } else if (userInput.length() > 45) { // Ensure input is within the character limit
                System.out.println("Input is too big! Max characters allowed: 1-45. Please provide a valid input!");
            } else {
                return userInput;
            }
        }
    }

    // Utility method for logging and printing messages to console
    public static void logAndPrintInfo(String consoleMessage, String logMessage) {
        System.out.println(consoleMessage);
        GlobalLogger.logInfoInFile("800", logMessage);
    }

    // Overloaded method to log and print messages when no logMessage is provided.
    public static void logAndPrintInfo(String message) {
        System.out.println(message);
        GlobalLogger.logInfoInFile("800", message);
    }
}