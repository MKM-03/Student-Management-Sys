package com.mkm.sms.entity;

import jakarta.persistence.*;

import java.util.List;



@Entity
@Table(name = "transcripts")
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToMany
    @JoinColumn(name = "transcript_id")
    private List<SemesterRecord> records;


    protected Transcript() {}

    public Transcript(Student student, List<SemesterRecord> records) {
        this.student = student;
        this.records = records;
    }

    public double calculateOverallGpa() {
        double points = 0;
        int totalCredits = 0;

        for (SemesterRecord record : records) {
            double gpa = record.calculateSemesterGpa();
            int hours = record.getTotalCredits();
            points += gpa * hours;
            totalCredits += hours;
        }
        return totalCredits == 0 ? 0.0 : points / totalCredits;
    }

    @Override
    public String toString() {
        return "Transcript{ " +
                "student= " + (student != null ? student.getName() : "N/A") +
                " | totalSemesters= " + records.size() +
                " | overallGpa= " + String.format("%.2f", calculateOverallGpa()) + "}";
    }
}
