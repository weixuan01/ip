package dill.task;

import java.time.LocalDate;

public class UpdateFields {
    private String taskName;
    private LocalDate byDate;
    private LocalDate startDate;
    private LocalDate endDate;

    public UpdateFields() {}

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setByDate(LocalDate byDate) {
        this.byDate = byDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getByDate() {
        return this.byDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public boolean isEmpty() {
        return (this.taskName == null) && (this.byDate == null) && (this.startDate == null) && (this.endDate == null);
    }
}
