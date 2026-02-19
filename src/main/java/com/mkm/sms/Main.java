package com.mkm.sms;

import com.mkm.sms.entity.Course;
import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.memory.InMemoryCourseRepository;
import com.mkm.sms.memory.InMemoryStudentRepository;
import com.mkm.sms.repository.CourseRepository;
import com.mkm.sms.repository.StudentRepository;
import com.mkm.sms.service.CourseService;
import com.mkm.sms.service.StudentService;

public class Main {
    public static void main(String[] args) {

        //===== Service =====
        StudentRepository stdRepo = new InMemoryStudentRepository();
        CourseRepository crsRepo = new InMemoryCourseRepository();
        StudentService stdService = new StudentService(stdRepo);
        CourseService courseService = new CourseService(crsRepo);



        //===== Students =====
        Student std1 = new Student(
                "MK",
                "12324@@gmail.com",
                "1234",
                Department.INFORMATION_TECHNOLOGY,
                "AI");

        Student std2 = new Student(
                "Kareem",
                "324234@@gmail.com",
                "634563",
                Department.INFORMATION_TECHNOLOGY,
                "Cyber Security");


        Student std3 = new Student(
                "Omar",
                "11122@gmail.com",
                "2334",
                Department.BUSINESS,
                "Any"
        );


        //===== Courses =====
        Course crs1 = courseService.createCourse(
                "m110",
                "Python Beginner",
                "Python programming for startups",
                8,
                Department.INFORMATION_TECHNOLOGY
        );

        Course crs2 = courseService.createCourse(
                "TM251",
                "OOP Java",
                "Java programming intermediate lvl",
                8,
                Department.INFORMATION_TECHNOLOGY
        );

        Course crs3 = courseService.createCourse(
                "GR101",
                "General",
                "None",
                3,
                Department.GENERAL
        );

        Course crs4 = courseService.createCourse(
                "m110",
                "Python Beginner",
                "Python programming for startups",
                8,
                Department.INFORMATION_TECHNOLOGY
        );

        //===== enrollments =====
        /* stdService.enrollStudent(std1);
        stdService.enrollStudent(std2);
        stdService.enrollStudent(std3); */

        // Testing duplicate check
        // stdService.enrollStudent(std1);


        //===


    }
}