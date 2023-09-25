package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;

    private String dob;

    private String firstName;
    private String emailAddress;

    private String lastName;

    private String gender;

    private  String homeAddress;


    private String session;


}
