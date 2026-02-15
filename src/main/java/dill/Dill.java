package dill;

import dill.command.Command;
import dill.exception.DillException;
import dill.exception.StorageException;
import dill.parser.Parser;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.cli.UserInterface;
import dill.userinterface.UiMessages;


/**
 * Main class for the Dill chatbot application.
 */
public class Dill {
    private static final String PATH_TASKS = "./data/dill.txt";
    private static final String PATH_QUOTES = "./data/quotes.txt";
    private TaskList taskList;
    private UserInterface textUi;
    private Storage storage;
    private QuoteList quoteList;
    private String loadMessage = "";

    /**
     * Creates a new instance of Dill and initializes core components.
     */
    public Dill() {
        this.textUi = new UserInterface();
        this.storage = new Storage(PATH_TASKS, PATH_QUOTES);

        // Load task list
        try {
            this.taskList = new TaskList(storage.loadTasks());
            loadMessage += UiMessages.getLoadSuccess(taskList.getSize());
        } catch (StorageException e) {
            this.taskList = new TaskList();
            loadMessage += UiMessages.getLoadError(e.getMessage());
        }

        // Load quote list
        try {
            this.quoteList = new QuoteList(storage.loadQuotes());
        } catch (StorageException e) {
            this.quoteList = new QuoteList();
            loadMessage += e.getMessage();
        }
    }

    public String getGuiBootMessage() {
        return UiMessages.getGreeting(loadMessage);
    }

    public String getResponse(String userInput) {
        try {
            Command cmd = Parser.parse(userInput);
            return cmd.execute(taskList, storage, quoteList);
        } catch (DillException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the main loop of the text-ui chatbot.
     * Displays a greeting message, then reads, parses, and executes inputs until an exit command ("bye") is given.
     */
    public void start() {
        textUi.displayMessage(UiMessages.getGreeting(loadMessage));
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = textUi.readInput();
                Command cmd = Parser.parse(userInput);
                String output = cmd.execute(taskList, storage, quoteList);
                textUi.displayMessage(output);
                isExit = cmd.isExit();
            } catch (DillException e) {
                textUi.displayMessage(e.getMessage());
            }
        }
    }

    // text-ui entry point
    public static void main(String[] args) {
        new Dill().start();
    }
}
