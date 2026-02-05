import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        return "[E]" + super.toString() + " (start: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ", end: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public String toFileString() {
        return "E | " + super.toFileString() + " | " + startDate + " | " + endDate;
    }
}
