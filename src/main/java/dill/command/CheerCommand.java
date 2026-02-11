package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UserInterface;

/**
 * Represents a command to output a random motivational message.
 */
public class CheerCommand extends Command {
    /**
     * Executes the cheer command by displaying a motivational message.
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        ui.displayMessage(quoteList.getRandomQuote());

    }
}
