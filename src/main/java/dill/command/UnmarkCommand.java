package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;
import dill.exception.ExecutionException;

/**
 * Represents a command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of UnmarkCommand and initializes it with a specified task index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the task at the specified index in the list as complete.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            taskList.unmarkTask(taskIndex);
            String unmarkMessage = "    OK, I've marked this task as not done yet:\n" +
                    "      " + taskList.getTask(taskIndex);
            ui.displayMessage(unmarkMessage);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot unmark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}
