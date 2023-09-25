package com.example.StudentRecordManagementSystem.controller;

import com.example.StudentRecordManagementSystem.payload.StudentDto;
import com.example.StudentRecordManagementSystem.payload.StudentResponse;
import com.example.StudentRecordManagementSystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentDto> createStudent (@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    //getall post api
    @GetMapping
    public StudentResponse getAllStudents(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy
    ){

        return studentService.getAllStudents(pageNo, pageSize, sortBy);
    }

    //getAll student by Id
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "id") long id){
       return ResponseEntity.ok(studentService.getStudentById(id));

    }

    //update Student By Id
    @PutMapping("/{id}")
    public  ResponseEntity<StudentDto> updateStudent (@RequestBody StudentDto studentDto,@PathVariable(name = "id") long id){
        StudentDto studentResponse = studentService.updateStudent(studentDto, id);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    //delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student profile has been deleted successfully", HttpStatus.OK);
    }

}
