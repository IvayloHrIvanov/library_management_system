package org.example;

import utility.GlobalLogger;

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
            GlobalLogger.logExceptionsInFile("900", illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }
}