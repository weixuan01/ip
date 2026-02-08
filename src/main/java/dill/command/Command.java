package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;
import dill.exception.DillException;

/**
 * A general abstract class that represents a command that can be executed.
 * Serves as the foundation for all specific commands.
 */
public abstract class Command {
    /**
     * Executes the specific logic of the command.
     *
     * @param taskList The list of tasks to be executed on.
     * @param ui The user interface for displaying messages.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @throws DillException If an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) throws DillException;

    /**
     * Indicates if the program should terminate.
     *
     * @return True if the command is the exit command.
     */
    public boolean isExit() {
        return false;
    }
}
