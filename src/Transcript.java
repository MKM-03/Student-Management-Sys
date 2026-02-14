import java.util.List;

public class Transcript {
    private final Student student;
    private final List<SemesterRecord> records;

    public Transcript(Student student, List<SemesterRecord> records) {
        this.student = student;
        this.records = records;
    }

    public double calculateOverallGpa() {
        double points = 0;
        int totalCredits = 0;

        for (SemesterRecord record : records) {
            double gpa = record.calculateSemesterGpa();
            int hours = record.getTotalCredits();
            points += gpa * hours;
            totalCredits += hours;
        }
        return totalCredits == 0 ? 0.0 : points / totalCredits;
    }
}
