package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;
import dill.exception.ExecutionException;

/**
 * Represents a command to mark a task as complete.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of MarkCommand and initializes it with a specified task index.
     *
     * @param taskIndex The index of the task to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the task at the specified index in the list as complete.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            taskList.markTask(taskIndex);
            String markMessage = "    Nice! I've marked this task as done:\n"
                    + "      " + taskList.getTask(taskIndex);
            ui.displayMessage(markMessage);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot mark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}