import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Dill {
    public static final String CHATBOT_NAME = "Dill";
    private static final String LINE = "    ____________________________________________________________";
    private static final List<String> inputs = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("    Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println("    What can I do for you?");
        System.out.println(LINE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(LINE);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.print(LINE);
                break;
            }
            else if (userInput.equals("list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + inputs.get(i));
                }
            }
            else {
                inputs.add(userInput);
                System.out.println(LINE);
                System.out.println("    added: " + userInput);
                System.out.println(LINE);
            }
        }
    }
}
