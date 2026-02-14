public class EnrollmentService {


    public void enroll(Student student, Course course, SemesterRecord record) {

        int newTotalCredits =
                record.getTotalCredits() + course.getCreditsHours();

        if (newTotalCredits > 24) {
            throw new IllegalArgumentException("Maximum semester credit hours is 24");
        }
        if (record.getTotalCredits() != 0 && newTotalCredits < 12) {
            throw new IllegalArgumentException("Minimum semester credit hours is 12");
        }

        Enrollment enrollment = new Enrollment(course);
        record.addEnrollment(enrollment);
    }
}
