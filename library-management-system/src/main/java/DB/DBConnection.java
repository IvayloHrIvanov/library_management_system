package DB;

import utility.GlobalLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1634";

    private static final int MAX_DB_RETRIES = 3; // Maximum number of connection to Database retry attempts
    private static int storeDBConnectionRuns = 1; // Stores how many times the Application has restarted

    public void connectToDB() {
       try {
            // Try to establish connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connection to Database - Established");
        } catch (SQLException sqlException) {
            storeDBConnectionRuns++;

            System.out.println("Connection to Database - Failed!");

            if (storeDBConnectionRuns <= MAX_DB_RETRIES) {
                GlobalLogger.logExceptionInFile("1000", sqlException.getMessage(), sqlException);
                System.out.println("Retrying to connect...");
                connectToDB();
            } else {
                System.out.println("\nApplication failed too many times. Please check your network connection " +
                        "or verify database credentials. For further information check the Log file. " +
                        "Please contact support if the issue persists.");
                GlobalLogger.logExceptionInFile("1000", sqlException.getMessage() +
                        " Terminated program!", sqlException);
                System.exit(-1);
            }
        }
    }
}