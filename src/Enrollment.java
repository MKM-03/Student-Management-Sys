public class Enrollment {
    private final Course course;
    private Double grade;

    public Enrollment(Course course) {
        this.course = course;
    }

    public int getCreditHours() {
        return course.getCreditsHours();
    }

    public Course getCourse() { return course; }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
