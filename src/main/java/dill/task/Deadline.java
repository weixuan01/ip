package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified deadline.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDate byDate;

    /**
     * Creates an instance of Deadline, and initializes its name and deadline.
     *
     * @param taskName The description of the task.
     * @param byDate The deadline of the task.
     */
    public Deadline(String taskName, LocalDate byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public String[] getDates() {
        return new String[]{byDate.toString()};
    }

    public boolean isOccurringOn(LocalDate date) {
        assert date != null : "LocalDate object should not be null";
        return this.byDate.equals(date);
    }

    public void updateTask(String taskName, LocalDate byDate, LocalDate startDate, LocalDate endDate) {
        if (startDate != null || endDate != null) {
            throw new IllegalArgumentException("A deadline task does not have start and end dates!");
        }
        if (taskName != null) {
            setTaskName(taskName);
        }
        if (byDate != null) {
            this.byDate = byDate;
        }
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
