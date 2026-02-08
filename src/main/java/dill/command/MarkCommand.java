package dill.command;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;
import dill.exception.ExecutionException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            taskList.markTask(taskIndex);
            String markMessage = "    Nice! I've marked this task as done:\n" +
                    "      " + taskList.getTask(taskIndex);
            ui.displayMessage(markMessage);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot mark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}