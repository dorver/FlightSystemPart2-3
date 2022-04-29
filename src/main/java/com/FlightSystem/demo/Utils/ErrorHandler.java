package com.FlightSystem.demo.Utils;

import java.sql.SQLException;

public class ErrorHandler {

    public static void printError(SQLException e) {
        String message = "An error has occurred";
        if(e.getSQLState().equals("23503")) {
            message = "The object has references, please delete tem and try again";
        }else if(e.getSQLState().equals("23505")){
            message = "Unique violation";
        }
        // else ifs - can add more types of error states and handle them
        System.out.println(message);
    }
}
