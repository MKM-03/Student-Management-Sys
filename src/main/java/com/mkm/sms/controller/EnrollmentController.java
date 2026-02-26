package com.mkm.sms.controller;


import com.mkm.sms.entity.*;
import com.mkm.sms.repository.CourseRepository;
import com.mkm.sms.repository.EnrollmentRepository;
import com.mkm.sms.repository.StudentRepository;
import com.mkm.sms.service.EnrollmentService;
import com.mkm.sms.service.TranscriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final TranscriptService transcriptService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;


    public EnrollmentController(
            EnrollmentService enrollmentService,
            TranscriptService transcriptService,
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository) {

        this.enrollmentService = enrollmentService;
        this.transcriptService = transcriptService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    @PostMapping
    public ResponseEntity<SemesterRecord> enroll(@RequestParam String studentId,
                                                 @RequestParam String courseId,
                                                 @RequestParam String semesterId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student was not found by ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course was not found by ID: " + courseId));

        Semester semester = new Semester("semester", java.time.LocalDate.now(),
                java.time.LocalDate.now().plusMonths(5));

        SemesterRecord record = enrollmentRepository
                .findByStudentStudentIDAndSemesterId(studentId, semesterId);


        if (record == null) {
            record = new SemesterRecord(student, semester);
        }

        enrollmentService.enroll(student, course, record);
        enrollmentRepository.save(record);

        return ResponseEntity.status(HttpStatus.CREATED).body(record);
    }


    @PostMapping("/finalize")
    public ResponseEntity<String> finalize(@RequestParam String studentId,
                                           @RequestParam String semesterId) {

        SemesterRecord record = enrollmentRepository
                .findByStudentStudentIDAndSemesterId(studentId, semesterId);

        if (record == null) {
            throw new IllegalArgumentException("No Semester record found for student with ID: " + studentId);
        }

        enrollmentService.validateEnrollment(record);
        enrollmentRepository.save(record);

        return ResponseEntity.ok("Semester finalized successfully");
    }

    @PostMapping("/grade")
    public ResponseEntity<String> assignGrade(@RequestParam String studentId,
                                              @RequestParam String semesterId,
                                              @RequestParam String courseCode,
                                              @RequestParam double grade) {

        SemesterRecord record = enrollmentRepository
                .findByStudentStudentIDAndSemesterId(studentId, semesterId);

        if (record == null) {
            throw new IllegalArgumentException("No Semester record found for student with ID: " + studentId);
        }

        enrollmentService.assignGrade(record, courseCode, grade);
        enrollmentRepository.save(record);

        return ResponseEntity.ok("Grade assigned successfully");
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<SemesterRecord>> getStudentRecords(@PathVariable String studentId) {
        return ResponseEntity.ok(enrollmentRepository.findByStudentStudentID(studentId));
    }

    @GetMapping("/transcript/{studentId}")
    public ResponseEntity<Transcript> getTranscript(@PathVariable String studentId) {
        return ResponseEntity.ok(transcriptService.generateTranscript(studentId));
    }
}
