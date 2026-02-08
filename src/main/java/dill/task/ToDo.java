package dill.task;

/**
 * Represents a task with a given name, the most basic task type.
 */
public class ToDo extends Task {
    /**
     * Creates a new instance of ToDo and initializes its name.
     *
     * @param taskName The description of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * @return A formatted string representation of the task.
     */
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
