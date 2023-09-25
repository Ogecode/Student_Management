package com.example.StudentRecordManagementSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    /*@Column(name = "course", nullable = false)
    private String course;*/
}
