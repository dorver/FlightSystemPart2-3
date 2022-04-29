package com.FlightSystem.demo.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
    private Connection connection=null;
    private Statement statement=null;

    public Repository(){

    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/FlightSystemDB",
                            "postgres","postg9794");
            System.out.println("Connected");

        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
            e.printStackTrace();
        }

        return this.connection;
    }

    public Statement getStatement() {
        try {
            statement=this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
