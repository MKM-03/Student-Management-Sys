package com.mkm.sms.service;

import com.mkm.sms.entity.Student;
import com.mkm.sms.entity.Transcript;
import com.mkm.sms.repository.EnrollmentRepository;
import com.mkm.sms.repository.StudentRepository;

import java.util.List;

public class TranscriptService {
    private final StudentRepository stdRepository;
    private final EnrollmentRepository enrollRepository;

    public TranscriptService(
            StudentRepository stdRepository,
            EnrollmentRepository enrollRepository) {

        this.stdRepository = stdRepository;
        this.enrollRepository = enrollRepository;
    }

    public Transcript generateTranscript(String studentId) {
        Student student = stdRepository.findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found by ID: " + studentId);
        }

        List<SemesterRecord> records = enrollRepository.findByStudentId(studentId);
        if (records.isEmpty()) {
            throw new IllegalStateException("No semester records were found for the student by ID: " + studentId);
        }

        Transcript transcript = new Transcript(student, records);
        double overallGpa = transcript.calculateOverallGpa();
        student.setGpa(overallGpa);
        stdRepository.saveStudent(student);

        return transcript;
    }

    public double getStudentOverallGpa(String studentId) {
        return generateTranscript(studentId).calculateOverallGpa();
    }
}
