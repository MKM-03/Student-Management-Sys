public class Main {
    public static void main(String[] args) {

        StudentRepository repository = new InMemoryStudentRepository();

        StudentService service1 = new StudentService(repository);

        Student student = new Student(
                "MK",
                "mk@gmail.com",
                "01012345678",
                "IT",
                "Software Engineering"
        );

        service1.enrollStudent(student);
        System.out.println("Student enrolled successfully");

        try {
            service1.enrollStudent(student);
            System.out.println("Student enrolled successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}