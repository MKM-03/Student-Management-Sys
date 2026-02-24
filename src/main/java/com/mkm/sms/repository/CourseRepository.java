package com.mkm.sms.repository;

import com.mkm.sms.entity.Course;
import com.mkm.sms.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    Course findByCourseCode(String courseCode);

    Boolean existsByCourseCode(String courseCode);

    List<Course> findByDepartment(Department department);
}
