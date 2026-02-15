package com.mkm.sms.repository;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;

import java.util.List;

public interface StudentRepository {

    void saveStudent(Student student);

    void deleteById(String studentId);

    Student findById(String studentId);

    Student findByName(String name);

    boolean existsById(String studentId);

    List<Student> listAll();

    List<Student> listByDepartment(Department department);

}
