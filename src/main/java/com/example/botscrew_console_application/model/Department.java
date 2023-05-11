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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "head_of_department_id")
    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments")
    private List<Lector> lectors = new ArrayList<>();
}
