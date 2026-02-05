package mortis.parser;
import mortis.MortisException;
import mortis.task.Task;
import mortis.task.TaskList;
import mortis.ui.*;

/** 
 * Parses user input.
 */
public class Parser {
    public Parser() {
    }
    
    public void parse(String input, TaskList taskList, Ui ui) {
        if (input.equals("list")) {
            taskList.displayTasks();
            ui.lineBreak();
        } else if (input.startsWith("mark")) {
            String[] arr = input.split(" ");
            try {
                int index = Integer.parseInt(arr[1]) - 1;
                taskList.markTask(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("""
                        Please provide a valid task number to mark.
                        E.g. "mark 2"
                        """);
            }
        } else if (input.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.unmarkTask(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("""
                        Please provide a valid task number to unmark.
                        E.g. "unmark 2"
                        """);
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.deleteTask(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("""
                        Please provide a valid task number to delete.
                        E.g. "delete 2"
                        Use "list" to check your list of tasks.
                        """);
            }
        } else if (input.startsWith("add")) {
            try {
                Task task = Task.createFromInput(input.substring(4));
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
            ui.unknownCommandMessage();
        }
    }

}
