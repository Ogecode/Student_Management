package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CourseDto {

    private Long courseId;

    private String courseTitle;

    private String courseCode;

    private int courseUnit;

    private LocalDate registeredAt;
}
