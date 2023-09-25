package com.example.StudentRecordManagementSystem.controller;

import com.example.StudentRecordManagementSystem.payload.DepartmentDto;
import com.example.StudentRecordManagementSystem.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

   private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/school/{schoolId}/student/{id}/department")
    public ResponseEntity<DepartmentDto> createDepartment (@PathVariable(value = "schoolId") long schoolId,
                                                           @PathVariable(value = "id") long id,
                                                           @RequestBody DepartmentDto departmentDto){
        return  new ResponseEntity<>(departmentService.createDepartment(schoolId,id, departmentDto), HttpStatus.CREATED);
    }

   /* @GetMapping("/school/{schoolId}/department")
    List<DepartmentDto> getDepartmentBySchoolId (@PathVariable(value = "schoolId") Long schoolId){
        return departmentService.getDepartmentsBySchoolId(schoolId);
    }*/

    @GetMapping("/school/{schoolId}/department/{departmentId}/department")
    public ResponseEntity<DepartmentDto> getDepartmentById (@PathVariable (value = "departmentId") long departmentId,
                                                            @PathVariable (value = "schoolId")long schoolId){
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId,schoolId));
    }

    @PutMapping("/school/{schoolId}/student/{studentId}/department/{departmentId}/department")
    public ResponseEntity<DepartmentDto> updateDepartment (@PathVariable(value = "schoolId") long schoolId,
                                                           @PathVariable(value = "studentId") long studentId,
                                                           @PathVariable(value = "departmentId") long departmentId,
                                                           @RequestBody DepartmentDto departmentDto){
       DepartmentDto departmentResponse = departmentService.updateDepartment(departmentDto,schoolId,departmentId,studentId);
        return new ResponseEntity(departmentResponse,HttpStatus.OK);
    }

    @DeleteMapping("{departmentId}/department")
    public ResponseEntity<String> deleteDepartment(@PathVariable(value = "departmentId") long departmentId){
        departmentService.deleteDepartment(departmentId);

        return  new ResponseEntity<>("Department has been deleted",HttpStatus.OK);
    }
}
