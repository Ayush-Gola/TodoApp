package com.example.todoapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {
    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidPriority(String input) {
        try {
            int p = Integer.parseInt(input);
            return p > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidId(String input) {
        try {
            int id = Integer.parseInt(input);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidBoolean(String input) {
        return "true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input);
    }

    public static boolean isValidEndTime(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime entered = LocalDateTime.parse(input, formatter);
            LocalDateTime now = LocalDateTime.now();
            return !entered.isBefore(now); // Only allow present or future
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}