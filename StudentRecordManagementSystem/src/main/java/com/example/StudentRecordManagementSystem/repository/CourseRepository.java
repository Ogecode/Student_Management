package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
   // List<Course> findCourseById  (Long courseId);
   // List<Course> findCourseByStudentId(Long studentId);
   // List<Course> findCourseByDepartmentId (Long departmentId);
}
