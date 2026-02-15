package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * Represents a command to list all current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks to the user via the user interface.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        return String.join("\n", taskList.listTasks());
    }
}
