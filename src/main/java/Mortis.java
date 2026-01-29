import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mortis {
    public static void main(String[] args) throws MortisException, IOException {
        Scanner sc = new Scanner(System.in);
        String filePath = "ip/Mortis.txt";
        ArrayList<Task> ls = new ArrayList<>();
        
        try {
            File data = new File(filePath);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            while (br.readLine() != null) {
                String line = br.readLine();
                ls.add(Task.createFromData(line));
            }
        } catch (IOException e) {
            System.out.println("No existing data file found. A new file will be created upon exiting.");
        }

        System.out.println("Hello, I'm Mortis.\nI'm case sensitive, please be nice to me.");

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
            } else {
                try {
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
                    } else {
                        System.out.println("""
                            I don't know that command...
                            My understood commands are: 
                            list (shows all tasks),
                            mark <num>, unmark <num> (mark or unmark task at position <num>),
                            todo <description> (add a todo task)
                            deadline <description> /by <ddl> (add a deadline task)
                            event <description> /from <start> /to <end> (add an event task)
                            bye (terminate the program)
                            """);
                        throw new MortisException("Unknown command");
                    }
                    System.out.println("I've added this task:");
                    System.out.println(ls.get(ls.size() - 1).toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide the necessary details for the task.
                        Command format:
                        Todo: "todo <description>"
                        Deadline: "deadline <description> /by <ddl>"
                        Event: "event <description> /from <start> /to <end>"
                        """);
                } catch (MortisException e) {
                    // Exception already handled above
                }
            }
            userInput = sc.nextLine();
        }

        System.out.println("Goodbye, user.");
        sc.close();
    }
}