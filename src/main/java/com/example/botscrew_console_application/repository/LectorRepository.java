package com.example.botscrew_console_application.repository;

import com.example.botscrew_console_application.model.Degree;
import com.example.botscrew_console_application.model.Department;
import com.example.botscrew_console_application.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    int countByDepartmentsInAndDegree(List<Department> departments, Degree degree);
    @Query("SELECT AVG(l.salary) FROM Lector l JOIN l.departments d WHERE d = :department")
    double calculateAverageSalaryByDepartment(@Param("department") Department department);
    long countByDepartments(Department department);
}

