package com.mkm.sms.service;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.enums.StudentStatus;
import com.mkm.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void enrollStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findStudentById(String stdID) {
        return studentRepository.findById(stdID)
                .orElseThrow(() -> new IllegalArgumentException("Student was not found with ID: " + stdID));
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
        studentRepository.save(std);
    }

    public void promoteStudent(String stdId) {
        Student std = findStudentById(stdId);
        if (std.getStatus() != StudentStatus.ACTIVE) {
            throw new IllegalArgumentException("Error! Only active students can be promoted");
        }
        std.setYearLevel(std.getYearLevel() + 1);
        studentRepository.save(std);
    }

    public void updateStudentStatus(String stdId, StudentStatus newStatus) {
        Student std = findStudentById(stdId);
        if (std.getStatus() == newStatus) {
            throw new IllegalStateException(
                    "Student " + stdId + " already has status " + newStatus);
        }
        std.setStatus(newStatus);
        studentRepository.save(std);
    }

    public void deleteStudent(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalArgumentException("Student not found by ID: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> listAllStudents() { return studentRepository.findAll(); }

    public List<Student> listByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Error! Department cannot be empty");
        }
        return studentRepository.findByDepartment(department);
    }
}

