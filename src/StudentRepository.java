public interface StudentRepository {

    void saveStudent(Student student);

    Student findById(String studentId);

    boolean existsById(String studentId);

    void deleteById(String studentId);
}
