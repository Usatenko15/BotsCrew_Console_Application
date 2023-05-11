package com.example.botscrew_console_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    private double salary;

    public Lector(String name, Degree degree, double salary) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
    }

    @ManyToMany
    @JoinTable(
            name = "lector_department",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        departments.add(department);
        department.getLectors().add(this);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
        department.getLectors().remove(this);
    }
}
