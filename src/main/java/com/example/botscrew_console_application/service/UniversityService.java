package com.example.botscrew_console_application.service;

import com.example.botscrew_console_application.model.Degree;
import com.example.botscrew_console_application.model.Department;
import com.example.botscrew_console_application.repository.DepartmentRepository;
import com.example.botscrew_console_application.repository.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public UniversityService(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    public String getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow();
        return department.getHeadOfDepartment().getName();
    }

    public Department findByName(String departmentName) {
        return departmentRepository.findByName(departmentName).orElseThrow();
    }

    public int getLectorsCountByDegree(Department department, Degree degree) {
        return lectorRepository.countByDepartmentsInAndDegree(List.of(department), degree);
    }

    public double getAverageSalaryByDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow();
        return lectorRepository.calculateAverageSalaryByDepartment(department);
    }

    public long getEmployeeCountByDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow();
        return lectorRepository.countByDepartments(department);
    }
}
