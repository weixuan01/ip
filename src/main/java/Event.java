public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (start: " + startTime + ", end: " + endTime + ")";
    }

    public String toFileString() {
        return "E | " + super.toFileString() + " | " + startTime + " | " + endTime;
    }
}
