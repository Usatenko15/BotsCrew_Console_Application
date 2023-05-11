package com.example.botscrew_console_application.repository;

import com.example.botscrew_console_application.BotsCrewConsoleApplication;
import com.example.botscrew_console_application.model.Degree;
import com.example.botscrew_console_application.model.Department;
import com.example.botscrew_console_application.model.Lector;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class LectorRepositoryTests {

    @MockBean
    private BotsCrewConsoleApplication botsCrewConsoleApplication;

    @Autowired
    private LectorRepository lectorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testCountByDepartmentsInAndDegree() {
        // Create test data
        Department department1 = new Department("Department 1");
        Department department2 = new Department("Department 2");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);
        Lector lector3 = new Lector("Michael Johnson", Degree.ASSOCIATE_PROFESSOR, 4000);

        lector1.setDepartments(Arrays.asList(department1, department2));
        lector2.setDepartments(Arrays.asList(department1));
        lector3.setDepartments(Arrays.asList(department2));

        entityManager.persist(department1);
        entityManager.persist(department2);
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.persist(lector3);
        entityManager.flush();

        // Perform the query
        List<Department> departments = Arrays.asList(department1);
        int count = lectorRepository.countByDepartmentsInAndDegree(departments, Degree.PROFESSOR);

        // Verify the result
        Assertions.assertEquals(1, count);
    }

    @Test
    public void testCalculateAverageSalaryByDepartment() {
        // Create test data
        Department department = new Department("Department 1");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);
        Lector lector3 = new Lector("Michael Johnson", Degree.ASSOCIATE_PROFESSOR, 4000);

        lector1.setDepartments(Arrays.asList(department));
        lector2.setDepartments(Arrays.asList(department));
        lector3.setDepartments(Arrays.asList(department));

        entityManager.persist(department);
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.persist(lector3);
        entityManager.flush();

        // Perform the query
        double averageSalary = lectorRepository.calculateAverageSalaryByDepartment(department);

        // Verify the result
        Assertions.assertEquals(4000, averageSalary);
    }

    @Test
    public void testCountByDepartments() {
        // Create test data
        Department department = new Department("Department 1");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);

        lector1.setDepartments(Arrays.asList(department));
        lector2.setDepartments(Arrays.asList(department));

        entityManager.persist(department);
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.flush();

        // Perform the query
        long count = lectorRepository.countByDepartments(department);

        // Verify the result
        Assertions.assertEquals(2, count);
    }
    @Test
    public void testCountByDepartmentsInAndDegree_NotFound() {
        // Create test data
        Department department1 = new Department("Department 1");
        Department department2 = new Department("Department 2");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);

        lector1.setDepartments(Arrays.asList(department1));
        lector2.setDepartments(Arrays.asList(department2));

        entityManager.persist(department1);
        entityManager.persist(department2);
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.flush();

        // Perform the query
        List<Department> departments = Arrays.asList(department1, department2);
        int count = lectorRepository.countByDepartmentsInAndDegree(departments, Degree.ASSOCIATE_PROFESSOR);

        // Verify the result
        Assertions.assertEquals(0, count);
    }

    @Test
    public void testCalculateAverageSalaryByDepartment_NotFound() {
        // Create test data
        Department department = new Department("Department 1");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);

        lector1.setDepartments(Arrays.asList(department));
        lector2.setDepartments(Arrays.asList(department));

        entityManager.persist(department);
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.flush();

        // Perform the query
        double averageSalary = lectorRepository.calculateAverageSalaryByDepartment(null);

        // Verify the result
        Assertions.assertEquals(0, averageSalary);
    }

    @Test
    public void testCountByDepartments_NoDepartments() {
        // Create test data
        Department department = new Department("Department 1");

        Lector lector1 = new Lector("John Doe", Degree.PROFESSOR, 5000);
        Lector lector2 = new Lector("Jane Smith", Degree.ASSISTANT, 3000);

        lector1.setDepartments(List.of(department));
        lector2.setDepartments(List.of(department));

        entityManager.persist(department);
        entityManager.flush();
        entityManager.persist(lector1);
        entityManager.persist(lector2);
        entityManager.flush();

        // Perform the query
        long count = lectorRepository.countByDepartments(null);

        // Verify the result
        Assertions.assertEquals(0, count);
    }
}
