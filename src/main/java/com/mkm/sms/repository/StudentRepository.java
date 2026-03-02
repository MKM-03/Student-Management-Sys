package com.mkm.sms.repository;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT COUNT(s) FROM Student s WHERE s.department = :dept AND YEAR(s.enrollmentDate) = :year")
    int countByDepartmentAndYear(@Param("dept") Department dept, @Param("year") int year);

    Student findByName(String name);

    List<Student> findByDepartment(Department department);

}
