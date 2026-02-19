package dill.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dill.exception.CorruptedFileException;
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
        assert taskPath != null : "task storage file path should not be null";
        assert quotePath != null : "quote storage file path should not be null";
        this.taskFile = new File(taskPath);
        this.quoteFile = new File(quotePath);
    }

    /**
     * Loads task from the data storage file
     *
     * @return A list of tasks loaded from storage.
     * @throws StorageException If the file path does not exist, the file path is invalid, or the file is unreadable
     */
    public List<Task> loadTasks() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(taskFile)) {
            while (scanner.hasNextLine()) {
                String[] taskVars = scanner.nextLine().split(" \\| "); // Split by " | "
                Task task = decodeTask(taskVars);
                String doneStatus = taskVars[1];
                if (doneStatus.equals("1")) {
                    task.setDone();
                }
                tasks.add(task);
            }
            isTaskWritable = true;
            return tasks;
        } catch (FileNotFoundException e1) {
            try {
                taskFile.getParentFile().mkdirs(); // create data folder if it doesn't exist
                assert taskFile.getParentFile().exists() : "Parent directory should have been created";
                taskFile.createNewFile();
                assert taskFile.exists() : "Task file should have been created";
                isTaskWritable = true;
                return tasks;
            } catch (IOException e2) {
                throw new StorageException("Error creating a task storage file.");
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
                fileWriter.write(taskList.getTask(i).toFileString());
                if (i < taskList.getSize() - 1) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            isTaskWritable = false;
            throw new StorageException("Error saving to disk");
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
                if (!quote.isBlank()) {
                    quotes.add(quote);
                }
            }
            return quotes;
        } catch (FileNotFoundException e) {
            throw new StorageException("Quote storage file deleted or moved.");
        }
    }

    private List<String> loadDefaultQuotes() throws StorageException {
        // Create quote storage file if it doesn't exist
        if (!quoteFile.exists()) {
            try {
                quoteFile.getParentFile().mkdirs(); // Create data folder if it doesn't exist
                assert quoteFile.getParentFile().exists() : "Parent directory should have been created";
                quoteFile.createNewFile();
                assert quoteFile.exists() : "Quote file should have been created";
            } catch (IOException e) {
                throw new StorageException("Error creating quote storage file.");
            }
        }

        List<String> quotes = QuoteList.getDefaultQuotes();
        assert !quotes.isEmpty() : "Quote list should not be empty after loading default quotes";

        // Write default quotes to storage file.
        try (FileWriter fileWriter = new FileWriter(quoteFile)) {
            for (int i = 0; i < quotes.size(); i++) {
                fileWriter.write(quotes.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new StorageException("Error loading default quotes.");
        }

        return quotes;
    }

    public void setTaskWritable(boolean isTaskWritable) {
        this.isTaskWritable = isTaskWritable;
    }

    private Task decodeTask(String[] taskVars) throws CorruptedFileException {
        if (taskVars.length < 3) {
            throw new CorruptedFileException("");
        }
        String taskType = taskVars[0];
        String doneStatus = taskVars[1];
        String taskName = taskVars[2];

        if (!doneStatus.equals("0") && !doneStatus.equals("1")) {
            throw new CorruptedFileException("Done status is not 1 or 0");
        }

        switch (taskType) {
        case "T":
            validateLength(taskVars, 3); // Verify length is not > 3
            return new ToDo(taskName);
        case "D":
            validateLength(taskVars, 4);
            return new Deadline(taskName, parseDate(taskVars[3]));
        case "E":
            validateLength(taskVars, 5);
            return new Event(taskName, parseDate(taskVars[3]), parseDate(taskVars[4]));
        default:
            throw new CorruptedFileException("Task type is not T, D, or E.");
        }
    }

    private void validateLength(String[] taskVars, int expectedLength) throws CorruptedFileException {
        if (taskVars.length != expectedLength) {
            throw new CorruptedFileException("Incomplete task information (< 3 variables).");
        }
    }

    private LocalDate parseDate(String date) throws CorruptedFileException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new CorruptedFileException("Date time format is not yyyy-mm-dd.");
        }
    }
}
