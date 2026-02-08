import java.util.Scanner;

public class UserInterface {
    public final String CHATBOT_NAME = "Dill";
    private final String LINE_DIVIDER = "    ____________________________________________________________";
    private final String DOT_DIVIDER =  "    ............................................................";
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(LINE_DIVIDER);
        System.out.println(message);
        System.out.println(LINE_DIVIDER);
    }

    public void displaySystemMessage(String message) {
        System.out.println(DOT_DIVIDER);
        System.out.println(message);
        System.out.println(DOT_DIVIDER);
    }

    public void displayGreeting() {
        displayMessage("    Hello! I'm " + CHATBOT_NAME + ".\n" +
                       "    What can I do for you?");
    }

    public void displayLoadError(String errorMessage) {
        displaySystemMessage(errorMessage + "\n" +
                             "    [SYSTEM]: Task list for this session will not be saved");
    }

    public void displayLoadSuccess(int n) {
        if (n > 0) {
            displaySystemMessage("    [SYSTEM]: Successfully loaded " + n + " previously saved tasks");
        } else {
            displaySystemMessage("    [SYSTEM]: No previously saved tasks found");
        }
    }

    public String readInput() {
        return scanner.nextLine();
    }


}
