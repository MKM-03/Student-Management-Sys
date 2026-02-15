package com.mkm.sms.service;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.enums.StudentStatus;
import com.mkm.sms.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void enrollStudent(Student student) {
        if (studentRepository.existsById(student.getStudentID())) {
            throw new IllegalArgumentException("Student already exists");
        }
        studentRepository.saveStudent(student);
    }

    public Student findStudentById(String stdID) {
        Student std = studentRepository.findById(stdID);
        if (std == null) {
            throw new IllegalArgumentException("Student was not found with ID: " + stdID);
        }
        return std;
    }

    public Student findStudentByName(String name) {
        Student std = studentRepository.findByName(name);
        if (std == null) {
            throw new IllegalArgumentException("Student was not found with name: " + name);
        }
        return std;
    }

    public void updateGpa(String stdId, double newGpa) {
        if (newGpa < 0.0 || newGpa > 4.0) {
            throw new IllegalArgumentException("Invalid GPA");
        }
        Student std = findStudentById(stdId);
        std.setGpa(newGpa);
        studentRepository.saveStudent(std);
    }

    public void promoteStudent(String stdId) {
        Student std = findStudentById(stdId);
        if (std.getStatus() != StudentStatus.ACTIVE) {
            throw new IllegalArgumentException("Error! Only active students can be promoted");
        }
        std.setYearLevel(std.getYearLevel() + 1);
        studentRepository.saveStudent(std);
    }

    public void updateStudentStatus(String stdId, StudentStatus newStatus) {
        Student std = findStudentById(stdId);
        if (std.getStatus() == newStatus) {
            throw new IllegalStateException(
                    "Student " + stdId + " already has status " + newStatus);
        }
        std.setStatus(newStatus);
        studentRepository.saveStudent(std);
    }

    public List<Student> listAllStudents() { return studentRepository.listAll(); }

    public List<Student> listByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Error! Department cannot be empty");
        }
        return studentRepository.listByDepartment(department);
    }
}

