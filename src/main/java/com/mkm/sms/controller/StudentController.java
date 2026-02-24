package com.mkm.sms.controller;


import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.enums.StudentStatus;
import com.mkm.sms.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService stdService;

    public StudentController(StudentService stdService) {
        this.stdService = stdService;
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        stdService.enrollStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(stdService.listAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(stdService.findStudentById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        return ResponseEntity.ok(stdService.findStudentByName(name));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Student>> getStudentByDepartment(@PathVariable Department department) {
        return ResponseEntity.ok(stdService.listByDepartment(department));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable String id,
                                               @RequestParam StudentStatus newStatus) {
        stdService.updateStudentStatus(id, newStatus);
        return ResponseEntity.ok("Student status updated successfully");
    }

    @PutMapping("/{id}/promote")
    public ResponseEntity<String> promoteStudent(@PathVariable String id) {
        stdService.promoteStudent(id);
        return ResponseEntity.ok("Student is now promoted successfully");
    }

    @PutMapping("/{id}/gpa")
    public ResponseEntity<String> updateGpa(@PathVariable String id,
                                            @RequestParam double gpa) {
        stdService.updateGpa(id, gpa);
        return ResponseEntity.ok("Student's gpa updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        stdService.findStudentById(id);
        stdService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
