package com.mycompany.poe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                    saveStoredMessages();
                    break;
                case "Deleted":
                    System.out.println("Message discarded.");
                    break;
            }
        }
    }
    private static final String STORED_MESSAGES_FILE = "storedMessages.json";

// Call this once, e.g. at the start of showMenu(), to load any previously stored messages
    public void loadStoredMessages() {
        try {
            if (!Files.exists(Paths.get(STORED_MESSAGES_FILE))) {
                return; // No file yet - nothing to load
            }
            String content = new String(Files.readAllBytes(Paths.get(STORED_MESSAGES_FILE)));
            if (content.trim().isEmpty()) {
                return;
            }
            JSONArray jsonArray = new JSONArray(content);
            storedMessages.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Message msg = new Message(
                        obj.getInt("messageNumber"),
                        obj.getString("recipient"),
                        obj.getString("content"),
                        obj.getString("messageID"),
                        obj.getString("status")
                );
                storedMessages.add(msg);
            }
        } catch (IOException e) {
            System.out.println("Could not load stored messages: " + e.getMessage());
        }
    }

// Call this every time storedMessages changes (i.e. after a "Store" action)
    private void saveStoredMessages() {
        JSONArray jsonArray = new JSONArray();
        for (Message msg : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("messageNumber", msg.getMessageNumber());
            obj.put("recipient", msg.getRecipient());
            obj.put("content", msg.getContent());
            obj.put("messageID", msg.getMessageID());
            obj.put("status", msg.getStatus());
            jsonArray.put(obj);
        }
        try (FileWriter writer = new FileWriter(STORED_MESSAGES_FILE)) {
            writer.write(jsonArray.toString(4)); // pretty-printed with 4-space indent
        } catch (IOException e) {
            System.out.println("Could not save stored messages: " + e.getMessage());
        }
    }

    public void viewStoredMessages() {
        if (storedMessages.isEmpty()) {
            System.out.println("\nNo stored messages.");
            return;
        }

        System.out.println("\n--- Stored Messages ---");
        for (Message msg : storedMessages) {
            System.out.println("Message ID: " + msg.getMessageID()
                    + " | Recipient: " + msg.getRecipient()
                    + " | Content: " + msg.getContent());
        }
    }
}
