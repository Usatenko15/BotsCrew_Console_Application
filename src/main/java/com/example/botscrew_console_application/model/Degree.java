package com.example.botscrew_console_application.model;

public enum Degree {

    ASSISTANT("Assistant"),
    ASSOCIATE_PROFESSOR("Associate Professor"),
    PROFESSOR("Professor");

    private final String displayName;

    Degree(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
