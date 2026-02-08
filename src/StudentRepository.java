public interface StudentRepository {

    void save(Student student);

    Student findById(String studentId);

    boolean existsById(String studentId);

    void deleteById(String studentId);
}
