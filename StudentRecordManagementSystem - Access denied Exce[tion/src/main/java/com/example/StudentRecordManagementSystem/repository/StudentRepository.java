package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
