package com.mycompany.poe;

import java.util.Scanner;
import java.util.regex.Pattern;

public class POE {

    public static boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    public static String checkUsername(String username) {
        if (validateUsername(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    public static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        return hasUpper && hasDigit && hasSpecial;
    }

    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("(\\+27|27)[0-9]{9}");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String registeredUsername = "";
        String registeredPassword = "";

        // Registration
        System.out.print("Enter username (max 5 chars, must contain '_'): ");
        String username = scanner.nextLine().trim();
        if (validateUsername(username)) {
            registeredUsername = username;
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Invalid username: must be ≤5 characters and contain an underscore.");
        }

        System.out.print("Enter password (min 8 chars, uppercase, digit, special char): ");
        String password = scanner.nextLine().trim();
        if (validatePassword(password)) {
            registeredPassword = password;
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Invalid password: must be ≥8 characters, with an uppercase letter, digit, and special character.");
        }

        System.out.print("Enter phone number (e.g. +27831234567 or 27831234567): ");
        String phone = scanner.nextLine().trim();
        if (validatePhoneNumber(phone)) {
            System.out.println("Cell phone number successfully captured.");
        } else {
            System.out.println("Invalid phone number: must start with +27 or 27 followed by exactly 9 digits.");
        }

        // Login
        Login loginHandler = new Login(registeredUsername, registeredPassword);

        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine().trim();

        if (loginHandler.login(loginUsername, loginPassword)) {
            System.out.println("Login successful. Welcome, " + loginUsername + "!");
            showMenu(scanner);
        } else {
            System.out.println("Login failed: incorrect username or password.");
        }

        scanner.close();
    }

    private static void showMenu(Scanner scanner) {
        MessageManager messageManager = new MessageManager();
        messageManager.loadStoredMessages();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Stored messages.");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    messageManager.sendMessages(scanner);
                    break;
                case "2":
                    messageManager.viewStoredMessages();
                    break;
                case "3":
                    System.out.println("Goodbye, and good day!");
                    running = false;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 0.");
            }
        }
    }
}
