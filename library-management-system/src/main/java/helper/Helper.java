package helper;

import org.example.User;

import java.util.Scanner;

public class Helper {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String[] VALID_ROLES = {"admin", "employee", "client"};

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

    public static void chooseUser() {
        String userInput = "";

        System.out.print("\nPlease enter your role (Admin, Employee, Client): ");

        while (true) {
            userInput = SCANNER.nextLine().trim();

            if (isValidRole(userInput)) {
                User.setRole(userInput);
                break;
            } else {
                System.out.print("Please provide a valid role (Admin, Employee, Client): ");
            }
        }
    }

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
                SCANNER.nextLine();
                return userInput;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                SCANNER.next();
            }
        }
    }

    public static String getStringInput(String prompt) {
        String userInput = "";

        while (true) {
            System.out.print(prompt);
            userInput = SCANNER.nextLine().trim(); // read the entire line of input and trim() removes leading and trailing whitespace

            // Check if input is not empty
            if (userInput.isEmpty()) {
                System.out.println("Input cannot be empty. Please provide a valid input.");
            } else if (userInput.length() > 45) {
                System.out.println("Input is too big! Max characters allowed: 1-45. Please provide a valid input!");
            } else {
                return userInput;
            }
        }
    }
}