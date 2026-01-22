import java.util.ArrayList;
import java.util.Scanner;

public class Mortis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<>();

        System.out.println("Hello, I'm Mortis.\nI'm case sensitive, please be nice to me.");

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + "." + ls.get(i).toString());
                }
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                ls.get(index).markAsDone();
                System.out.println("""
                                   I have marked this task as done:
                                   """ + ls.get(index).toString());
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                ls.get(index).unmarkAsDone();
                System.out.println("""
                                   I have marked this task as not done:
                                   """ + ls.get(index).toString());
            } else {
                if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5);
                    Todo todo = new Todo(description);
                    ls.add(todo);
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.substring(9).split(" /by ");
                    String description = parts[0];
                    String ddl = parts[1];
                    Deadline deadline = new Deadline(description, ddl);
                    ls.add(deadline);
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.substring(6).split(" /from ");
                    String description = parts[0];
                    String[] parts2 = parts[1].split(" /to ");
                    String from = parts2[0];
                    String to = parts2[1];
                    Event event = new Event(description, from, to);
                    ls.add(event);
                }
                System.out.println("I've added this task:");
                System.out.println(ls.get(ls.size() - 1).toString());
            }
            userInput = sc.nextLine();
        }

        System.out.println("Goodbye, user.");
    }
}
