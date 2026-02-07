public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        tasks.addTask(task); // if not successful throw exception
        String addMessage = "    Got it. I've added this task:\n" +
                                  "    " + task + "\n" +
                                  "    Now you have " + tasks.getSize() + " tasks in the list.";
        ui.displayMessage(addMessage);
        // SAVE to DISK here
    }
}

