package com.example.StudentRecordManagementSystem.controller;

import com.example.StudentRecordManagementSystem.entity.Course;
import com.example.StudentRecordManagementSystem.payload.CourseDto;
import com.example.StudentRecordManagementSystem.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
       this.courseService = courseService;
   }
@PostMapping("/students/{studentId}/department/{departmentId}/courses")
    public ResponseEntity<CourseDto> createCourse(@PathVariable(value = "studentId") long  studentId,
                                                  @PathVariable (value = "departmentId")long departmentId,
                                                  @RequestBody CourseDto courseDto){
        return new ResponseEntity<>(courseService.createCourse(studentId, departmentId, courseDto), HttpStatus.CREATED);

    }

    @PutMapping("/students/{studentId}/course/{courseId}")
    public ResponseEntity<CourseDto> updateCourse (@RequestBody CourseDto courseDto,
                                                   @PathVariable(value = "studentId") Long studentId,
                                                   @PathVariable(value = "courseId") Long courseId){

       CourseDto updatedCourse = courseService.updateCourse(courseDto, studentId,courseId);

       return  new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }
    /*@GetMapping("/students/{studentId}/courses")
    public List<CourseDto> getCoursesByStudentId (@PathVariable (value = "studentId") Long studentId){
        return courseService.getCoursesByStudentId(studentId);

    }
     */

    /**@GetMapping("department/{departmentId}/courses")
    public List<CourseDto> getCoursesByDepartmentId (@PathVariable(value ="departmentId" ) Long departmentId){
        return courseService.getCoursesByDepartmentId(departmentId);

    }*/

    @DeleteMapping("/{courseId}")
    public  ResponseEntity<String> deleteCourse (@PathVariable(value = "courseId") long courseId){
      courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Course has been successfully deleted", HttpStatus.OK);
    }


    @GetMapping("/{courseId}" )
    public ResponseEntity<CourseDto> getCourseById (long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }
}
