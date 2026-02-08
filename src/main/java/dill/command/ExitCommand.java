package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

public class ExitCommand extends Command {
    private final String EXIT_MESSAGE = "    Bye. Hope to see you again soon!";

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.displayMessage(EXIT_MESSAGE);
    }

    public boolean isExit() {
        return true;
    }

}
