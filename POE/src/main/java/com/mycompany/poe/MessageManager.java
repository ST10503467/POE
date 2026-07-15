package com.mycompany.poe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageManager {

    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();

    public void sendMessages(Scanner scanner) {
        System.out.print("\nHow many messages would you like to send? ");

        int total;
        try {
            total = Integer.parseInt(scanner.nextLine().trim());
            if (total <= 0) {
                System.out.println("Please enter a number greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            return;
        }

        for (int i = 1; i <= total; i++) {
            System.out.println("\n--- Message " + i + " of " + total + " ---");

            // --- Validate Recipient ---
            String recipient;
            Message tempMsg = new Message(i, "", ""); // temp instance for validation
            while (true) {
                System.out.print("Enter recipient cell number (max 10 chars, starts with 0 or +): ");
                recipient = scanner.nextLine().trim();
                if (tempMsg.validateRecipient(recipient)) {
                    break;
                }
            }

            // --- Enter message content ---
            System.out.print("Enter message: ");
            String content = scanner.nextLine().trim();

            // --- Create message (ID auto-generated) and choose action ---
            Message message = new Message(i, recipient, content);
            System.out.println("Generated Message ID: " + message.getMessageID());
            String status = message.chooseMessageAction(scanner);

            switch (status) {
                case "Sent":
                    sentMessages.add(message);
                    break;
                case "Stored":
                    storedMessages.add(message);
                    break;
                case "Deleted":
                    System.out.println("Message discarded.");
                    break;
            }
        }
    }

public void viewStoredMessages() {
    if (storedMessages.isEmpty()) {
        System.out.println("\nNo stored messages.");
        return;
    }

    System.out.println("\n--- Stored Messages ---");
    for (Message msg : storedMessages) {
        System.out.println("Message ID: " + msg.getMessageID() +
                            " | Recipient: " + msg.getRecipient() +
                            " | Content: " + msg.getContent());
    }
}
}
