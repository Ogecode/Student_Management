package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.Lecturer;
import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.exception.StudentRecordAPIException;
import com.example.StudentRecordManagementSystem.payload.LecturerDto;
import com.example.StudentRecordManagementSystem.repository.LecturerRepository;
import com.example.StudentRecordManagementSystem.repository.StudentRepository;
import com.example.StudentRecordManagementSystem.service.LecturerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {
    private ModelMapper mapper;

    public LecturerServiceImpl(LecturerRepository lecturerRepository, StudentRepository studentRepository, ModelMapper mapper) {

        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    private LecturerRepository  lecturerRepository;
    private StudentRepository studentRepository;


    @Override
    public LecturerDto createLecturer(long studentId, LecturerDto lecturerDto) {
        Lecturer lecturer = mapToEntity(lecturerDto);
        // retrieve student entity by ID
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student", "id",studentId));
        //set student to a lecturer entity
        lecturer.setStudent(student);
        // save lecturer entity to the db

        Lecturer newLecturer = lecturerRepository.save(lecturer);
        return mapToDto(lecturer);

    }

    @Override
    public List<LecturerDto> getLecturersByStudentId(long studentId) {
        // retrieve lecturers by studentId

        List<Lecturer> lecturers =lecturerRepository.findLecturerByStudentId(studentId);

        //convert list of lecturers to lecturer dto
        return lecturers.stream().map(lecturer -> mapToDto(lecturer)).collect(Collectors.toList());
    }

    @Override
    public LecturerDto getLecturerById(Long staffId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student", "id",studentId));
        // retrieve using staff id
        Lecturer lecturer = lecturerRepository.findById(staffId).orElseThrow(() -> new ResourceNotFoundException("Lecture", "id", staffId));

        if(!lecturer.getStudent().getId().equals(student.getId())){
            throw new StudentRecordAPIException(HttpStatus.BAD_REQUEST,"lecture does not belong to student");
        }
        return mapToDto(lecturer);
    }

    @Override
    public LecturerDto updateLecturerById(LecturerDto lecturerDto, Long staffId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student", "id",studentId));
        // retrieve using staff id
        Lecturer lecturer = lecturerRepository.findById(staffId).orElseThrow(() -> new ResourceNotFoundException("Lecture", "id", staffId));
        if(!lecturer.getStudent().getId().equals(student.getId())){
            throw  new StudentRecordAPIException(HttpStatus.BAD_REQUEST,"lecture does not belong to student" );
        }

        lecturer.setLastName(lecturerDto.getLastName());
        lecturer.setFirstName(lecturerDto.getFirstName());
        lecturer.setEmail(lecturerDto.getEmail());
        lecturer.setGender(lecturerDto.getGender());
        lecturer.setTelephoneNo(lecturerDto.getTelephoneNo());
        Lecturer updatedLecturer = lecturerRepository.save(lecturer);

        return mapToDto(updatedLecturer);
    }

    @Override
    public void deleteLecturerById(Long staffId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student", "id",studentId));
        // retrieve using staff id
        Lecturer lecturer = lecturerRepository.findById(staffId).orElseThrow(() -> new ResourceNotFoundException("Lecture", "id", staffId));

        if(!lecturer.getStudent().getId().equals(student.getId())){
            throw new StudentRecordAPIException(HttpStatus.BAD_REQUEST,"lecture does not belong to student");
        }
        lecturerRepository.delete(lecturer);
    }

    private LecturerDto mapToDto (Lecturer lecturer){
        LecturerDto lecturerDto = mapper.map(lecturer, LecturerDto.class);

        /*
        LecturerDto lecturerDto = new LecturerDto();
        lecturerDto.setAddress(lecturer.getAddress());
        lecturerDto.setEmail(lecturer.getEmail());
        lecturerDto.setGender(lecturer.getGender());
        lecturerDto.setStaffId(lecturer.getStaffId());
        lecturerDto.setLastName(lecturer.getLastName());
        lecturerDto.setFirstName(lecturer.getFirstName());
        lecturerDto.setTelephoneNo(lecturer.getTelephoneNo());*/
        return lecturerDto;
    }
    private  Lecturer mapToEntity (LecturerDto lecturerDto){
        Lecturer lecturer = mapper.map(lecturerDto, Lecturer.class);

        /*Lecturer lecturer = new Lecturer();
        lecturer.setAddress(lecturerDto.getAddress());
        lecturer.setEmail(lecturerDto.getEmail());
        lecturer.setFirstName(lecturerDto.getFirstName());
        lecturer.setLastName(lecturerDto.getLastName());
        lecturer.setStaffId(lecturerDto.getStaffId());
        lecturer.setTelephoneNo(lecturerDto.getTelephoneNo());
        lecturer.setGender(lecturerDto.getGender());*/
        return lecturer;
    }
}
