package com.mkm.sms.repository;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByName(String name);

    List<Student> listByDepartment(Department department);

}
