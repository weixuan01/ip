public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task removed = tasks.deleteTask(taskIndex);
        String deleteMessage = "    Noted. I've removed this task:\n" +
                                "      " + removed + "\n" +
                                "    Now you have " + tasks.getSize() + " tasks in the list.";
        ui.displayMessage(deleteMessage);
        // SAVE TASKS HERE
    }
}