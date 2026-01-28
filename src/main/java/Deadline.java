public class Deadline extends Task {
    private String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    public String toString() {
        if (getIsDone()) {
            return "[D][X] " + getTaskName() + " (by: " + date + ")";
        }
        return "[D][ ] " + getTaskName() + " (by: " + date + ")";
    }
}
