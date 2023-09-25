package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.payload.CourseDto;

import java.util.List;

public interface CourseService {

   CourseDto createCourse(long studentId, long departmentId,  CourseDto courseDto);

  //List<CourseDto> getCoursesByStudentId (long studentId);

  //List<CourseDto> getCoursesByDepartmentId (long departmentId);

    CourseDto getCourseById(Long courseId );

    CourseDto updateCourse (CourseDto courseDto, Long studentId, Long courseId);

    void deleteCourse(long courseId);
}
