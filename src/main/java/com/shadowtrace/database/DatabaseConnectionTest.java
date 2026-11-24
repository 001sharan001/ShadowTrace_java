
package com.shadowtrace.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection != null) {

            System.out.println("SHADOWTRACE database is ready!");

            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {

            System.out.println("Please check database settings.");
        }
    }
}
