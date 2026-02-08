package dill.task;

public abstract class Task {
    private String taskName;
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

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }

    public String toFileString() {
        if (isDone) {
            return "1 | " + taskName;
        }
        return "0 | " + taskName;
    }
}
