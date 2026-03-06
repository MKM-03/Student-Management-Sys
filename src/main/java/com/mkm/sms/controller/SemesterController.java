package com.mkm.sms.controller;


import com.mkm.sms.entity.Semester;
import com.mkm.sms.repository.SemesterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {

    private final SemesterRepository semesterRepository;


    public SemesterController(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {
        return ResponseEntity.ok(semesterRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable String id) {
        return ResponseEntity.ok(semesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Semester was not found by ID: " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSemester(@PathVariable String id) {
        semesterRepository.deleteById(id);
        return ResponseEntity.ok("Semester was deleted successfully");
    }
}
