import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Mortis is a simple task management application. 
 * It allows users to add, mark, unmark, delete, and list tasks.
 * The tasks are saved to a file upon exiting the application.
 * The task data is loaded back from the file when the application starts.
 */

public class Mortis {
    public static void main(String[] args) throws MortisException, IOException {
        Scanner sc = new Scanner(System.in);
        String filePath = "ip/Mortis.txt";
        ArrayList<Task> ls = new ArrayList<>();
        
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String nextLine = br.readLine();
            while (nextLine != null) {
                ls.add(Task.createFromData(nextLine));
                nextLine = br.readLine();
            }
            System.out.println("Successfully loaded existing data file.");
            System.out.println("Current task list: ");
            for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + "." + ls.get(i).toString());
                }
            System.out.println("(END OF LIST)" + "\n");
            br.close();
        } catch (IOException e) {
            System.out.println("No existing data file found.");
            System.out.println("Starting with an empty task list.");
            File file = new File(filePath);
        } catch (MortisException e) {
            System.out.println("Error loading data: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
            File file = new File(filePath);
        }

        System.out.println("""
            Hello, I'm Mortis. 
            I'm case sensitive, please be nice to me.
            Enter time in the format "yyyy-MM-dd HHmm".
            E.g. 2023-10-15 1800.
            """);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + "." + ls.get(i).toString());
                }
                System.out.println("(END OF LIST)");
            } else if (userInput.startsWith("mark")) {
                String[] arr = userInput.split(" ");
                try {
                    int index = Integer.parseInt(arr[1]) - 1;
                    ls.get(index).markAsDone();
                    System.out.println("""
                                   I have marked this task as done:
                                   """ + ls.get(index).toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide a valid task number to mark.
                        E.g. "mark 2"
                        """);
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    ls.get(index).unmarkAsDone();
                    System.out.println("""
                                    I have marked this task as not done:
                                    """ + ls.get(index).toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide a valid task number to unmark.
                        E.g. "unmark 2"
                        """);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task removedTask = ls.remove(index);
                    System.out.println("""
                                    I have removed this task:
                                    """ + removedTask.toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide a valid task number to delete.
                        E.g. "delete 2"
                        Use "list" to check your list of tasks.
                        """);
                }
            } else if (userInput.startsWith("add")) {
                try {
                    Task task = Task.createFromInput(userInput.substring(4));
                    ls.add(task);
                    System.out.println("I've added this task:");
                    System.out.println(ls.get(ls.size() - 1).toString());
                } catch (MortisException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide the necessary details for the task.
                        Command format:
                        Todo: "add todo, <description>"
                        Deadline: "add deadline, <description>, <ddl>"
                        Event: "add event, <description>, <start>, <end>"
                        """);
                }
            } else {
                System.out.println("""
                            I don't know that command...
                            My understood commands are: 
                            >list (shows all tasks),
                            >mark <num> (mark task at position <num>),
                            >unmark <num> (unmark task at position <num>),
                            >add todo, <description> (add a todo task)
                            >add deadline, <description>, <ddl> (add a deadline task)
                            >add event, <description>, <start>, <end> (add an event task)
                            >bye (terminate the program)
                            """);
            }
            userInput = sc.nextLine();
        }
        System.out.println("Goodbye, user.");

        FileWriter fw = new FileWriter(filePath);
        String dataToSave = "";
        for (Task t : ls) {
            dataToSave += t.toDataString() + "\n";
        }
        fw.write(dataToSave);
        fw.close();

        System.out.println("Your data has been saved to "
                + filePath + ".");
        
        sc.close();
    }
}