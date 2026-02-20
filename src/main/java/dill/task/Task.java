package dill.task;

import java.time.LocalDate;

/**
 * Represents a generic task that provides the foundation for specific task types.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Initializes a task with a name and sets its status as incomplete.
     *
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public abstract String getTaskType();

    public abstract void updateTask(String taskName, LocalDate byDate, LocalDate startDate, LocalDate endDate);

    public String[] getDates() {
        return new String[0]; // Return empty array by default for a todo task
    }

    /**
     * Marks the task as completed.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        assert taskName != null : "task name should not be null";
        this.taskName = taskName;
    }

    public boolean isOccurringOn(LocalDate date) {
        return false; // Returns false by default for a todo task
    }

    /**
     *  Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
