public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void enrollStudent(Student student) {
        if (studentRepository.existsById(student.getStudentID())) {
            throw new IllegalArgumentException("Student already exists");
        }
        studentRepository.save(student);
    }

    public Student findStudentById(String stdID) {
        Student std = studentRepository.findById(stdID);
        if (std == null) {
            throw new IllegalArgumentException("Student was not found");
        }
        return std;
    }

    public void updateGpa(String stdId, double newGpa) {
        if (newGpa < 0.0 || newGpa > 4.0) {
            throw new IllegalArgumentException("Invalid GPA");
        }
        Student std = findStudentById(stdId);
        std.setGpa(newGpa);
        studentRepository.save(std);
    }

    public void promoteStudent(String stdId) {

        if ()
    }
}
