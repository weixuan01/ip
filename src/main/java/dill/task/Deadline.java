package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified deadline.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDate date;

    /**
     * Creates an instance of Deadline, and initializes its name and deadline.
     *
     * @param taskName The description of the task.
     * @param date The deadline of the task.
     */
    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public String[] getDates() {
        return new String[]{date.toString()};
    }

    public boolean isOccurringOn(LocalDate date) {
        assert date != null : "LocalDate object should not be null";
        return this.date.equals(date);
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
