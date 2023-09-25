package com.example.StudentRecordManagementSystem.payload;

import com.example.StudentRecordManagementSystem.entity.School;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class DepartmentDto {

    private Long departmentId;
    private String departmentName;


}
