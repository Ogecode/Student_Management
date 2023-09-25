package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.payload.SchoolDto;

import java.util.List;

public interface SchoolService {

    SchoolDto createSchool (SchoolDto schoolDto);

    List<SchoolDto> getAllSchools();

    SchoolDto getSchoolById(Long schoolId);

    SchoolDto updateSchoolById (SchoolDto schoolDto, Long schoolId);

    void deleteSchoolById (Long schoolId);
}
