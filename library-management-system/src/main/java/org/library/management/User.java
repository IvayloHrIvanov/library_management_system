package org.library.management;

import utility.GlobalLogger;

// Enum representing different roles a user can have in the system
enum Role {
    ADMIN,
    EMPLOYEE,
    CLIENT
}

public class User{
    private static Role role;

    public static String getRole(){
        return role.toString();
    }

    public static void setRole(String userRole){
        try {
            role = Role.valueOf(userRole.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            System.err.println("Role could not be set! For further information check the Log file.");
            GlobalLogger.logExceptionInFile("900", illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }
}