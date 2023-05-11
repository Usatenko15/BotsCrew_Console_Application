package com.example.botscrew_console_application.service;

import com.example.botscrew_console_application.model.Degree;
import com.example.botscrew_console_application.model.Department;
import com.example.botscrew_console_application.model.Lector;
import com.example.botscrew_console_application.repository.DepartmentRepository;
import com.example.botscrew_console_application.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UniversityServiceTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LectorRepository lectorRepository;

    private UniversityService universityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        universityService = new UniversityService(departmentRepository, lectorRepository);
    }

    @Test
    void getHeadOfDepartment_ShouldReturnHeadOfDepartmentName() {
        // Arrange
        String departmentName = "IT";
        Department department = new Department();
        department.setName(departmentName);
        department.setHeadOfDepartment(new Lector("John Doe", Degree.ASSISTANT, 3000));
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        // Act
        String headOfDepartment = universityService.getHeadOfDepartment(departmentName);

        // Assert
        assertEquals("John Doe", headOfDepartment);
    }

    @Test
    void findByName_ShouldReturnDepartment() {
        // Arrange
        String departmentName = "IT";
        Department department = new Department();
        department.setName(departmentName);
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        // Act
        Department result = universityService.findByName(departmentName);

        // Assert
        assertEquals(department, result);
    }

    @Test
    void getLectorsCountByDegree_ShouldReturnLectorsCount() {
        // Arrange
        Department department = new Department();
        Lector lector1 = new Lector("John Doe", Degree.ASSISTANT, 3000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);
        department.setLectors(List.of(lector1, lector2));
        Degree degree = Degree.ASSISTANT;
        when(lectorRepository.countByDepartmentsInAndDegree(List.of(department), degree)).thenReturn(2);

        // Act
        int lectorsCount = universityService.getLectorsCountByDegree(department, degree);

        // Assert
        assertEquals(2, lectorsCount);
    }

    @Test
    void getAverageSalaryByDepartment_ShouldReturnAverageSalary() {
        // Arrange
        String departmentName = "IT";
        Department department = new Department();
        department.setName(departmentName);
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));
        when(lectorRepository.calculateAverageSalaryByDepartment(department)).thenReturn(5000.0);

        // Act
        double averageSalary = universityService.getAverageSalaryByDepartment(departmentName);

        // Assert
        assertEquals(5000.0, averageSalary);
    }

    @Test
    void getEmployeeCountByDepartment_ShouldReturnEmployeeCount() {
        // Arrange
        String departmentName = "IT";
        Department department = new Department();
        department.setName(departmentName);
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));
        when(lectorRepository.countByDepartments(department)).thenReturn(10L);

        // Act
        long employeeCount = universityService.getEmployeeCountByDepartment(departmentName);

        // Assert
        assertEquals(10L, employeeCount);
    }

    @Test
    void getHeadOfDepartment_WhenDepartmentNotFound_ShouldThrowException() {
        // Arrange
        String departmentName = "Nonexistent Department";
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> universityService.getHeadOfDepartment(departmentName));
    }

    @Test
    void findByName_WhenDepartmentNotFound_ShouldThrowException() {
        // Arrange
        String departmentName = "Nonexistent Department";
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> universityService.findByName(departmentName));
    }

    @Test
    void getLectorsCountByDegree_WhenDepartmentHasNoLectors_ShouldReturnZero() {
        // Arrange
        Department department = new Department();
        Degree degree = Degree.ASSISTANT;
        when(lectorRepository.countByDepartmentsInAndDegree(List.of(department), degree)).thenReturn(0);

        // Act
        int lectorsCount = universityService.getLectorsCountByDegree(department, degree);

        // Assert
        assertEquals(0, lectorsCount);
    }

    @Test
    void getAverageSalaryByDepartment_WhenDepartmentNotFound_ShouldThrowException() {
        // Arrange
        String departmentName = "Nonexistent Department";
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> universityService.getAverageSalaryByDepartment(departmentName));
    }

    @Test
    void getEmployeeCountByDepartment_WhenDepartmentNotFound_ShouldThrowException() {
        // Arrange
        String departmentName = "Nonexistent Department";
        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> universityService.getEmployeeCountByDepartment(departmentName));
    }
}
