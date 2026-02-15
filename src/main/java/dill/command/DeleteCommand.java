package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;

/**
 * Represents a command to remove a task from the list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of DeleteCommand and initializes it with the specified task.
     * @param taskIndex The index of the task to be removed from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the removal of a task from the task list.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public String execute(TaskList taskList, Storage storage,
            QuoteList quoteList) throws ExecutionException {
        String message = "";
        try {
            Task removed = taskList.deleteTask(taskIndex);
            message += "Noted. I've removed this task:\n"
                    + "  " + removed + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException("I cannot delete an entry that is not in the list!");
        } catch (StorageException e) {
            message += "\n" + e.getMessage();
        }
        return message;
    }
}
