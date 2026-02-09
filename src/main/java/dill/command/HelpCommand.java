package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;

/**
 * Represents a command to display the list of available commands and their syntaxes.
 */
public class HelpCommand extends Command {
    private final String HELP_MESSAGE = "    Here are the available commands:\n"
            + "        list: displays the tasks in your list.\n"
            + "        todo <task_name>: adds a todo task.\n"
            + "        deadline <task_name> /by <yyyy-mm-dd>: adds a deadline task.\n"
            + "        event <task_name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>: adds an event task.\n"
            + "        mark <entry_number>: marks a task as done.\n"
            + "        unmark <entry_number>: marks a task as undone.\n"
            + "        find <keyword>: displays tasks matching the specified keyword.\n"
            + "        cheer: displays a random motivational message.";

    /**
     * Executes the help command by displaying a list of all supported operations.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        ui.displayMessage(HELP_MESSAGE);
    }
}
