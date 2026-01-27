import java.util.Scanner;

public class Dill {
    public static final String CHATBOT_NAME = "Dill";
    private static final String LINE = "    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.print(LINE);
        System.out.println("    Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println("    What can I do for you?");
        System.out.print(LINE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.print(LINE);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.print(LINE);
                break;
            }
            System.out.print(LINE);
            System.out.println("    " + userInput);
            System.out.print(LINE);
        }
    }
}
