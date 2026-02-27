package com.mkm.sms.service;

import com.mkm.sms.entity.Course;
import com.mkm.sms.enums.Department;
import com.mkm.sms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CourseService {
    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(String courseCode,
                             String title,
                             String description,
                             int creditHours, Department department) {

        if (creditHours < 2 || creditHours > 8) {
            throw new IllegalArgumentException("Error adding course hours! Minimum hours allowed are 2");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Error! Course code cannot be empty");
        }
        if (courseRepository.existsByCourseCode(courseCode)) {
            throw new IllegalArgumentException(
                    "Error adding course! This course already exist by the code " + courseCode);
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Error adding course title! The title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Error adding description! The description cannot be empty");
        }
        if (department == null) {
            throw new IllegalArgumentException("Error adding department! All courses must belong to a department");
        }

        Course course = new Course(courseCode, title, description, creditHours, department);
        courseRepository.save(course);

        return course;
    }

    public Course findCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found by ID: " + courseId));
    }

    public void deleteCourse(String courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    public Course findCourseByCode(String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode);

        if (course == null) {
            throw new IllegalArgumentException("Course was not found by Code: " + courseCode);
        }

        return course;
    }

        public List<Course> findAllCourses() { return courseRepository.findAll(); }

    public List<Course> listByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Error! Department cannot be empty");
        }
        return courseRepository.findByDepartment(department);
    }
}
