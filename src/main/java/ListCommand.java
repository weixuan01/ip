public class ListCommand extends Command {
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.displayMessage(tasks.listTasks());
    }
}
