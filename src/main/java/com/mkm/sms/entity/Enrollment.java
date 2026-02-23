package com.mkm.sms.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Entity(name = "enrollments")
public class Enrollment {

    @Id
    @Column(unique = true, nullable = false)
    private String enrollmentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Double grade;


    protected Enrollment() {}

    public Enrollment(Course course) {
        this.enrollmentId = "ENR-" + UUID.randomUUID();
        this.course = course;
    }

    public String getEnrollmentId() { return enrollmentId; }

    public int getCreditHours() {
        return course.getCreditsHours();
    }

    public Course getCourse() { return course; }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{ " +
                "courseCode: " + course.getCourseCode() +
                " | grade=" + (grade == null ? "N/A" : grade) +
                " }";
    }
}
