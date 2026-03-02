package com.mkm.sms.config;

import com.mkm.sms.entity.Semester;
import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.repository.SemesterRepository;
import com.mkm.sms.service.CourseService;
import com.mkm.sms.service.StudentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataInitializer implements ApplicationRunner {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SemesterRepository semesterRepository;


    public DataInitializer(StudentService studentService, CourseService courseService, SemesterRepository semesterRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.semesterRepository = semesterRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            studentService.enrollStudent(new Student("Alice Johnson", "alice@uni.edu", "555-1001", Department.INFORMATION_TECHNOLOGY, "Software Engineering"));
            studentService.enrollStudent(new Student("Bob Smith", "bob@uni.edu", "555-1002", Department.INFORMATION_TECHNOLOGY, "Data Science"));
            studentService.enrollStudent(new Student("Carol White", "carol@uni.edu", "555-1003", Department.BUSINESS, "Finance"));
            studentService.enrollStudent(new Student("David Brown", "david@uni.edu", "555-1004", Department.LINGUISTICS, "Translation"));
            studentService.enrollStudent(new Student("Eva Green", "eva@uni.edu", "555-1005", Department.INFORMATION_TECHNOLOGY, "AI Research"));

            courseService.createCourse("CS101", "Object Oriented Programming", "Core OOP concepts in Java", 3, Department.INFORMATION_TECHNOLOGY);
            courseService.createCourse("CS102", "Data Structures & Algorithms", "Arrays, trees, graphs and sorting", 3, Department.INFORMATION_TECHNOLOGY);
            courseService.createCourse("CS201", "Machine Learning", "Supervised and unsupervised learning", 3, Department.INFORMATION_TECHNOLOGY);
            courseService.createCourse("BUS101", "Financial Accounting", "Basics of financial accounting", 3, Department.BUSINESS);
            courseService.createCourse("LNG101", "Introduction to Linguistics", "Phonetics, morphology and syntax", 3, Department.LINGUISTICS);

            semesterRepository.save(new Semester("Fall 2025", LocalDate.of(2025, 9, 1), LocalDate.of(2026, 1, 15)));
            semesterRepository.save(new Semester("Spring 2026", LocalDate.of(2026, 2, 1), LocalDate.of(2026, 6, 30)));
            semesterRepository.save(new Semester("Fall 2026", LocalDate.of(2026, 9, 1), LocalDate.of(2027, 1, 15)));

            System.out.println("✅ Database initialized with sample data!");
        } catch (Exception e) {
            System.err.println("❌ DataInitializer failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
