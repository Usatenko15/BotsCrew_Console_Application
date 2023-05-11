package com.example.botscrew_console_application.command_pattern;

import com.example.botscrew_console_application.service.UniversityService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AverageSalaryCommand implements Command {
    private final Pattern pattern;
    private final UniversityService universityService;

    public AverageSalaryCommand(UniversityService universityService) {
        this.universityService = universityService;
        this.pattern = Pattern.compile("^Show the average salary for the department (.+)$");
    }

    @Override
    public boolean matchesCommand(String commandText) {
        return pattern.matcher(commandText).matches();
    }

    @Override
    public void executeCommand(String commandText) {
        Matcher matcher = pattern.matcher(commandText);
        if (matcher.matches()) {
            String departmentName = matcher.group(1);

            double averageSalary = universityService.getAverageSalaryByDepartment(departmentName);
            System.out.println("The average salary of " + departmentName + " is " + averageSalary);
        }
    }
}
