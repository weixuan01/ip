public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        tasks.markTask(taskIndex);
        String markMessage = "    Nice! I've marked this task as done:\n" +
                               "      " + tasks.getTask(taskIndex);
        ui.displayMessage(markMessage);
        // SAVE TO DISK HERE
    }
}