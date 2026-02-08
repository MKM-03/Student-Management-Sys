import java.time.LocalDate;
import java.util.UUID;

public class Student extends User {
    private final String studentID;
    private String department;
    private String careerPath;
    private StudentStatus status;
    private LocalDate enrollmentDate;
    private double gpa = 0;
    private int yearLevel = 1;



    public Student(String name, String email, String phoneNum,
                   String department, String careerPath) {
        super(name, email, phoneNum);
        this.studentID = UUID.randomUUID().toString();
        this.department = department;
        this.careerPath = careerPath;
        this.status = StudentStatus.ACTIVE;
        this.enrollmentDate = LocalDate.now();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getDepartment() {
        return department;
    }

    public String getCareerPath() {
        return careerPath;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getGpa() {
        return gpa;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCareerPath(String careerPath) {
        this.careerPath = careerPath;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public void setYearLevel(int yearLevel) {
        if (yearLevel < 1 || yearLevel > 5) {
            throw new IllegalArgumentException("Error!! Year level must be between 1 and 4");
        }
        this.yearLevel = yearLevel;
    }

    public void setGpa(double gpa) {
        // Do note this could differ based on whether the German GPA scale is used or not.
        if (gpa <  0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("Error!! GPA must be between 0.0 and 4.0");
        }
        this.gpa = gpa;
    }


}
