import java.time.format.DateTimeParseException;

public class Dill {
    private static final String FILE_PATH = "./data/dill.txt";
    private TaskList tasks;
    private UserInterface ui;
    private Storage storage;

    public Dill(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
            ui.displayLoadSuccess(tasks.getSize());
        } catch (StorageException e) {
            ui.displayLoadError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void start() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command cmd = Parser.parse(userInput);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DillException e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Dill(FILE_PATH).start();
    }
}
