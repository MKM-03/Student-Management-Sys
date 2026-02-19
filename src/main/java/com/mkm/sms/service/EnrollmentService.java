package com.mkm.sms.service;

import com.mkm.sms.entity.Course;
import com.mkm.sms.entity.Enrollment;
import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.EnrollmentStatus;

public class EnrollmentService {


    public void enroll(Student student, Course course, SemesterRecord record) {

        if (!record.isDraft()) {
            throw new IllegalStateException("Semester is already closed");
        }

        int newTotalCredits =
                record.getTotalCredits() + course.getCreditsHours();

        if (newTotalCredits > 24) {
            throw new IllegalArgumentException("Maximum semester credit hours is 24");
        }
        Enrollment enrollment = new Enrollment(course);
        record.addEnrollment(enrollment);
    }

    public void validateEnrollment(SemesterRecord record) {

        if (record.isLocked()) {
            throw new IllegalStateException("Error! The semester is locked, please ensure ");
        }

        if (record.getTotalCredits() < 12) {
            throw new IllegalStateException(
                    "Error finalizing the schedule, number of credit hours cannot be below 12h");
        }
        record.confirm();
        record.lock();
    }


}
