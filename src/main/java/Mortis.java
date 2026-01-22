import java.util.ArrayList;
import java.util.Scanner;

public class Mortis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ls = new ArrayList<>();

        System.out.println("Hello, I'm Mortis.\nWhat can I do for you today?");

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
            } else {
                System.out.println("added: " + userInput);
                ls.add(userInput);
            }
            userInput = sc.nextLine();
        }

        System.out.println("Goodbye, user.");
    }
}
