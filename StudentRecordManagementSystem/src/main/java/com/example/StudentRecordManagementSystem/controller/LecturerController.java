package com.example.StudentRecordManagementSystem.controller;

import com.example.StudentRecordManagementSystem.payload.LecturerDto;
import com.example.StudentRecordManagementSystem.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LecturerController {

    private LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @PostMapping("/students/{studentId}/lecturer")
    public ResponseEntity<LecturerDto> createLecturer (@PathVariable(value = "studentId") long studentId,
                                                       @RequestBody LecturerDto lecturerDto){
        return  new ResponseEntity<>(lecturerService.createLecturer(studentId,lecturerDto), HttpStatus.CREATED);

    }
    @GetMapping("/students/{studentId}/lecturer")
   public List<LecturerDto> getLecturersByStudentId (@PathVariable(value = "studentId") Long studentId){
        return lecturerService.getLecturersByStudentId(studentId);
    }


    @GetMapping("/students/{studentId}/lecturer/{staffId}")
    public  ResponseEntity<LecturerDto> getLecturerById (@PathVariable(value = "staffId") Long staffId,
                                                         @PathVariable(value = "studentId") Long studentId)
    {
       LecturerDto lecturerDto = lecturerService.getLecturerById(staffId,studentId);
       return new ResponseEntity<>(lecturerDto, HttpStatus.OK);
    }
    @PutMapping ("/students/{studentId}/lecturer/{lecturerId}")
   public ResponseEntity<LecturerDto> updateLecturerById (@PathVariable(value = "lecturerId") Long staffId,
                                                          @PathVariable(value = "studentId") Long studentId,
                                                          @RequestBody LecturerDto lecturerDto){
        LecturerDto updatedLecturer = lecturerService.updateLecturerById(lecturerDto, staffId, studentId);
        return  new ResponseEntity<>(updatedLecturer,HttpStatus.OK);
   }

   @DeleteMapping("/students/{studentId}/lecturer/{staffId}")
   public ResponseEntity<String> deleteLecturer (@PathVariable(value = "studentId") Long studentId,
                                                 @PathVariable(value = "staffId") Long staffId){
        lecturerService.deleteLecturerById(staffId,studentId);
        return new ResponseEntity<>("Lecturer account deleted successfully", HttpStatus.OK);

   }
}
