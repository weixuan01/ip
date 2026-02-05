import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Dill {
    public static final String CHATBOT_NAME = "Dill";
    private static final String LINE = "    ____________________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();
    private static final File DATA_FILE = new File("./data/dill.txt");

    private static void printGreeting() {
        System.out.println(LINE);
        System.out.println("    Hello! I'm " + CHATBOT_NAME + ".");
        if (tasks.isEmpty()) {
            System.out.println("    I could not find any previous tasks. Let's start fresh!");
        } else {
            System.out.println("    I found " + tasks.size() + " previously saved tasks.");
        }
        System.out.println("    What can I do for you?");
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void printTaskList() {
        System.out.println(LINE);
        if (tasks.isEmpty()) {
            System.out.println("    There are no tasks in the list!");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    // also throws NumberFormatException if argument cannot be parsed to int
    private static void markTask(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("mark ") || userInput.equals("mark ")) {
            throw new InvalidCommandException("Please specify an entry to mark.");
        }
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new InvalidCommandException("Cannot mark an entry that is not in the list!");
        }
        tasks.get(taskIndex).markDone();
        System.out.println(LINE);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + tasks.get(taskIndex));
        System.out.println(LINE);
        saveTasks();
    }

    private static void unmarkTask(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("unmark ") || userInput.equals("unmark ")) {
            throw new InvalidCommandException("Please specify an entry to unmark.");
        }
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new InvalidCommandException("Cannot unmark an entry that is not in the list!");
        }
        tasks.get(taskIndex).markUndone();
        System.out.println(LINE);
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + tasks.get(taskIndex));
        System.out.println(LINE);
        saveTasks();
    }

    private static void addToDo(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("todo ") || userInput.equals("todo ")) {
            throw new InvalidCommandException("Please specify todo task name.");
        }
        String taskName = userInput.substring(5);
        tasks.add(new ToDo(taskName));
        printTaskAdded();
        saveTasks();
    }

    private static void addDeadline(String userInput) throws InvalidCommandException{
        int byIndex = userInput.indexOf("/by ");
        if (!userInput.startsWith("deadline ") || userInput.equals("deadline ") || byIndex == 9) {
            throw new InvalidCommandException("Please specify deadline task name.");
        }
        if (byIndex < 0) {
            throw new InvalidCommandException("Please specify a deadline.");
        }
        String taskName = userInput.substring(9, byIndex - 1);
        String rawDate = userInput.substring(byIndex + 4);
        LocalDate date = LocalDate.parse(rawDate);
        tasks.add(new Deadline(taskName, date));
        printTaskAdded();
        saveTasks();
    }

    private static void addEvent(String userInput) throws InvalidCommandException {
        int startIndex = userInput.indexOf("/start ");
        int endIndex = userInput.indexOf("/end ");
        if (!userInput.startsWith("event ") || userInput.equals("event ") || startIndex == 6 || endIndex == 6) {
            throw new InvalidCommandException("Please specify event task name.");
        }
        if (startIndex < 0) {
            throw new InvalidCommandException("Please specify a start time.");
        }
        if (endIndex < 0) {
            throw new InvalidCommandException("Please specify an end time.");
        }
        if (endIndex < startIndex) {
            throw new InvalidCommandException("Please specify start time followed by end time, in that order.");
        }
        String taskName = userInput.substring(6, userInput.indexOf("/start") - 1);
        String rawStartDate = userInput.substring(userInput.indexOf("/start") + 7, userInput.indexOf("/end") - 1);
        String rawEndDate = userInput.substring(userInput.indexOf("/end") + 5);
        LocalDate startDate = LocalDate.parse(rawStartDate);
        LocalDate endDate = LocalDate.parse(rawEndDate);
        tasks.add(new Event(taskName, startDate, endDate));
        printTaskAdded();
        saveTasks();
    }

    private static void printTaskAdded() {
        System.out.println(LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.getLast());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void printHelp() {
        System.out.println(LINE);
        System.out.println("    Here are the available commands:");
        System.out.println("        list: displays the tasks in your list.");
        System.out.println("        todo <task_name>: adds a todo task.");
        System.out.println("        deadline <task_name> /by <yyyy-mm-dd>: adds a deadline task.");
        System.out.println("        event <task_name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>: adds an event task.");
        System.out.println("        mark <entry_number>: marks a task as done.");
        System.out.println("        unmark <entry_number>: marks a task as undone.");
        System.out.println(LINE);
    }

    private static void deleteTask(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("delete ") || userInput.equals("delete ")) {
            throw new InvalidCommandException("Please specify an entry to delete.");
        }
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new InvalidCommandException("Cannot delete an entry that is not in the list!");
        }
        Task remove = tasks.remove(taskIndex);
        System.out.println(LINE);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + remove);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
        saveTasks();
    }

    private static Command extractCommand(String userInput) {
        if (userInput.equals("bye")) {
            return Command.EXIT;
        }
        if (userInput.equals("list")) {
            return Command.LIST;
        }
        if (userInput.startsWith("mark")) {
            return Command.MARK;
        }
        if (userInput.startsWith("unmark")) {
            return Command.UNMARK;
        }
        if (userInput.startsWith("todo")) {
            return Command.TODO;
        }
        if (userInput.startsWith("deadline")) {
            return Command.DEADLINE;
        }
        if (userInput.startsWith("event")) {
            return Command.EVENT;
        }
        if (userInput.equals("help")) {
            return Command.HELP;
        }
        if (userInput.startsWith("delete")) {
            return Command.DELETE;
        }
        return Command.UNKNOWN;
    }

    private static void loadTasks() {
        try {
            Scanner scanner = new Scanner(DATA_FILE);
            while (scanner.hasNextLine()) {
                String[] taskVars = scanner.nextLine().split(" \\| ");
                Task task = decodeTask(taskVars);
                if (task != null) {
                    if (taskVars[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e1) {
            try {
                DATA_FILE.getParentFile().mkdirs(); // create data folder if it doesn't exist
                DATA_FILE.createNewFile();
            } catch (IOException e2) {
                System.out.println("    Error creating dill.txt");
            }
        }
    }

    private static Task decodeTask(String[] taskVars) {
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

    private static void saveTasks() {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            for (Task t : tasks) {
                fileWriter.write(t.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("    Error saving tasks to disk.");
        }
    }

    public static void main(String[] args) {
        loadTasks();
        printGreeting();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                Command cmd = extractCommand(userInput);
                if (cmd == Command.EXIT) {
                    printExit();
                    break;
                } else if (cmd == Command.LIST) {
                    printTaskList();
                } else if (cmd == Command.MARK) {
                    markTask(userInput);
                } else if (cmd == Command.UNMARK) {
                    unmarkTask(userInput);
                } else if (cmd == Command.TODO) {
                    addToDo(userInput);
                } else if (cmd == Command.DEADLINE) {
                    addDeadline(userInput);
                } else if (cmd == Command.EVENT) {
                    addEvent(userInput);
                } else if (cmd == Command.HELP) {
                    printHelp();
                } else if (cmd == Command.DELETE) {
                    deleteTask(userInput);
                } else {
                    throw new InvalidCommandException("I'm not quite sure what you meant.\n    Type \"help\" if you wish to view a list of available commands.");
                }
            } catch (NumberFormatException e) {
                System.out.println(LINE);
                System.out.println("    Entry must be an integer!");
                System.out.println(LINE);
            } catch (InvalidCommandException e) {
                System.out.println(LINE);
                System.out.println("    " + e.getMessage());
                System.out.println(LINE);
            } catch (DateTimeParseException e) {
                System.out.println(LINE);
                System.out.println("    Please enter dates in the format yyyy-mm-dd.");
                System.out.println(LINE);
            }
        }
    }
}
