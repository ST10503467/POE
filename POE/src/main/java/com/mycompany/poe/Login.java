package com.mycompany.poe;

public class Login {
    private String registeredUsername;
    private String registeredPassword;

    // Constructor accepts the registered credentials
    public Login(String registeredUsername, String registeredPassword) {
        this.registeredUsername = registeredUsername;
        this.registeredPassword = registeredPassword;
    }

    // Validates login attempt against stored credentials
    public boolean login(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Returns a formatted login result message
    public String getLoginMessage(String username, String password) {
        if (login(username, password)) {
            return "Login successful. Welcome, " + username + "!";
        } else {
            return "Login failed: incorrect username or password.";
        }
    }
}