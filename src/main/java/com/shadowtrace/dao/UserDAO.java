
package com.shadowtrace.dao;

import com.shadowtrace.database.DatabaseConnection;
import com.shadowtrace.model.User;
import com.shadowtrace.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    public boolean registerUser(User user) {

        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Hash the password before storing it
            String hashedPassword =
                    PasswordUtil.hashPassword(user.getPassword());

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashedPassword);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {

                try (ResultSet result = statement.getGeneratedKeys()) {

                    if (result.next()) {
                        user.setUserId(result.getInt(1));
                    }
                }

                return true;
            }

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        return false;
    }

    public boolean emailExists(String email) {

        String query = "SELECT email FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }

        } catch (Exception e) {
            System.out.println("Email check failed: " + e.getMessage());
        }

        return false;
    }


public User loginUser(String email, String password) {

    String query = "SELECT * FROM users WHERE email = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, email);

        try (ResultSet result = statement.executeQuery()) {

            if (result.next()) {

                String storedPassword = result.getString("password");

                boolean isValid =
                        PasswordUtil.verifyPassword(
                                password, storedPassword
                        );

                if (isValid) {

                    return new User(
                            result.getInt("user_id"),
                            result.getString("username"),
                            result.getString("email"),
                            storedPassword,
                            result.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Login failed: " + e.getMessage());
    }

    return null;
}

}
