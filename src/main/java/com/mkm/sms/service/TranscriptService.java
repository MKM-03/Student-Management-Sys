package com.mkm.sms.service;

import com.mkm.sms.entity.SemesterRecord;
import com.mkm.sms.entity.Student;
import com.mkm.sms.entity.Transcript;
import com.mkm.sms.repository.EnrollmentRepository;
import com.mkm.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
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
        Student student = stdRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student was not found by ID: " + studentId));

        List<SemesterRecord> records = enrollRepository.findByStudentStudentID(studentId);
        if (records.isEmpty()) {
            throw new IllegalStateException("No semester records were found for the student by ID: " + studentId);
        }

        Transcript transcript = new Transcript(student, records);
        double overallGpa = transcript.calculateOverallGpa();
        student.setGpa(overallGpa);
        stdRepository.save(student);

        return transcript;
    }

    public double getStudentOverallGpa(String studentId) {
        return generateTranscript(studentId).calculateOverallGpa();
    }
}
