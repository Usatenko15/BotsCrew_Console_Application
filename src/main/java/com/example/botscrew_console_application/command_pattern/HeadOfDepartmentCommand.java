package com.example.botscrew_console_application.command_pattern;

import com.example.botscrew_console_application.service.UniversityService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeadOfDepartmentCommand implements Command {
    private final Pattern pattern;
    private final UniversityService universityService;

    public HeadOfDepartmentCommand(UniversityService universityService) {
        this.universityService = universityService;
        this.pattern = Pattern.compile("^Who is head of department (.+)$");
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
            String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
            System.out.println("Head of " + departmentName + " department is " + headOfDepartment);
        }
    }
}
