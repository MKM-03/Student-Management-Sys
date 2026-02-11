import java.util.HashMap;
import java.util.Map;

public class InMemoryStudentRepository implements StudentRepository{
    private final Map<String, Student> students = new HashMap<>();


    @Override
    public void saveStudent(Student student) {
        students.put(student.getStudentID(), student);
    }

    @Override
    public Student findById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public boolean existsById(String studentId) {
        return students.containsKey(studentId);
    }

    @Override
    public void deleteById(String studentId) {
        students.remove(studentId);
    }
}
