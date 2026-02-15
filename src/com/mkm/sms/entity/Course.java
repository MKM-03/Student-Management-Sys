package com.mkm.sms.entity;
import com.mkm.sms.enums.CourseStatus;
import com.mkm.sms.enums.Department;

import java.util.UUID;

public class Course {
    private final String courseId;
    private final String courseCode;
    private String title;
    private String description;
    private int creditsHours;
    private Department department;
    private CourseStatus status;


    public Course(String courseCode,
                  String title,
                  String description,
                  int creditsHours,
                  Department department) {

        this.courseId = "CRS-" + UUID.randomUUID();
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.creditsHours = creditsHours;
        this.department = department;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCreditsHours() {
        return creditsHours;
    }

    public Department getDepartment() {
        return department;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreditsHours(int creditsHours) {
        this.creditsHours = creditsHours;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
