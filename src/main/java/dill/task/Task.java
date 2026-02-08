package dill.task;

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
        isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markUndone() {
        isDone = false;
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
