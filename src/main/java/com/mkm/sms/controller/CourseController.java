package com.mkm.sms.controller;


import com.mkm.sms.entity.Course;
import com.mkm.sms.enums.Department;
import com.mkm.sms.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course created = courseService.createCourse(
                course.getCourseCode(),
                course.getTitle(),
                course.getDescription(),
                course.getCreditsHours(),
                course.getDepartment()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        return ResponseEntity.ok(courseService.findCourseByCode(code));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Course>> getCourseByDepartment(@PathVariable Department department) {
        return ResponseEntity.ok(courseService.listByDepartment(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        courseService.findCourseById(id); // verify exists first
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
