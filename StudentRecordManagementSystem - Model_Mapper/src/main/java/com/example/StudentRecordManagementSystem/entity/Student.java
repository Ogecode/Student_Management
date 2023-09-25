package com.example.StudentRecordManagementSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

@Table(name = "Students", uniqueConstraints = {@UniqueConstraint(columnNames = {"emailAddress"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "homeAddress", nullable = false)
    private  String homeAddress;

    @Column(name = "session", nullable = false)
    private String session;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lecturer> lecturers = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <Course> courses = new HashSet<>();

  //  @OneToOne(mappedBy = "student")
  //  private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    /*@Column(name = "course", nullable = false)
    private String course;*/
}
