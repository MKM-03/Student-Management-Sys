package com.mkm.sms.service;

import com.mkm.sms.entity.Enrollment;
import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.EnrollmentStatus;
import com.mkm.sms.model.Semester;

import java.util.ArrayList;
import java.util.List;

public class SemesterRecord {
    private final Student student;
    private final Semester semester;
    private EnrollmentStatus status = EnrollmentStatus.DRAFT;
    private final List<Enrollment> enrollments = new ArrayList<>();


    public SemesterRecord(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public void removeEnrollment(Enrollment e) {
        enrollments.remove(e);
    }

    public int getTotalCredits() {
        int total = 0;
        for (Enrollment e : enrollments) {
            total += e.getCreditHours();
        }
        return total;
    }

    public double calculateSemesterGpa() {
        double points = 0;
        int credits = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                points += e.getGrade() * e.getCreditHours();
                credits += e.getCreditHours();
            }
        }
        return credits == 0 ? 0.0 : points / credits;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public boolean isLocked() {
        return status == EnrollmentStatus.LOCKED;
    }

    public boolean isDraft() {
        return status == EnrollmentStatus.DRAFT;
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
    }

    public void lock() {
        this.status = EnrollmentStatus.LOCKED;
    }
}
