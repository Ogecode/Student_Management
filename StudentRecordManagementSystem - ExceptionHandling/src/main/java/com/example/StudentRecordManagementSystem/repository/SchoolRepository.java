package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository  extends JpaRepository<School, Long> {
}
