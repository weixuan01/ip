package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

/**
 * Represents a command to list all current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks to the user via the user interface.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.displayMessage(taskList.listTasks());
    }
}
