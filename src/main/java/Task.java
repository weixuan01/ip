public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
