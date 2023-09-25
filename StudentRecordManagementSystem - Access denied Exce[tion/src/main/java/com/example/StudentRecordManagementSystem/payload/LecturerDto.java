package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class LecturerDto {

    private long staffId;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private String email;

    private String telephoneNo;
}
