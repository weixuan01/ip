package dill.command;

import java.time.LocalDate;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

public class UpdateCommand extends Command {
    private int taskIndex;
    private String taskName;
    private LocalDate byDate;
    private LocalDate startDate;
    private LocalDate endDate;

    public UpdateCommand(int taskIndex, String taskName, LocalDate byDate, LocalDate startDate, LocalDate endDate) {
        this.taskIndex = taskIndex;
        this.taskName = taskName;
        this.byDate = byDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) throws ExecutionException {
        StringBuilder messageBuilder = new StringBuilder();

        try {
            Task task = taskList.getTask(taskIndex);
            String taskBefore = task.toString();
            task.updateTask(taskName, byDate, startDate, endDate);
            storage.saveTasks(taskList);
            messageBuilder.append(UiMessages.getTaskUpdateSuccess(taskBefore, task));
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException("I cannot update an entry that is not in the list!");
        } catch (IllegalArgumentException e) {
            throw new ExecutionException(e.getMessage());
        } catch (StorageException e) {
            messageBuilder.append("\n")
                    .append(e.getMessage());
        }

        return messageBuilder.toString();
    }
}
