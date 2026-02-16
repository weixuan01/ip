package dill.userinterface;

public class UiMessages {
    private static final String GREETING = "Hello! I'm Dill.\n%s\nWhat shall we do this time?";
    private static final String LOAD_TASKS_SUCCESS = "I found %d saved tasks. Let's pick up where we left off!";
    private static final String LOAD_TASKS_SUCCESS_EMPTY = "I could not find any saved tasks. Let's start fresh!";
    private static final String LOAD_TASKS_ERROR = "%s\nThis session will not be saved.";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String HELP_MESSAGE =
            """
            Here are the available commands:"
            1. list
               Displays the current tasks in the list.
            2. todo <task-name>
               Adds a to-do task to the list.
            3. deadline <task-name> /by <yyyy-mm-dd>
               Adds a deadline task to your list.
            4. event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>
               Adds an event task to the list.
            5. mark <entry-number>
               Marks the task at specified entry as complete.
            6. unmark <entry-number>
               Marks the task at specified entry as incomplete.
            7. delete <entry-number>
               Removes the task at specified entry from the list.
            8. find <keyword>
               Displays all tasks matching the specified keyword.
            9. cheer
               Displays a random motivational message.
            10. bye
               Terminates the current session.""";

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
