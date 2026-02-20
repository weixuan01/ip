package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

public class CloneCommand extends Command {
    private int taskIndex;

    public CloneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) throws ExecutionException {
        StringBuilder messageBuilder = new StringBuilder();

        try {
            Task clonedTask = taskList.getTask(taskIndex).cloneTask();
            taskList.addTask(clonedTask);
            messageBuilder.append(UiMessages.getCloneSuccess(clonedTask, taskList.getSize()));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException("I cannot clone a task that is not in the list!");
        } catch (StorageException e) {
            messageBuilder.append("\n")
                    .append(e.getMessage());
        }

        return messageBuilder.toString();
    }
}
