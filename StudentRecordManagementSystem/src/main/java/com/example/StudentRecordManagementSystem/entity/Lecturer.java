package com.example.StudentRecordManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name =" Lectures")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastNmame", nullable = false)
    private String lastName;

    @Column(name = "homeAddress")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "emailAddress")
    private String email;

    @Column(name = "telephoneNo")
    private String telephoneNo;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
