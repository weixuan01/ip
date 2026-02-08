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
