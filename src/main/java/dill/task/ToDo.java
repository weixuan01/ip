package dill.task;

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

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
