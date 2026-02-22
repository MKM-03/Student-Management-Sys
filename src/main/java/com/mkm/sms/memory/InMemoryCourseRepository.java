package com.mkm.sms.memory;

import com.mkm.sms.entity.Course;
import com.mkm.sms.enums.Department;
import com.mkm.sms.repository.CourseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCourseRepository implements CourseRepository {
    private final Map<String, Course> courses = new HashMap<>();


    @Override
    public void saveCourse(Course course) {
        courses.put(course.getCourseId(), course);
    }

    @Override
    public void deleteById(String courseId) {
        courses.remove(courseId);
    }

    @Override
    public Course findById(String courseId) {
        return courses.get(courseId);
    }

    public Course findByCode(String courseCode) {
        for (Course course : courses.values()) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public Boolean existsById(String courseId) {
        return courses.containsKey(courseId);
    }

    @Override
    public Boolean existsByCode(String courseCode) {
        for (Course course : courses.values()) {
            if (course.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Course> listAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public List<Course> listByDepartment(Department department) {
        List<Course> result = new ArrayList<>();

        for (Course course : courses.values()) {
            if (course.getDepartment() == department) {
                result.add(course);
            }
        }
        return result;
    }
}
