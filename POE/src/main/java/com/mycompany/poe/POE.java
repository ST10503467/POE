package poe;

import java.util.Scanner;
import java.util.regex.Pattern;

public class POE {

    // Store registered credentials
    private static String registeredUsername = "";
    private static String registeredPassword = "";

    public static boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
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

    // NEW: Checks login credentials against stored values
    public static boolean login(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Registration ---
        System.out.print("Enter username (max 5 chars, must contain '_'): ");
        String username = scanner.nextLine().trim();
        if (validateUsername(username)) {
            registeredUsername = username;  // Save on success
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Invalid username: must be ≤5 characters and contain an underscore.");
        }

        System.out.print("Enter password (min 8 chars, uppercase, digit, special char): ");
        String password = scanner.nextLine().trim();
        if (validatePassword(password)) {
            registeredPassword = password;  // Save on success
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Invalid password: must be ≥8 characters and contain an uppercase letter, a digit, and a special character.");
        }

        System.out.print("Enter phone number (e.g. +27831234567 or 27831234567): ");
        String phone = scanner.nextLine().trim();
        if (validatePhoneNumber(phone)) {
            System.out.println("Cell phone number successfully captured.");
        } else {
            System.out.println("Invalid phone number: must start with +27 or 27 and be followed by exactly 9 digits.");
        }

        // --- Login ---
        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine().trim();

        if (login(loginUsername, loginPassword)) {
            System.out.println("Login successful. Welcome, " + loginUsername + "!");
        } else {
            System.out.println("Login failed: incorrect username or password.");
        }

        scanner.close();
    }
}
