package dill.storage;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import dill.task.Task;
import dill.task.TaskList;
import dill.task.ToDo;
import dill.task.Deadline;
import dill.task.Event;
import dill.exception.StorageException;

public class Storage {
    private File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    public List<Task> loadTasks() throws StorageException {
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String[] taskVars = scanner.nextLine().split(" \\| ");
                Task task = decodeTask(taskVars);
                if (task != null) {
                    if (taskVars[1].equals("1")) {
                        task.markDone();
                    }
                    taskList.add(task);
                }
            }
            return taskList;
        } catch (FileNotFoundException e1) {
            try {
                storageFile.getParentFile().mkdirs(); // create data folder if it doesn't exist
                storageFile.createNewFile();
                return taskList;
            } catch (IOException e2) {
                throw new StorageException("    [SYSTEM]: Error creating storage file");
            }
        }
    }

    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            // creates file if file doesn't exist, throws IOException if parent dir also doesn't exist
            FileWriter fileWriter = new FileWriter(storageFile);
            for (int i = 0; i < taskList.getSize(); i++) {
                fileWriter.write(taskList.getTask(i).toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageException("    [SYSTEM]: Error saving to disk");
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
