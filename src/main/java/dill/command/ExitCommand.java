package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UserInterface;

/**
 * Represents a command to terminate the application.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Executes the exit command by displaying an exit message.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     */
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        ui.displayMessage(EXIT_MESSAGE);
    }

    /**
     * Signals the program to terminate.
     * @return Always true, as this is the exit command.
     */
    public boolean isExit() {
        return true;
    }
}
