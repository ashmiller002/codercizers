package capstone.workout_buddy.controllers;

import java.time.LocalDateTime;

public class Errors {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;

    public Errors(String message) {
        this.message = message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
}
