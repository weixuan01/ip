package dill.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import dill.task.Task;
import dill.task.TaskList;
import dill.task.ToDo;
import dill.task.Deadline;
import dill.task.Event;
import dill.exception.StorageException;

/**
 * Represents the data storage management component of Dill.
 */
public class Storage {
    private File taskFile;
    private File quoteFile;

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
     * @throws StorageException If the file path does not exist or the file is unreadable
     */
    public List<Task> loadTasks() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(taskFile);
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
            return tasks;
        } catch (FileNotFoundException e1) {
            try {
                taskFile.getParentFile().mkdirs(); // create data folder if it doesn't exist
                taskFile.createNewFile();
                return tasks;
            } catch (IOException e2) {
                throw new StorageException("    [SYSTEM]: Error creating task storage file");
            }
        }
    }

    /**
     * Save tasks to the data storage file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws StorageException If the file path does not exist or the file is unreadable
     */
    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            // creates file if file doesn't exist, throws IOException if parent dir also doesn't exist
            FileWriter fileWriter = new FileWriter(taskFile);
            for (int i = 0; i < taskList.getSize(); i++) {
                fileWriter.write(taskList.getTask(i).toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageException("    [SYSTEM]: Error saving to disk");
        }
    }

    public List<String> loadQuotes() throws StorageException {
        List<String> quotes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(quoteFile);
            while (scanner.hasNextLine()) {
                quotes.add(scanner.nextLine());
            }
            return quotes;
        } catch (FileNotFoundException e1) {
            try {
                quoteFile.getParentFile().mkdirs(); // create data folder if it doesn't exist
                quoteFile.createNewFile();
                return quotes;
            } catch (IOException e2) {
                throw new StorageException("    [SYSTEM]: Error creating quote storage file");
            }
        }
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
