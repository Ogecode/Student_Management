package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.payload.StudentDto;
import com.example.StudentRecordManagementSystem.repository.StudentRepository;
import com.example.StudentRecordManagementSystem.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        //convert dto to entity
       /** Student student = new Student();
        student.setHomeAddress(studentDto.getHomeAddress());
        student.setEmailAddress(studentDto.getEmailAddress());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        student.setSession(studentDto.getSession());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());*/

        Student student = mapToEntity(studentDto);
        Student newStudent = studentRepository.save(student);

        // convert entity to DTO
        StudentDto studentResponse = mapToDto(newStudent);

       /** StudentDto studentResponse = new StudentDto();
        studentResponse.setId(newStudent.getId());
        studentResponse.setSession(newStudent.getSession());
        studentResponse.setHomeAddress(newStudent.getHomeAddress());
        studentResponse.setEmailAddress(newStudent.getEmailAddress());
        studentResponse.setFirstName(newStudent.getFirstName());
        studentResponse.setLastName(newStudent.getLastName());
        studentResponse.setGender(newStudent.getGender());
        studentResponse.setDob(newStudent.getDob());*/

        return studentResponse;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        //converting student entity to list of student dto
       return  students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());

    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
        return mapToDto(student);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, long id) {
        //grt post by Id from the database
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
       student.setEmailAddress(studentDto.getEmailAddress());
       student.setHomeAddress(studentDto.getHomeAddress());
       student.setLastName(studentDto.getLastName());
       student.setFirstName(studentDto.getFirstName());
       student.setSession(studentDto.getSession());
       student.setDob(student.getDob());
       student.setGender(studentDto.getGender());
       Student updatedStudent = studentRepository.save(student);

        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
        studentRepository.delete(student);
    }


    // converting Entity to DTO
    private StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setDob(student.getDob());
        studentDto.setSession(student.getSession());
        studentDto.setEmailAddress(studentDto.getEmailAddress());
        studentDto.setHomeAddress(student.getHomeAddress());
        studentDto.setGender(student.getGender());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(studentDto.getLastName());
        studentDto.setId(student.getId());

        return studentDto;
    }

    //converting DTO to entity
    private  Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setHomeAddress(studentDto.getHomeAddress());
        student.setEmailAddress(studentDto.getEmailAddress());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        student.setSession(studentDto.getSession());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        return student;
    }
}
