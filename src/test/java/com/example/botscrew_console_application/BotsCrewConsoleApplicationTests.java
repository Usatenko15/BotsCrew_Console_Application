package com.example.botscrew_console_application;

import com.example.botscrew_console_application.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BotsCrewConsoleApplicationTests {
    private BotsCrewConsoleApplication application;

    @Mock
    private UniversityService universityService;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStream));
        application = new BotsCrewConsoleApplication(universityService);
    }

    @Test
    public void testHeadOfDepartmentCommand() {
        String departmentName = "Computer Science";
        String headOfDepartment = "John Doe";
        String command = "Who is head of department " + departmentName;

        when(universityService.getHeadOfDepartment(departmentName)).thenReturn(headOfDepartment);

        InputStream inputStream = new ByteArrayInputStream(command.getBytes());
        Scanner scanner = new Scanner(inputStream);
        System.setIn(inputStream);
        System.setIn(new ByteArrayInputStream("\n".getBytes()));
        application.processCommand(command);

        String expectedOutput = "Head of Computer Science department is John Doe\n";
        assertEquals(expectedOutput, outputStream.toString());

        verify(universityService, times(1)).getHeadOfDepartment(departmentName);
    }
}
