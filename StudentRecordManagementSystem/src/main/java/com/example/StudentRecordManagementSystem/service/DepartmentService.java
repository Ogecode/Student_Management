package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.payload.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(long schoolId, long id, DepartmentDto departmentDto);

    //List<DepartmentDto> getDepartmentsBySchoolId (long schoolId);

    DepartmentDto getDepartmentById (Long departmentId, Long schoolId);


    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long schoolId, Long departmentId, Long studentId);

    void deleteDepartment (Long departmentId);

    interface UserService {
    }
}
