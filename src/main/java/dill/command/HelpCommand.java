package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

public class HelpCommand extends Command {
    private final String HELP_MESSAGE =
            "    Here are the available commands:\n" +
            "        list: displays the tasks in your list.\n" +
            "        todo <task_name>: adds a todo task.\n" +
            "        deadline <task_name> /by <yyyy-mm-dd>: adds a deadline task.\n" +
            "        event <task_name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>: adds an event task.\n" +
            "        mark <entry_number>: marks a task as done.\n" +
            "        unmark <entry_number>: marks a task as undone.";

    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.displayMessage(HELP_MESSAGE);
    }
}
