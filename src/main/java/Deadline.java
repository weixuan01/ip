import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | " + date;
    }
}
