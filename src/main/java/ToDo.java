public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
