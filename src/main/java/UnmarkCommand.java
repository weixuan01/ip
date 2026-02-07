public class UnmarkCommand {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        tasks.unmarkTask(taskIndex);
        String unmarkMessage = "    OK, I've marked this task as not done yet:\n" +
                                 "      " + tasks.getTask(taskIndex);
        ui.displayMessage(unmarkMessage);
        // SAVE TO DISK HERE
    }
}
