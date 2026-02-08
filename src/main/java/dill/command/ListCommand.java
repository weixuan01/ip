package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

public class ListCommand extends Command {
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.displayMessage(taskList.listTasks());
    }
}
