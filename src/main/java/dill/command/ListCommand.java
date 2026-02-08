package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;

public class ListCommand extends Command {
    public void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) {
        ui.displayMessage(taskList.listTasks());
    }
}
