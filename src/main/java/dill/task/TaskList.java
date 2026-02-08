package dill.task;

import java.util.List;
import java.util.ArrayList;

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
     * @return A formatted string representation of a task list containing all matching tasks.
     */
    public String findTasks(String keyword) {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(keyword)) {
                output += "    " + (i + 1) + "." + t + "\n";
            }
        }
        if (output.isEmpty()) {
            return "    No matching tasks found.";
        }
        return "    Here are the matching tasks in your list:\n" + output.stripTrailing();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A formatted string representation of the task list.
     */
    public String listTasks() {
        String output = "";
        if (tasks.isEmpty()) {
            output += "    There are no tasks in the list!";
        } else {
            output += "    Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += "    " + (i + 1) + "." + tasks.get(i);
                if (i != tasks.size() - 1) {
                    output += "\n";
                }
            }
        }
        return output;
    }
}
