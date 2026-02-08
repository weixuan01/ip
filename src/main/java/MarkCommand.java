public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        try {
            tasks.markTask(taskIndex);
            String markMessage = "    Nice! I've marked this task as done:\n" +
                    "      " + tasks.getTask(taskIndex);
            ui.displayMessage(markMessage);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e1) {
            throw new ExecutionException("    Cannot mark an entry that is not in the list!");
        } catch (StorageException e2) {
            ui.displaySystemMessage(e2.getMessage());
        }
    }
}