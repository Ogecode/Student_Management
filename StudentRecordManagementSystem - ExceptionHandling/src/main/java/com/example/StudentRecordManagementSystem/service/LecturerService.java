package com.example.StudentRecordManagementSystem.service;

import com.example.StudentRecordManagementSystem.payload.LecturerDto;

import java.util.List;

public interface LecturerService {
    LecturerDto createLecturer (long studentId, LecturerDto lecturerDto);

    List<LecturerDto> getLecturersByStudentId (long studentId);

    LecturerDto getLecturerById (Long staffId, Long studentId);

    LecturerDto updateLecturerById(LecturerDto lecturerDto,Long staffId, Long studentId);

    void deleteLecturerById (Long staffId, Long studentId);

}
