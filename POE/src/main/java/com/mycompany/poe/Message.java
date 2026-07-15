package com.mycompany.poe;

import java.util.Random;
import java.util.Scanner;

public class Message {

    private final String messageID;
    private final String recipient;
    private final String content;
    private final int messageNumber;
    private String status;

    public Message(int messageNumber, String recipient, String content) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.content = content;
        this.status = "";
        this.messageID = generateMessageID(); // Auto-generated on creation
    }

    // --- 1. Randomly generate a 10-character alphanumeric message ID ---
    private String generateMessageID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }
        return id.toString();
    }

    // --- 2. Validate recipient cell number ---
    public boolean validateRecipient(String cellNumber) {
        if (cellNumber.length() > 10) {
            System.out.println("Cell number must be no more than 10 characters.");
            return false;
        }
        if (!cellNumber.startsWith("0") && !cellNumber.startsWith("+")) {
            System.out.println("Cell number must start with '0' or '+'.");
            return false;
        }
        return true;
    }

    // --- 3. Allow user to send, store, or delete the message ---
    public String chooseMessageAction(Scanner scanner) {
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1. Send");
        System.out.println("2. Store");
        System.out.println("3. Delete");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                status = "Sent";
                System.out.println("Message sent successfully.");
                break;
            case "2":
                status = "Stored";
                System.out.println("Message stored successfully.");
                break;
            case "3":
                status = "Deleted";
                System.out.println("Message deleted successfully.");
                break;
            default:
                status = "Unknown";
                System.out.println("Invalid choice. No action taken.");
        }
        return status;
    }

    // --- Getters ---
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Message " + messageNumber
                + " | ID: " + messageID
                + " | To: " + recipient
                + " | Status: " + status
                + " | Content: " + content;
    }
}
