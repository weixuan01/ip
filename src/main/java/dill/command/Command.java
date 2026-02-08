package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;
import dill.exception.DillException;

public abstract class Command {
    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage, QuoteList quoteList) throws DillException;

    public boolean isExit() {
        return false;
    }
}
