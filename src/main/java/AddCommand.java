public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        try {
            tasks.addTask(task);
            String addMessage = "    Got it. I've added this task:\n" +
                                "      " + task + "\n" +
                                "    Now you have " + tasks.getSize() + " tasks in the list.";
            ui.displayMessage(addMessage);
            storage.saveTasks(tasks);
        } catch (StorageException e) {
            ui.displaySystemMessage(e.getMessage());
        }
    }
}

