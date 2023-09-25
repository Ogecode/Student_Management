package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.payload.StudentDto;
import com.example.StudentRecordManagementSystem.payload.StudentResponse;
import com.example.StudentRecordManagementSystem.repository.StudentRepository;
import com.example.StudentRecordManagementSystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private ModelMapper mapper;
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
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
    public StudentResponse getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

       Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> students = studentRepository.findAll(pageable);

        // get content from the list of  object(it returns the list of students)
        List<Student> listOfStudents = students.getContent();
        //converting student entity to list of student dto
       List<StudentDto>  content = listOfStudents.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setTotalElement(studentResponse.getTotalElement());
        studentResponse.setLast(students.isLast());

        return studentResponse;
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
        //introducing modelmapper
        StudentDto studentDto = mapper.map(student, StudentDto.class);

       /* StudentDto studentDto = new StudentDto();
        studentDto.setDob(student.getDob());
        studentDto.setSession(student.getSession());
        studentDto.setEmailAddress(student.getEmailAddress());
        studentDto.setHomeAddress(student.getHomeAddress());
        studentDto.setGender(student.getGender());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setId(student.getId());*/

        return studentDto;
    }

    //converting DTO to entity
    private  Student mapToEntity(StudentDto studentDto){
        Student student = mapper.map(studentDto, Student.class);
      /*  Student student = new Student();
        student.setHomeAddress(studentDto.getHomeAddress());
        student.setEmailAddress(studentDto.getEmailAddress());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        student.setSession(studentDto.getSession());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());*/
        return student;
    }
}
