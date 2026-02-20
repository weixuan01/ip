package dill.task;

import java.time.LocalDate;

/**
 * Represents a task with a given name, the most basic task type.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    /**
     * Creates a new instance of ToDo and initializes its name.
     *
     * @param taskName The description of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public void updateTask(String taskName, LocalDate byDate, LocalDate startDate, LocalDate endDate) {
        if (byDate != null || startDate != null || endDate != null) {
            throw new IllegalArgumentException("A todo task does not have a date!");
        }
        if (taskName != null) {
            setTaskName(taskName);
        }
    }

    public Task cloneTask() {
        return new ToDo(this.getTaskName());
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
