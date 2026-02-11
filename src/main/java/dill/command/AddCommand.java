package dill.command;

import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UserInterface;

/**
 * Represents a command to add a new task to the list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an instance of AddCommand and initializes it with the specified task.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of the task to the provided task list.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        try {
            taskList.addTask(task);
            ui.displayMessage("Got it. I've added this task:", "  " + task,
                    "Now you have " + taskList.getSize() + " tasks in the list.");
            storage.saveTasks(taskList);
        } catch (StorageException e) {
            ui.displaySystemMessage(e.getMessage());
        }
    }
}

