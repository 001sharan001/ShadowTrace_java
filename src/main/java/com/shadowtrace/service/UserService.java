
package com.shadowtrace.service;

import com.shadowtrace.dao.UserDAO;
import com.shadowtrace.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Register a new detective
    public String register(String username, String email, String password) {

        // Check empty fields
        if (username == null || username.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.isEmpty()) {

            return "All fields are required!";
        }

        // Check duplicate email
        if (userDAO.emailExists(email)) {
            return "Email is already registered!";
        }

        // Create new User object
        User user = new User(
                0,
                username,
                email,
                password,
                null
        );

        // Save user in database
        boolean registered = userDAO.registerUser(user);

        if (registered) {
            return "Registration successful!";
        }

        return "Registration failed!";
    }

public User login(String email, String password) {

    if (email == null || email.trim().isEmpty()
            || password == null || password.isEmpty()) {

        return null;
    }

    return userDAO.loginUser(email, password);
}

}
