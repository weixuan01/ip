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
            else if (userInput.startsWith("todo")) {
                String taskName = userInput.substring(5);
                tasks.add(new ToDo(taskName));
                System.out.println(LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks.getLast());
                System.out.println("    Now you have " + tasks.size() + " tasks in the list");
                System.out.println(LINE);
            }
            else if (userInput.startsWith("deadline")) {
                String taskName = userInput.substring(9, userInput.indexOf("/by") - 1);
                String deadline = userInput.substring(userInput.indexOf("/by") + 4);
                tasks.add(new Deadline(taskName, deadline));
                System.out.println(LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks.getLast());
                System.out.println("    Now you have " + tasks.size() + " tasks in the list");
                System.out.println(LINE);
            }
            else if (userInput.startsWith("event")) {
                String taskName = userInput.substring(6, userInput.indexOf("/start") - 1);
                String startTime = userInput.substring(userInput.indexOf("/start") + 7, userInput.indexOf("/end") - 1);
                String endTime = userInput.substring(userInput.indexOf("/end") + 5);
                tasks.add(new Event(taskName, startTime, endTime));
                System.out.println(LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks.getLast());
                System.out.println("    Now you have " + tasks.size() + " tasks in the list");
                System.out.println(LINE);
            }
            else {
                tasks.add(new Task(userInput));
                System.out.println(LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks.getLast());
                System.out.println("    Now you have " + tasks.size() + " tasks in the list");
                System.out.println(LINE);
            }
        }
    }
}
