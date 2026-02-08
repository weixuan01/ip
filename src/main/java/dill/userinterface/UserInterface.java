package dill.userinterface;

import java.util.Scanner;

/**
 * User interface of the Dill chatbot application.
 * Captures raw user input and outputs formatted messages to the user.
 */
public class UserInterface {
    public final String CHATBOT_NAME = "Dill";
    private final String LINE_DIVIDER = "    ____________________________________________________________";
    private final String DOT_DIVIDER =  "    ............................................................";
    private Scanner scanner;

    /**
     * Creates a new instance of UserInterface and initializes scanner.
     */
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs a formatted message from Dill to the console
     * Wraps the given message with line dividers to demarcate responses from Dill.
     *
     * @param message The unformatted message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(LINE_DIVIDER);
        System.out.println(message);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Outputs a formatted system-level message to the console.
     * Wraps the given message with dot dividers to demarcate system messages.
     *
     * @param message The unformatted message to be displayed.
     */
    public void displaySystemMessage(String message) {
        System.out.println(DOT_DIVIDER);
        System.out.println(message);
        System.out.println(DOT_DIVIDER);
    }

    /**
     * Outputs a greeting message to the console.
     */
    public void displayGreeting() {
        displayMessage("    Hello! I'm " + CHATBOT_NAME + ".\n" +
                       "    What can I do for you?");
    }

    /**
     * Output a system message specifying a data storage load failure.
     *
     * @param errorMessage Message specifying details of the failure.
     */
    public void displayLoadError(String errorMessage) {
        displaySystemMessage(errorMessage + "\n" +
                             "    [SYSTEM]: Task list for this session will not be saved");
    }

    /**
     * Output a system message specifying a successful data storage load.
     *
     * @param n Number of tasks that have been loaded.
     */
    public void displayLoadSuccess(int n) {
        if (n > 0) {
            displaySystemMessage("    [SYSTEM]: Successfully loaded " + n + " previously saved tasks");
        } else {
            displaySystemMessage("    [SYSTEM]: No previously saved tasks found");
        }
    }

    /**
     * Reads a line of user input
     *
     * @return The raw input string from the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
