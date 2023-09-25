package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CourseDto {

    private Long courseId;

    @NotEmpty
    @Size(min = 4, message = "course Tile should contain at least 4 characters")
    private String courseTitle;

    @NotEmpty
    private String courseCode;

    @Min(value = 2, message = "course unit must contain 2 character")
    private int courseUnit;

    @PastOrPresent
    private LocalDate registeredAt;
}
