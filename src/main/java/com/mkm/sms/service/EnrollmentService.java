package com.mkm.sms.service;

import com.mkm.sms.entity.Course;
import com.mkm.sms.entity.Enrollment;
import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.EnrollmentStatus;

public class EnrollmentService {


    public void enroll(Student student, Course course, SemesterRecord record) {

        if (!record.isDraft()) {
            throw new IllegalStateException("Cannot enroll: semester is already closed");
        }


        if (record.isAlreadyEnrolled(course.getCourseCode())) {
            throw new IllegalArgumentException(
                    "Student is already enrolled in course: " + course.getCourseCode());
        }

        int newTotalCredits = record.getTotalCredits() + course.getCreditsHours();

        if (newTotalCredits > 24) {
            throw new IllegalArgumentException(
                    "Exceeds maximum semester credit hours (24). Current: "
                            + record.getTotalCredits() + ", attempted to add: " + course.getCreditsHours());
        }

        Enrollment enrollment = new Enrollment(course);
        record.addEnrollment(enrollment);
    }

    public void validateEnrollment(SemesterRecord record) {

        if (record.isLocked()) {
            throw new IllegalStateException("Semester is already locked");
        }

        if (record.getTotalCredits() < 12) {
            throw new IllegalStateException(
                    "Cannot finalize: minimum 12 credit hours required. Currently at: "
                            + record.getTotalCredits() + "h");
        }

        record.confirm();
        record.lock();
    }

    public void assignGrade(SemesterRecord record, String courseCode, double grade) {
        for (Enrollment e : record.getEnrollments()) {
            if (e.getCourse().getCourseCode().equals(courseCode)) {
                e.setGrade(grade);
                return;
            }
        }
        throw new IllegalArgumentException("No enrollment found for course: " + courseCode);
    }

}
