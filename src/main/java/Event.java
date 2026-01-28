public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        if (getIsDone()) {
            return "[E][X] " + getTaskName() + " (start: " + startTime + ", end: " + endTime + ")";
        }
        return "[E][ ] " + getTaskName() + " (start: " + startTime + ", end: " + endTime + ")";
    }
}
