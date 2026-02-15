package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

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
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public String execute(TaskList taskList, Storage storage,
            QuoteList quoteList) throws ExecutionException {
        String message = "";
        try {
            taskList.markTask(taskIndex);
            message += "Nice! I've marked this task as done:\n"
                    + "  " + taskList.getTask(taskIndex);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException("I cannot mark an entry that is not in the list!");
        } catch (StorageException e) {
            message += "\n" + e.getMessage();
        }
        return message;
    }
}
