
package com.shadowtrace.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/shadowtrace_db";

    private static final String USER = "root";

    private static final String PASSWORD = "ENTER_YOUR_MYSQL_PASSWORD";

    public static Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            System.out.println("Database connected successfully!");

            return connection;

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());

            return null;
        }
    }
}

