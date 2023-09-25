package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    List<Lecturer> findLecturerByStudentId (long studentId);
}
