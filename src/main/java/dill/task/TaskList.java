package dill.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks stored by Dill.
 */
public class TaskList {
    private List<Task> tasks;

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
        tasks.get(taskIndex).setDone();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).setUndone();
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
     * Search for tasks in the list containing the specified keyword.
     *
     * @param keyword The specified matching keyword.
     * @return A formatted list of strings representing the matching tasks.
     */
    public List<String> findTasks(String keyword) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(keyword)) {
                output.add((i + 1) + "." + t);
            }
        }
        if (output.isEmpty()) {
            output.add("No matching tasks found.");
        } else {
            output.addFirst("Here are the matching tasks in your list:");
        }
        return output;
    }

    /**
     * Lists the tasks in the current task list.
     *
     * @return A formatted list of strings representing the current tasks in the list.
     */
    public List<String> listTasks() {
        List<String> output = new ArrayList<>();
        if (tasks.isEmpty()) {
            output.add("There are no tasks in the list!");
            return output;
        }
        output.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.add((i + 1) + "." + tasks.get(i));
        }
        return output;
    }
}
