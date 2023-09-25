package com.example.StudentRecordManagementSystem.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SchoolDto {

    private Long schoolId;
    private String name;

    private String address;

    private  String telephoneNo;
}
