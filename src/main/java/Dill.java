import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Dill {
    public static final String CHATBOT_NAME = "Dill";
    private static final String LINE = "    ____________________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();

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
                System.out.println(LINE);
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(LINE);
            }
            else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.get(taskIndex).markDone();
                System.out.println(LINE);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks.get(taskIndex));
                System.out.println(LINE);
            }
            else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                tasks.get(taskIndex).markUndone();
                System.out.println(LINE);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + tasks.get(taskIndex));
                System.out.println(LINE);
            }
            else {
                tasks.add(new Task(userInput));
                System.out.println(LINE);
                System.out.println("    added: " + userInput);
                System.out.println(LINE);
            }
        }
    }
}
