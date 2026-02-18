package dill.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

public class ViewCommand extends Command {
    private LocalDate date;

    public ViewCommand(LocalDate date) {
        this.date = date;
    }

    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        List<TaskList.MatchingTask> matchingTasks = taskList.filterByDate(date);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("There are no tasks occurring on ")
                    .append(formattedDate);
            return output.toString();
        }

        output.append("Here are the tasks occurring on ")
                .append(formattedDate)
                .append(":\n")
                .append(formatMatchingTasks(matchingTasks));
        return output.toString();
    }
}