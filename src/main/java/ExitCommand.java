public class ExitCommand extends Command {
    private final String EXIT_MESSAGE = "    Bye. Hope to see you again soon!";
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws ExecutionException {
        ui.displayMessage(EXIT_MESSAGE);
    }
}
