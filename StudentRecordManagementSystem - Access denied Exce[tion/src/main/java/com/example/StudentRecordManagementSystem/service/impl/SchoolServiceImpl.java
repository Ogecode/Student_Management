package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.School;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.payload.SchoolDto;
import com.example.StudentRecordManagementSystem.repository.SchoolRepository;
import com.example.StudentRecordManagementSystem.service.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;
    private ModelMapper mapper;

    public SchoolServiceImpl(SchoolRepository schoolRepository, ModelMapper mapper) {
        this.schoolRepository = schoolRepository;
        this.mapper = mapper;
    }

    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school= mapToEntity(schoolDto);
        School newSchool = schoolRepository.save(school);
        SchoolDto schoolResponse = mapToDto(newSchool);

        return schoolResponse;
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream().map(school -> mapToDto(school)).collect(Collectors.toList());
    }

    @Override
    public SchoolDto getSchoolById(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() ->new ResourceNotFoundException("School", "id", schoolId));
        return mapToDto(school);
    }

    @Override
    public SchoolDto updateSchoolById(SchoolDto schoolDto, Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
        school.setAddress(schoolDto.getAddress());
        school.setName(school.getName());
        school.setTelephoneNo(schoolDto.getTelephoneNo());
        school.setSchoolId(schoolDto.getSchoolId());

        return null;
    }

    @Override
    public void deleteSchoolById(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(()-> new ResourceNotFoundException("School", "id", schoolId));
        schoolRepository.delete(school);
    }

    private SchoolDto mapToDto (School school){
        SchoolDto schoolDto = mapper.map(school, SchoolDto.class);
        /*
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setSchoolId(school.getSchoolId());
        schoolDto.setAddress(school.getAddress());
        schoolDto.setName(school.getName());
        schoolDto.setTelephoneNo(school.getTelephoneNo());*/
        return schoolDto;
    }

    private School mapToEntity (SchoolDto schoolDto){
        School school = mapper.map(schoolDto, School.class);
        /*
        School school = new School();
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setTelephoneNo(schoolDto.getTelephoneNo());
        school.setSchoolId(schoolDto.getSchoolId());*/
        return school;
    }
}
