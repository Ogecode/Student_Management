package com.example.StudentRecordManagementSystem.controller;

import com.example.StudentRecordManagementSystem.payload.SchoolDto;
import com.example.StudentRecordManagementSystem.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody SchoolDto schoolDto){
        return  new ResponseEntity<>(schoolService.createSchool(schoolDto), HttpStatus.CREATED);
    }

    @GetMapping
    List<SchoolDto> getAllSchool (){
        return schoolService.getAllSchools();
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<SchoolDto> getSchoolById (@PathVariable(value = "schoolId") Long schoolId){
        return  ResponseEntity.ok(schoolService.getSchoolById(schoolId));
    }

    @PutMapping("/{schoolId}")
    public ResponseEntity<SchoolDto> updateSchoolById (@RequestBody SchoolDto schoolDto, @PathVariable(value = "schoolId") long schoolId){
        SchoolDto schoolResponse = schoolService.updateSchoolById(schoolDto, schoolId);

        return new ResponseEntity<>(schoolResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{schoolId}")
    public  ResponseEntity<String> deleteSchoolById (@PathVariable(value = "schoolId") long schoolId){
        schoolService.deleteSchoolById(schoolId);
        return new ResponseEntity<>("School has been successfully deleted", HttpStatus.OK);
    }
}
