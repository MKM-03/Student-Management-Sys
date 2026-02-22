package com.mkm.sms.entity;

import java.util.UUID;

public class Enrollment {
    private final String enrollmentId;
    private final Course course;
    private Double grade;

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
