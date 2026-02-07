public class ListCommand extends Command {
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException{
        ui.displayMessage(tasks.listTasks());
    }
}
