package dill.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.task.Deadline;
import dill.task.Event;
import dill.task.Task;
import dill.task.TaskList;
import dill.task.ToDo;

/**
 * Represents the data storage management component of Dill.
 */
public class Storage {
    private File taskFile;
    private File quoteFile;
    private boolean isTaskWritable = false;

    /**
     * Creates an instance of Storage and initializes it with a file path.
     *
     * @param taskPath The relative path of the task storage file.
     * @param quotePath The relative path of the quote storage file.
     */
    public Storage(String taskPath, String quotePath) {
        this.taskFile = new File(taskPath);
        this.quoteFile = new File(quotePath);
    }

    /**
     * Loads task from the data storage file
     *
     * @return A list of tasks loaded from storage.
     * @throws StorageException If the file path does not exist or the file is unreadable.
     */
    public List<Task> loadTasks() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(taskFile)){
            while (scanner.hasNextLine()) {
                String[] taskVars = scanner.nextLine().split(" \\| ");
                Task task = decodeTask(taskVars);
                if (task != null) {
                    if (taskVars[1].equals("1")) {
                        task.setDone();
                    }
                    tasks.add(task);
                }
            }
            isTaskWritable = true;
            return tasks;
        } catch (FileNotFoundException e1) {
            try {
                taskFile.getParentFile().mkdirs(); // create data folder if it doesn't exist
                taskFile.createNewFile();
                isTaskWritable = true;
                return tasks;
            } catch (IOException e2) {
                throw new StorageException("There was an error creating a task storage file.");
            }
        }
    }

    /**
     * Save tasks to the data storage file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws StorageException If the file path does not exist or the file is unreadable.
     */
    public void saveTasks(TaskList taskList) throws StorageException {
        if (!isTaskWritable) {
            return; // Do not save
        }
        try {
            FileWriter fileWriter = new FileWriter(taskFile);
            for (int i = 0; i < taskList.getSize(); i++) {
                fileWriter.write(taskList.getTask(i).toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            isTaskWritable = false;
            throw new StorageException("There was an expected error saving to disk, "
                    + "disabling auto save for the rest of this session.");
        }
    }

    /**
     * Load quotes from the quote storage file.
     * @return A list of string quotes loaded from storage.
     * @throws StorageException If the file path does not exist or the file is unreadable.
     */
    public List<String> loadQuotes() throws StorageException {
        if (!quoteFile.exists() || quoteFile.length() == 0) {
            return loadDefaultQuotes();
        }

        List<String> quotes = new ArrayList<>();
        try (Scanner scanner = new Scanner(quoteFile)) {
            while (scanner.hasNextLine()) {
                String quote = scanner.nextLine();
                if (!quote.isBlank()){
                    quotes.add(quote);
                }
            }
            return quotes;
        } catch (FileNotFoundException e) {
            throw new StorageException("Quote storage file was unexpectedly deleted or moved! Please reboot.");
        }
    }

    private List<String> loadDefaultQuotes() throws StorageException {
        // Create quote storage file if it doesn't exist
        if (!quoteFile.exists()) {
            try {
                quoteFile.getParentFile().mkdirs(); // Create data folder if it doesn't exist
                quoteFile.createNewFile();
            } catch (IOException e) {
                throw new StorageException("There was an error creating a quote storage file.");
            }
        }

        List<String> quotes = QuoteList.getDefaultQuotes();

        // Write default quotes to storage file.
        try (FileWriter fileWriter = new FileWriter(quoteFile)) {
            for (int i = 0; i < quotes.size(); i++) {
                fileWriter.write(quotes.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new StorageException("There was an error loading the default quotes.");
        }

        return quotes;
    }

    private Task decodeTask(String[] taskVars) {
        switch (taskVars[0]) {
        case "T":
            return new ToDo(taskVars[2]);
        case "D":
            return new Deadline(taskVars[2], LocalDate.parse(taskVars[3]));
        case "E":
            return new Event(taskVars[2], LocalDate.parse(taskVars[3]), LocalDate.parse(taskVars[4]));
        default:
            return null;
        }
    }
}
