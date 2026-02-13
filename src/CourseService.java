import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(String courseCode,
                             String title,
                             String description,
                             int creditHours, Department department) {

        if (creditHours < 2 || creditHours > 8) {
            throw new IllegalArgumentException("Error adding course hours! Minimum hours allowed are 2");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Error! Course code cannot be empty");
        }
        if (courseRepository.existsByCode(courseCode)) {
            throw new IllegalArgumentException(
                    "Error adding course! This course already exist by the code " + courseCode);
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Error adding course title! The title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Error adding description! The description cannot be empty");
        }
        if (department == null) {
            throw new IllegalArgumentException("Error adding department! All courses must belong to a department");
        }

        Course course = new Course(courseCode, title, description, creditHours, department);
        courseRepository.saveCourse(course);

        return course;
    }

    public Course findCourseById(String courseId) {
        Course course = courseRepository.findById(courseId);

        if (course == null) {
            throw new IllegalArgumentException("Course not found by ID: " + courseId);
        }

        return course;
    }


    public Course findCourseByCode(String courseCode) {
        Course course = courseRepository.findByCode(courseCode);

        if (course == null) {
            throw new IllegalArgumentException("Course was not found by Code: " + courseCode);
        }

        return course;
    }

    public List<Course> listAllCourses() { return courseRepository.listAll(); }

    public List<Course> listByDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Error! Department cannot be empty");
        }
        return courseRepository.listByDepartment(department);
    }
}
