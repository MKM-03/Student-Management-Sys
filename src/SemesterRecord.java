import java.util.ArrayList;
import java.util.List;

public class SemesterRecord {
    private final Student student;
    private final Semester semester;
    private final List<Enrollment> enrollments = new ArrayList<>();


    public SemesterRecord(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public int getTotalCredits() {
        int total = 0;
        for (Enrollment e : enrollments) {
            total += e.getCreditHours();
        }
        return total;
    }

    public double calculateSemesterGpa() {
        double points = 0;
        int credits = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                points += e.getGrade() * e.getCreditHours();
                credits += e.getCreditHours();
            }
        }
        return credits == 0 ? 0.0 : points / credits;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
