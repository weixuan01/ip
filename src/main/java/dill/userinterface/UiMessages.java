package dill.userinterface;

public class UiMessages {
    private static final String GREETING = "Hello! I'm Dill.\n%s\nWhat shall we do this time?";
    private static final String LOAD_SUCCESS = "I found %d saved tasks. Let's pick up where we left off!";
    private static final String LOAD_SUCCESS_EMPTY = "I could not find any saved tasks. Let's start fresh!";
    private static final String LOAD_ERROR = "%s\nThis session will not be saved.";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String HELP_MESSAGE = "Here are the available commands:\n"
            + "    list: displays the tasks in your list.\n"
            + "    todo <task_name>: adds a todo task.\n"
            + "    deadline <task_name> /by <yyyy-mm-dd>: adds a deadline task.\n"
            + "    event <task_name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>: adds an event task.\n"
            + "    mark <entry_number>: marks a task as done.\n"
            + "    unmark <entry_number>: marks a task as undone.\n"
            + "    delete <entry_number>: removes a task from the list.\n"
            + "    find <keyword>: displays tasks matching the specified keyword.\n"
            + "    cheer: displays a random motivational message.";

    public static String getGreeting(String loadMessage) {
        return String.format(GREETING, loadMessage);
    }

    public static String getExit() {
         return EXIT_MESSAGE;
    }

    public static String getLoadSuccess(int n) {
        if (n > 0) {
            return String.format(LOAD_SUCCESS, n);
        } else {
            return LOAD_SUCCESS_EMPTY;
        }
    }

    public static String getLoadError(String errorMessage) {
        return String.format(LOAD_ERROR, errorMessage);
    }

    public static String getHelp() {
        return HELP_MESSAGE;
    }
}
