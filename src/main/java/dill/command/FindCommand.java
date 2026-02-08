package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.displayMessage(taskList.findTasks(keyword));
    }
}
