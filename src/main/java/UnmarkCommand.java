public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            tasks.unmarkTask(taskIndex);
            String unmarkMessage = "    OK, I've marked this task as not done yet:\n" +
                    "      " + tasks.getTask(taskIndex);
            ui.displayMessage(unmarkMessage);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot unmark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}
