package dill.userinterface;

import dill.task.Task;

public class UiMessages {
    private static final String TASKS_LOAD_SUCCESS = "I found %d saved tasks. Let's pick up where we left off!";

    private static final String TASKS_LOAD_SUCCESS_EMPTY = "I could not find any saved tasks. Let's start fresh!";

    private static final String TASKS_LOAD_CORRUPT = "Oh no!"
            + " My memory seems to be corrupted. I'm unable to load our previous tasks, let's start fresh shall we?";

    private static final String TASKS_LOAD_ERROR = "Uh oh. I'm having some troubles with my memory."
            + " Don't worry! I can still help you but I won't be able to save our progress this time.";

    private static final String TASKS_SAVE_ERROR = "Oops! I encountered an issue while saving your tasks."
            + " I'll pause saving for the rest of this session while I work on a fix.";

    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final String GREETING =
            """
            Hello! I'm Dill.
            %s
            What shall we do this time?""";

    private static final String TASK_ADD_SUCCESS =
            """
            Got it. I've added this task:
              %s
            Now you have %d tasks in the list.""";

    private static final String TASK_DELETE_SUCCESS =
            """
            Noted. I've removed this task:
              %s
            Now you have %d tasks in the list.""";

    private static final String TASK_MARK_SUCCESS =
            """
            Nice! I've marked this task as done:
              %s""";

    private static final String TASK_UNMARK_SUCCESS =
            """
            OK, I've marked this task as not done yet:
              %s""";

    private static final String TASK_UPDATE_SUCCESS =
            """
            Got it! I have updated the task:
              From: %s
              To    : %s""";

    private static final String MESSAGE_HELP =
            """
            Here are the available commands:
            
            Managing Tasks:
            1. list
                Displays the current tasks in the list.
            2. todo <task-name>
                Adds a to-do task to the list.
            3. deadline <task-name> /by <yyyy-mm-dd>
                Adds a deadline task to your list.
            4. event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>
                Adds an event task to the list.
            5. mark <task-id>
                Marks the task at specified entry as complete.
            6. unmark <task-id>
                Marks the task at specified entry as incomplete.
            7. delete <task-id>
                Removes the task at specified entry from the list.
            8. update <task-id> <flag> <value> ...
                Modifies one or more fields of a task.
                Supported flags: /name, /by, /start, /end
                e.g., update 3 /name myTask /end 2026-03-14
            
            Quick Tools:
            1. find <keyword>
                Displays all tasks matching the specified keyword.
            2. view <yyyy-mm-dd>
                Displays all tasks occurring on the specified date.
            3. cheer
                Displays a random motivational message.
            4. bye
                Terminates the current session.""";

    public static String getGreeting(String loadMessage) {
        return String.format(GREETING, loadMessage);
    }

    public static String getExit() {
        return MESSAGE_EXIT;
    }

    public static String getLoadTasksSuccess(int n) {
        if (n > 0) {
            return String.format(TASKS_LOAD_SUCCESS, n);
        } else {
            return TASKS_LOAD_SUCCESS_EMPTY;
        }
    }

    public static String getLoadTasksError() {
        return TASKS_LOAD_ERROR;
    }

    public static String getHelp() {
        return MESSAGE_HELP;
    }

    public static String getAddSuccess(Task task, int listSize) {
        return String.format(TASK_ADD_SUCCESS, task, listSize);
    }

    public static String getDeleteSuccess(Task task, int listSize) {
        return String.format(TASK_DELETE_SUCCESS, task, listSize);
    }

    public static String getMarkSuccess(Task task) {
        return String.format(TASK_MARK_SUCCESS, task);
    }

    public static String getUnmarkSuccess(Task task) {
        return String.format(TASK_UNMARK_SUCCESS, task);
    }

    public static String getTasksLoadCorrupt() {
        return TASKS_LOAD_CORRUPT;
    }

    public static String getTasksSaveError() {
        return TASKS_SAVE_ERROR;
    }

    public static String getTaskUpdateSuccess(String taskBefore, Task taskAfter) {
        return String.format(TASK_UPDATE_SUCCESS, taskBefore, taskAfter);
    }
}
