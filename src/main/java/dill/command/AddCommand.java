package dill.command;

import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        try {
            taskList.addTask(task);
            String addMessage = "    Got it. I've added this task:\n" +
                                "      " + task + "\n" +
                                "    Now you have " + taskList.getSize() + " tasks in the list.";
            ui.displayMessage(addMessage);
            storage.saveTasks(taskList);
        } catch (StorageException e) {
            ui.displaySystemMessage(e.getMessage());
        }
    }
}

