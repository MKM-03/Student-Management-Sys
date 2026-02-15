package com.mkm.sms;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.memory.InMemoryStudentRepository;
import com.mkm.sms.repository.StudentRepository;
import com.mkm.sms.service.StudentService;

public class Main {
    public static void main(String[] args) {

        StudentRepository repository = new InMemoryStudentRepository();

        StudentService service1 = new StudentService(repository);

        Student student = new Student(
                "MK",
                "mk@gmail.com",
                "01012345678",
                Department.INFORMATION_TECHNOLOGY,
                "Software Engineering"
        );



        service1.enrollStudent(student);
        System.out.println("Student enrolled successfully");

        try {
            service1.enrollStudent(student);
            System.out.println("Student enrolled successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}