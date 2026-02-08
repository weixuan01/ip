package dill;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.parser.Parser;
import dill.command.Command;
import dill.exception.DillException;
import dill.exception.StorageException;

/**
 * Main class for the Dill chatbot application.
 */
public class Dill {
    private static final String FILE_PATH = "./data/dill.txt";
    private TaskList taskList;
    private UserInterface ui;
    private Storage storage;

    /**
     * Creates a new instance of Dill and initializes core components.
     *
     * @param filePath The relative file path to the data storage file.
     */
    public Dill(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTasks());
            ui.displayLoadSuccess(taskList.getSize());
        } catch (StorageException e) {
            ui.displayLoadError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the main loop of the chatbot.
     * Displays a greeting message, then repeatedly reads, parses, and executes
     * user inputs until an exit command ("bye") is given.
     */
    public void start() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command cmd = Parser.parse(userInput);
                cmd.execute(taskList, ui, storage);
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