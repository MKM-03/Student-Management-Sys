package com.mkm.sms.memory;

import com.mkm.sms.entity.Student;
import com.mkm.sms.enums.Department;
import com.mkm.sms.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<String, Student> students = new HashMap<>();


    @Override
    public void saveStudent(Student student) {
        students.put(student.getStudentID(), student);
    }

    @Override
    public void deleteById(String studentId) {
        students.remove(studentId);
    }

    @Override
    public boolean existsById(String studentId) {
        return students.containsKey(studentId);
    }

    @Override
    public Student findById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student findByName(String name) {
        for (Student std : students.values()) {
            if (std.getName().equals(name)) {
                return std;
            }
        }
        return null;
    }

    @Override
    public List<Student> listAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public List<Student> listByDepartment(Department department) {
        List<Student> result = new ArrayList<>();

        for (Student std : students.values()) {
            if (std.getDepartment() == department) {
                result.add(std);
            }
        }
        return result;
    }
}
