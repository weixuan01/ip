package dill.command;

import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;

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
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        try {
            taskList.addTask(task);
            String addMessage = "    Got it. I've added this task:\n"
                    + "      " + task + "\n"
                    + "    Now you have " + taskList.getSize() + " tasks in the list.";
            ui.displayMessage(addMessage);
            storage.saveTasks(taskList);
        } catch (StorageException e) {
            ui.displaySystemMessage(e.getMessage());
        }
    }
}

