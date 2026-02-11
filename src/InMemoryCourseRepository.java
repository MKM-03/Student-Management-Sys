import java.util.HashMap;
import java.util.Map;

public class InMemoryCourseRepository implements CourseRepository{
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

    @Override
    public Boolean existsById(String courseId) {
        return courses.containsKey(courseId);
    }
}
