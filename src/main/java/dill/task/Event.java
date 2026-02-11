package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified given start and end date.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Creates an instance of Event, initializing its name along with its start and end date.
     *
     * @param taskName The description of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     */
    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[E]" + super.toString() + " (start: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ", end: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * @return A formatted string representation of the task.
     */
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + startDate + " | " + endDate;
    }
}
