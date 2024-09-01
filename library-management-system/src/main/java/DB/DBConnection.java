package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public void connectToDB() {
        Connection connection = null;

        String url = "jdbc:postgresql://localhost:5432/library";
        String user = "postgres";
        String password = "1634";

        try {
            // Try to establishing the connection
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Connection to Database - Established");
            } else {
                System.out.println("Connection to Database - Failed");
                connection.close();
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}