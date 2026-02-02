import java.io.File;
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
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        
        try {
            taskList = new TaskList(storage.load());
            System.out.println("Successfully loaded existing data file.");
            taskList.displayTasks();
        } catch (IOException e) {
            System.out.println("No existing data file found.");
            System.out.println("Starting with an empty task list.");
            File file = new File(filePath);
        } catch (MortisException e) {
            System.out.println("Error loading data: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
            File file = new File(filePath);
        }

        ui.lineBreak();
        ui.displayWelcomeMessage();
        ui.lineBreak();

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                taskList.displayTasks();
            } else if (userInput.startsWith("mark")) {
                String[] arr = userInput.split(" ");
                try {
                    int index = Integer.parseInt(arr[1]) - 1;
                    taskList.markTask(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide a valid task number to mark.
                        E.g. "mark 2"
                        """);
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.unmarkTask(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("""
                        Please provide a valid task number to unmark.
                        E.g. "unmark 2"
                        """);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.deleteTask(index);
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
                    taskList.addTask(task);
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
                            >delete <num> (delete task at position <num>),
                            >add todo, <description> (add a todo task)
                            >add deadline, <description>, <ddl> (add a deadline task)
                            >add event, <description>, <start>, <end> (add an event task)
                            >bye (terminate the program)
                            """);
            }
            userInput = sc.nextLine();
        }
        System.out.println("Goodbye, user.");

        storage.save(taskList);
        sc.close();
    }
}