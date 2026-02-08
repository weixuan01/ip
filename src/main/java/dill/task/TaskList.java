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
     * Returns a string representation of the task list.
     *
     * @return A formatted string representation of the task list.
     */
    public String listTasks() {
        String list = "";
        if (tasks.isEmpty()) {
            list += "    There are no tasks in the list!";
        } else {
            list += "    Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                list += "    " + (i + 1) + "." + tasks.get(i);
                if (i != tasks.size() - 1) {
                    list += "\n";
                }
            }
        }
        return list;
    }
}
