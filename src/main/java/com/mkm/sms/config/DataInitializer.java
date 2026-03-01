package com.mkm.sms.config;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.service.CourseService;
import com.mkm.sms.service.StudentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DataInitializer implements ApplicationRunner {

    private final StudentService studentService;
    private final CourseService courseService;


    public DataInitializer(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Students
        studentService.enrollStudent(new Student("Alice Johnson", "alice@uni.edu", "555-1001", Department.INFORMATION_TECHNOLOGY, "Software Engineering"));
        studentService.enrollStudent(new Student("Bob Smith", "bob@uni.edu", "555-1002", Department.INFORMATION_TECHNOLOGY, "Data Science"));
        studentService.enrollStudent(new Student("Carol White", "carol@uni.edu", "555-1003", Department.BUSINESS, "Finance"));
        studentService.enrollStudent(new Student("David Brown", "david@uni.edu", "555-1004", Department.LINGUISTICS, "Translation"));
        studentService.enrollStudent(new Student("Eva Green", "eva@uni.edu", "555-1005", Department.INFORMATION_TECHNOLOGY, "AI Research"));

        // Courses
        courseService.createCourse("CS101", "Object Oriented Programming", "Core OOP concepts in Java", 8, Department.INFORMATION_TECHNOLOGY);
        courseService.createCourse("CS102", "Data Structures & Algorithms", "Arrays, trees, graphs and sorting", 8, Department.INFORMATION_TECHNOLOGY);
        courseService.createCourse("CS103", "Machine Learning", "Supervised and unsupervised learning", 4, Department.INFORMATION_TECHNOLOGY);
        courseService.createCourse("BUS101", "Financial Accounting", "Basics of financial accounting", 4, Department.BUSINESS);
        courseService.createCourse("LNG101", "Introduction to Linguistics", "Phonetics, morphology and syntax", 8, Department.LINGUISTICS);

        System.out.println("Database initialized with sample data!");
    }
}
