package com.example.botscrew_console_application.command_pattern;

import com.example.botscrew_console_application.service.UniversityService;
import com.example.botscrew_console_application.model.Degree;
import com.example.botscrew_console_application.model.Department;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatisticsCommand implements Command {
    private final Pattern pattern;
    private final UniversityService universityService;

    public StatisticsCommand(UniversityService universityService) {
        this.universityService = universityService;
        this.pattern = Pattern.compile("^Show (.+) statistics$");
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

            Department department = universityService.findByName(departmentName);
            int assistantsCount = universityService.getLectorsCountByDegree(department, Degree.ASSISTANT);
            int associateProfessorsCount = universityService.getLectorsCountByDegree(department, Degree.ASSOCIATE_PROFESSOR);
            int professorsCount = universityService.getLectorsCountByDegree(department, Degree.PROFESSOR);

            System.out.println("Statistics for " + departmentName + " department:");
            System.out.println("Assistants: " + assistantsCount);
            System.out.println("Associate Professors: " + associateProfessorsCount);
            System.out.println("Professors: " + professorsCount);
        }
    }
}
