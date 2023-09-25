package com.example.StudentRecordManagementSystem.service.impl;

import com.example.StudentRecordManagementSystem.entity.Course;
import com.example.StudentRecordManagementSystem.entity.Department;
import com.example.StudentRecordManagementSystem.entity.Lecturer;
import com.example.StudentRecordManagementSystem.entity.Student;
import com.example.StudentRecordManagementSystem.exception.ResourceNotFoundException;
import com.example.StudentRecordManagementSystem.exception.StudentRecordAPIException;
import com.example.StudentRecordManagementSystem.payload.CourseDto;
import com.example.StudentRecordManagementSystem.repository.CourseRepository;
import com.example.StudentRecordManagementSystem.repository.DepartmentRepository;
import com.example.StudentRecordManagementSystem.repository.StudentRepository;
import com.example.StudentRecordManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;
    @Override
    public CourseDto createCourse( long studentId, long departmentId,  CourseDto courseDto) {
        Course course = matToEntity(courseDto);
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department", "id", departmentId));
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","id", studentId));
        course.setStudent(student);
        course.setDepartment(department);
        Course newCourse = courseRepository.save(course);

        return mapToDTO(course);
    }

  /* @Override
   public List<CourseDto> getCoursesByStudentId(long studentId) {
        List<Course> courses = courseRepository.findCourseByStudentId(studentId);
       return courses.stream().map(course -> mapToDTO (course)).collect(Collectors.toList());}

   /* @Override
    public List<CourseDto> getCoursesByDepartmentId(long departmentId){
        List<Course> courses = courseRepository.findCourseByDepartmentId(departmentId);
        return courses.stream().map(course -> mapToDTO(course)).collect(Collectors.toList());
    }*/



    @Override
    public CourseDto updateCourse(CourseDto courseDto, Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student", "id",studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course", "id", courseId));
        if(!course.getStudent().getId().equals(student.getId())){
            throw  new StudentRecordAPIException(HttpStatus.BAD_REQUEST, "Course doest not belong to student");
        }
        course.setCourseCode(courseDto.getCourseCode());
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseUnit(courseDto.getCourseUnit());
        course.setRegisteredAt(courseDto.getRegisteredAt());
        course.setCourseId(courseDto.getCourseId());
        Course updatedCourse = courseRepository.save(course);

        return mapToDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(@PathVariable(value = "courseId") long courseId) {
            // retrieve using staff id
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course", "id", courseId));
            courseRepository.delete(course);

    }

    @Override
    public CourseDto getCourseById(Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException
                ("Course", "id", courseId));
        return mapToDTO(course);
    }


    private  CourseDto mapToDTO (Course course){
        CourseDto courseDto = mapper.map(course, CourseDto.class);
        /*
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseTitle(course.getCourseTitle());
        courseDto.setCourseUnit(course.getCourseUnit());
        courseDto.setCourseCode(course.getCourseCode());
       /********** final String DATE_FORMAT_JAVA = "yyyy-MM-dd";
        final DateFormat df = new SimpleDateFormat(DATE_FORMAT_JAVA);
        String callTime = df.format(course.getRegisteredAt());********
        courseDto.setRegisteredAt(course.getRegisteredAt());*/

        return courseDto;
    }

    private Course matToEntity (CourseDto courseDto){
        Course course = mapper.map(courseDto, Course.class);
        /*
        Course course = new Course();
        course.setCourseCode(courseDto.getCourseCode());
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseUnit(courseDto.getCourseUnit());
        course.setCourseId(courseDto.getCourseId());

        /*final String DATE_FORMAT_JAVA = "yyyy-MM-dd";
        final DateFormat df = new SimpleDateFormat(DATE_FORMAT_JAVA);
        String callTime = df.format(courseDto.getRegisteredAt());********
        course.setRegisteredAt(courseDto.getRegisteredAt());*/
        return course;
    }
}
