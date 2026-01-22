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
                System.out.println("added: " + userInput);
                ls.add(new Task(userInput));
            }
            userInput = sc.nextLine();
        }

        System.out.println("Goodbye, user.");
    }
}
