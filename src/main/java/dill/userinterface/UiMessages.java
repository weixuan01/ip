package dill.userinterface;

public class UiMessages {
    private static final String GREETING = "Hello! I'm Dill.\n%s\nWhat shall we do this time?";
    private static final String LOAD_TASKS_SUCCESS = "I found %d saved tasks. Let's pick up where we left off!";
    private static final String LOAD_TASKS_SUCCESS_EMPTY = "I could not find any saved tasks. Let's start fresh!";
    private static final String LOAD_TASKS_ERROR = "%s\nThis session will not be saved.";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String HELP_MESSAGE = "Here are the available commands:\n"
            + "list\n"
            + "todo <task-name>\n"
            + "deadline <task-name> /by <yyyy-mm-dd>\n"
            + "event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>\n"
            + "mark <entry-number>\n"
            + "unmark <entry-number>\n"
            + "delete <entry-number>\n"
            + "find <keyword>\n"
            + "cheer\n"
            + "bye";

    public static String getGreeting(String loadMessage) {
        return String.format(GREETING, loadMessage);
    }

    public static String getExit() {
         return EXIT_MESSAGE;
    }

    public static String getLoadTasksSuccess(int n) {
        if (n > 0) {
            return String.format(LOAD_TASKS_SUCCESS, n);
        } else {
            return LOAD_TASKS_SUCCESS_EMPTY;
        }
    }

    public static String getLoadTasksError(String errorMessage) {
        return String.format(LOAD_TASKS_ERROR, errorMessage);
    }

    public static String getHelp() {
        return HELP_MESSAGE;
    }
}
