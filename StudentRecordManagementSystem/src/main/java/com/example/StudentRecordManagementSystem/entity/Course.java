package com.example.StudentRecordManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseTitle;

    private String courseCode;

    private int courseUnit;

    private LocalDate registeredAt;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "student_id", nullable = false)
 private Student student;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id", nullable = false)
private  Department department;

}
