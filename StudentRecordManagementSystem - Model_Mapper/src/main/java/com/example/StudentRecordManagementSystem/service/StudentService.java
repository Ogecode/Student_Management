package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.payload.StudentDto;
import com.example.StudentRecordManagementSystem.payload.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

   StudentResponse getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto getStudentById (Long id);

    StudentDto updateStudent(StudentDto studentDto, long id);

    void deleteStudentById(long id);
}
