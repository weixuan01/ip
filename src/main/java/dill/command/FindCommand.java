package dill.command;

import java.util.List;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * Represents a command to find all tasks matching a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates an instance of FindCommand and initializes it with the specified keyword.
     *
     * @param keyword The word to be used for matching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks matching the specified keyword.
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        List<TaskList.MatchingTask> matchingTasks = taskList.filterByName(keyword);
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("No matching tasks found.");
            return output.toString();
        }

        output.append("Here are the matching tasks in your list:\n")
                .append(formatMatchingTasks(matchingTasks));
        return output.toString();
    }
}
