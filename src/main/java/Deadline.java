public class Deadline extends Task {
    private String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | " + date;
    }
}
