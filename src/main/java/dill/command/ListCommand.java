package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

public class ListCommand extends Command {
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.displayMessage(tasks.listTasks());
    }
}
