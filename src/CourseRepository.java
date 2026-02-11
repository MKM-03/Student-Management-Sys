public interface CourseRepository {

    void saveCourse(Course course);

    void deleteById(String courseId);

    Course findById(String courseId);

    Boolean existsById(String courseId);
}
