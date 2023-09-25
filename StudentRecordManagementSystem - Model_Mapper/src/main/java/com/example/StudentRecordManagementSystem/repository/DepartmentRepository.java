package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

//List<Department> findDepartmentBySchoolId (long schoolId);
}
