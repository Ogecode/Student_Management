package com.example.StudentRecordManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "School", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;
    private String name;

    private String address;

    private  String telephoneNo;

   @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> department;
}
