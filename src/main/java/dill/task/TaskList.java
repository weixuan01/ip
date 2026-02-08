package dill.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markDone();
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markUndone();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

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
