package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.Department;
import com.example.StudentRecordManagementSystem.entity.School;
import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.exception.StudentRecordAPIException;
import com.example.StudentRecordManagementSystem.payload.DepartmentDto;
import com.example.StudentRecordManagementSystem.repository.DepartmentRepository;
import com.example.StudentRecordManagementSystem.repository.SchoolRepository;
import com.example.StudentRecordManagementSystem.repository.StudentRepository;
import com.example.StudentRecordManagementSystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final  SchoolRepository schoolRepository;

    private final  StudentRepository studentRepository;
    private  final ModelMapper mapper;

   /** public DepartmentServiceImpl(DepartmentRepository departmentRepository, SchoolRepository schoolRepository, StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
    }*/

   @Override
   public DepartmentDto createDepartment(long schoolId, long id, DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
       School school = schoolRepository.findById(schoolId).orElseThrow(()-> new ResourceNotFoundException("School", "id", schoolId));
       Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));

       department.setSchool(school);
       department.setStudent(student);
        Department newDepartment = departmentRepository.save(department);
        return mapToDto(department);

    }

   /* @Override
    public List<DepartmentDto> getDepartmentsBySchoolId(long schoolId) {

       List<Department> departments = departmentRepository.findDepartmentBySchoolId(schoolId);

        return departments.stream().map(department -> mapToDto(department)).collect(Collectors.toList());
       return null;
    }*/

    @Override
    public DepartmentDto getDepartmentById(Long departmentId, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(()-> new ResourceNotFoundException("School", "id", schoolId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department", "id",departmentId));
        if(!department.getSchool().getSchoolId().equals(school.getSchoolId())){
            throw  new StudentRecordAPIException(HttpStatus.BAD_REQUEST,  "Department does not belong to school");
        }
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long schoolId, Long departmentId,Long studentId) {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(()-> new ResourceNotFoundException("School", "id", schoolId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department", "id",departmentId));
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student", "id", studentId));

        department.setSchool(school);
        department.setStudent(student);
        department.setDepartmentName(department.getDepartmentName());
        department.setDepartmentId(departmentDto.getDepartmentId());
        Department updatedDepartment = departmentRepository.save(department);
        return mapToDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment( Long departmentId) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
    departmentRepository.delete(department);
    }

    private DepartmentDto mapToDto (Department department){
        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        /*
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(department.getDepartmentId());
        departmentDto.setDepartmentName(department.getDepartmentName());*/

        return departmentDto;
    }

    private Department mapToEntity (DepartmentDto departmentDto){
        Department department = mapper.map(departmentDto, Department.class);
        /*
        Department department = new Department();
        department.setDepartmentId(departmentDto.getDepartmentId());
        department.setDepartmentName(departmentDto.getDepartmentName());*/
        return department;
    }
}
