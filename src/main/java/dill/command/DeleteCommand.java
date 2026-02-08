package dill.command;

import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.exception.StorageException;
import dill.exception.ExecutionException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            Task removed = taskList.deleteTask(taskIndex);
            String deleteMessage = "    Noted. I've removed this task:\n"
                    + "      " + removed + "\n"
                    + "    Now you have " + taskList.getSize() + " tasks in the list.";
            ui.displayMessage(deleteMessage);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot delete an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}