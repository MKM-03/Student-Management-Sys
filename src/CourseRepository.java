import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course);

    void deleteById(String courseId);

    Course findById(String courseId);

    Course findByCode(String courseCode);

    Boolean existsById(String courseId);

    Boolean existsByCode(String courseCode);

    List<Course> listAll();

    List<Course> listByDepartment();
}
