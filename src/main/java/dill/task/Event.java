package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified given start and end date.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
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

    public String getTaskType() {
        return TASK_TYPE;
    }

    public String[] getDates() {
        return new String[]{startDate.toString(), endDate.toString()};
    }

    public boolean isOccurringOn(LocalDate date) {
        assert date != null : "LocalDate object should not be null";

        boolean isBeforeStart = date.isBefore(startDate);
        boolean isAfterEnd = date.isAfter(endDate);
        return !isBeforeStart && !isAfterEnd;
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (start: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ", end: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
