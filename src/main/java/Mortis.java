import java.util.Scanner;

public class Mortis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, I'm Mortis.\nWhat can I do for you today?");

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }

        System.out.println("Goodbye, user.");
    }
}
