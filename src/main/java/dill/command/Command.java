package dill.command;

import dill.exception.DillException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * A general abstract class that represents a command that can be executed.
 * Serves as the foundation for all specific commands.
 */
public abstract class Command {
    /**
     * Executes the specific logic of the command.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @throws DillException If an error occurs during execution.
     */
    public abstract String execute(
            TaskList taskList, Storage storage, QuoteList quoteList) throws DillException;

    /**
     * Indicates if the program should terminate.
     *
     * @return True if the command is the exit command.
     */
    public boolean isExit() {
        return false;
    }
}
