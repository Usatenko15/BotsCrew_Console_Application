package com.example.botscrew_console_application.command_pattern;

import com.example.botscrew_console_application.service.UniversityService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeCountCommand implements Command {
    private final Pattern pattern;
    private final UniversityService universityService;

    public EmployeeCountCommand(UniversityService universityService) {
        this.universityService = universityService;
        this.pattern = Pattern.compile("^Show count of employees for (.+)$");
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

            long employeeCount = universityService.getEmployeeCountByDepartment(departmentName);
            System.out.println("Count of employees for " + departmentName + ": " + employeeCount);
        }
    }
}
