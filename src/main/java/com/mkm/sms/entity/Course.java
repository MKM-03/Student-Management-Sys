package com.mkm.sms.entity;
import com.mkm.sms.enums.CourseStatus;
import com.mkm.sms.enums.Department;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(unique = true, nullable = false)
    private String courseId;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String title;

    private String description;

    private int creditsHours;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;


    protected Course() {}

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

    @Override
    public String toString() {
        return "Course{ " +
                "ID: " + courseId +
                " | Code: " + courseCode +
                " | Title: " + title +
                " | Department: " + department +
                " | Hours: " + creditsHours + " }";
    }
}
