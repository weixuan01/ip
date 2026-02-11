package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UserInterface;

/**
 * Represents a command to find all tasks matching a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates an instance of FindCommand and initializes it with the specified keyword.
     * @param keyword The word to be used for matching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks matching the specified keyword.
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        ui.displayMessage(taskList.findTasks(keyword));
    }
}
