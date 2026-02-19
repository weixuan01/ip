package dill.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks stored by Dill.
 */
public class TaskList {
    private List<Task> tasks;

    public static class MatchingTask {
        private Task task;
        private int index;

        public MatchingTask(Task task, int index) {
            this.task = task;
            this.index = index;
        }

        public Task getTask() {
            return this.task;
        }

        public int getIndex() {
            return this.index;
        }
    }

    /**
     * Creates an instance of TaskList and initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates an instance of TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves a specific task from the list.
     *
     * @param taskIndex The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Lists the tasks in the current task list.
     *
     * @return A formatted list of strings representing the current tasks in the list.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).setIsDone(true);
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).setIsDone(false);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex The index of the task in the list.
     * @return The removed task.
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Search for tasks in the list with names containing the specified keyword.
     *
     * @param keyword The specified matching keyword.
     * @return A formatted list of strings representing the matching tasks.
     */
    public List<MatchingTask> filterByName(String keyword) {
        List<MatchingTask> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getTaskName().contains(keyword)) {
                matchingTasks.add(new MatchingTask(t, i));
            }
        }
        return matchingTasks;
    }

    public List<MatchingTask> filterByDate(LocalDate date) {
        List<MatchingTask> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.isOccurringOn(date)) {
                matchingTasks.add(new MatchingTask(t, i));
            }
        }
        return matchingTasks;
    }
}
