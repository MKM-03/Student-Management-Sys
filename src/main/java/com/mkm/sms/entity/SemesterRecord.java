package com.mkm.sms.entity;

import com.mkm.sms.enums.EnrollmentStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "semester_record")
public class SemesterRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.DRAFT;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "semeester_record_id")
    private final List<Enrollment> enrollments = new ArrayList<>();



    protected SemesterRecord() {}

    public SemesterRecord(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    public boolean isAlreadyEnrolled(String courseCode) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public void removeEnrollment(Enrollment e) {
        enrollments.remove(e);
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

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Student getStudent() { return student; }

    public Semester getSemester() { return semester; }

    public List<Enrollment> getEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public boolean isLocked() {
        return status == EnrollmentStatus.LOCKED;
    }

    public boolean isDraft() {
        return status == EnrollmentStatus.DRAFT;
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
    }

    public void lock() {
        this.status = EnrollmentStatus.LOCKED;
    }

    @Override
    public String toString() {
        return "SemesterRecord{ " +
                "semester=" + semester.getName() +
                " | status=" + status +
                " | credits=" + getTotalCredits() +
                " | gpa=" + String.format("%.2f", calculateSemesterGpa()) +
                " }";
    }
}
