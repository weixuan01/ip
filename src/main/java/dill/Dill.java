package dill;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;
import dill.parser.Parser;
import dill.command.Command;
import dill.exception.DillException;
import dill.exception.StorageException;

/**
 * Main class for the Dill chatbot application.
 */
public class Dill {
    private static final String PATH_TASKS = "./data/dill.txt";
    private static final String PATH_QUOTES = "./data/quotes.txt";
    private TaskList taskList;
    private UserInterface ui;
    private Storage storage;
    private QuoteList quoteList;

    /**
     * Creates a new instance of Dill and initializes core components.
     *
     * @param taskPath The relative path of the task storage file.
     * @param quotePath The relative path of the quote storage file.
     */
    public Dill(String taskPath, String quotePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(PATH_TASKS, PATH_QUOTES);

        // Load task list
        try {
            this.taskList = new TaskList(storage.loadTasks());
            ui.displayLoadSuccess(taskList.getSize());
        } catch (StorageException e) {
            ui.displayLoadError(e.getMessage());
            this.taskList = new TaskList();
        }

        // Load quote list
        try {
            this.quoteList = new QuoteList(storage.loadQuotes());
        } catch (StorageException e) {
            ui.displaySystemMessage(e.getMessage());
            this.quoteList = new QuoteList();
        }
    }

    /**
     * Starts the main loop of the chatbot.
     * Displays a greeting message, then reads, parses, and executes inputs until an exit command ("bye") is given.
     */
    public void start() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command cmd = Parser.parse(userInput);
                cmd.execute(taskList, ui, storage, quoteList);
                isExit = cmd.isExit();
            } catch (DillException e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Dill(PATH_TASKS, PATH_QUOTES).start();
    }
}