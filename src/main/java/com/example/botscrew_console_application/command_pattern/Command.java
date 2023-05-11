package com.example.botscrew_console_application.command_pattern;

public interface Command {
    boolean matchesCommand(String commandText);
    void executeCommand(String commandText);
}
