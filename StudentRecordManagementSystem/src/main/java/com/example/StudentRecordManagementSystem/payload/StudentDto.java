package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;

    private String dob;

    @NotEmpty
    @Size(min = 2, message = "name should contain at least 2 characters")
    private String firstName;

    @Email(message = "please enter a valid email address")
    private String emailAddress;

    @NotEmpty
    @Size(min = 2, message = "name should contain at least 2 characters")
    private String lastName;

    private String gender;
    @Size(min = 4, message = "Adrresshould contain at leasdt 3 chaarcters")
    private  String homeAddress;


    private String session;


}
