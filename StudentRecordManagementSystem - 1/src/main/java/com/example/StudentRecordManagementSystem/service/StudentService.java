package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.payload.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    StudentDto getStudentById (Long id);

    StudentDto updateStudent(StudentDto studentDto, long id);

    void deleteStudentById(long id);
}
