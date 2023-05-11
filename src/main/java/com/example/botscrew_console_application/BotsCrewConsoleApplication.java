package com.example.botscrew_console_application;

import com.example.botscrew_console_application.command_pattern.*;
import com.example.botscrew_console_application.service.UniversityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SpringBootApplication
public class BotsCrewConsoleApplication {
    private final UniversityService universityService;
    private final List<Command> commandStrategies;

    public BotsCrewConsoleApplication(UniversityService universityService) {
        this.universityService = universityService;
        this.commandStrategies = initializeCommandStrategies();
    }

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewConsoleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startConsole() {
        Scanner scanner = new Scanner(System.in);

        // Display the welcome message
        System.out.println("Welcome to the University Console Application!");

        while (true) {
            // Read the user's command
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();;

            // Process the command
            try {
                processCommand(command);
            } catch (NoSuchElementException e) {
                System.out.println("Department not found. Please enter a valid department name.");
            }
        }
    }

    private List<Command> initializeCommandStrategies() {
        List<Command> strategies = new ArrayList<>();

        strategies.add(new HeadOfDepartmentCommand(universityService));
        strategies.add(new StatisticsCommand(universityService));
        strategies.add(new AverageSalaryCommand(universityService));
        strategies.add(new EmployeeCountCommand(universityService));

        return strategies;
    }

    public void processCommand(String commandText) {
        for (Command strategy : commandStrategies) {
            if (strategy.matchesCommand(commandText)) {
                strategy.executeCommand(commandText);
                return;
            }
        }

        System.out.println("Unknown command. Please try again.");
    }
}
