import java.util.Scanner;

public class Dill {
    public static final String CHATBOT_NAME = "Dill";

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + userInput);
            System.out.println("    ____________________________________________________________");
        }
    }
}
