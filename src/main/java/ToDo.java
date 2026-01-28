public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public String toString() {
        if (getIsDone()) {
            return "[T][X] " + getTaskName();
        }
        return "[T][ ] " + getTaskName();
    }
}
