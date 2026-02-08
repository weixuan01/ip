package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;
import dill.exception.StorageException;
import dill.exception.ExecutionException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) throws ExecutionException {
        try {
            taskList.unmarkTask(taskIndex);
            String unmarkMessage = "    OK, I've marked this task as not done yet:\n" +
                    "      " + taskList.getTask(taskIndex);
            ui.displayMessage(unmarkMessage);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot unmark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}
