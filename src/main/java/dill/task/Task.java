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

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void setUndone() {
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isOccuringOn(LocalDate date) {
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

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * @return A formatted string representation of the task.
     */
    public String toFileString() {
        if (isDone) {
            return "1 | " + taskName;
        }
        return "0 | " + taskName;
    }
}
