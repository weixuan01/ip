package dill;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.quote.QuoteList;
import dill.parser.Parser;
import dill.command.Command;
import dill.exception.DillException;
import dill.exception.StorageException;

public class Dill {
    private static final String PATH_TASKS = "./data/dill.txt";
    private static final String PATH_QUOTES = "./data/quotes.txt";
    private TaskList taskList;
    private UserInterface ui;
    private Storage storage;
    private QuoteList quoteList;

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
