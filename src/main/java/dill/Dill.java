package dill;

import dill.task.TaskList;
import dill.userinterface.UserInterface;
import dill.storage.Storage;
import dill.parser.Parser;
import dill.command.Command;
import dill.exception.DillException;
import dill.exception.StorageException;

public class Dill {
    private static final String FILE_PATH = "./data/dill.txt";
    private TaskList taskList;
    private UserInterface ui;
    private Storage storage;

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
