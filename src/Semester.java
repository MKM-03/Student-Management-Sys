import java.time.LocalDate;

public record Semester(String id, String name, LocalDate startDate, LocalDate endDate) {
    public Semester {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must before end date.");
        }
    }
}
