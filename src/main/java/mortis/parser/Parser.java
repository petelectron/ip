package mortis.parser;

import mortis.MortisException;
import mortis.task.Task;
import mortis.task.TaskList;
import mortis.ui.Ui;

/**
 * Parses user input.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Parses user input, accordingly updates task list and displays ui.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    public String parse(String input, TaskList taskList, Ui ui) {
        if (input.equals("list")) {
            return taskList.displayTasks();
        } else if (input.startsWith("mark")) {
            String[] userInputArray = input.split(" ");
            try {
                int index = Integer.parseInt(userInputArray[1]) - 1;
                taskList.markTask(index);
                return "test string";
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ui.invalidTaskMark();
            }
        } else if (input.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.unmarkTask(index);
                return "unmarked task test string";
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ("""
                        Please provide a valid task number to unmark.
                        E.g. "unmark 2"
                        """);
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.deleteTask(index);
                return "test string";
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ("""
                        Please provide a valid task number to delete.
                        E.g. "delete 2"
                        Use "list" to check your list of tasks.
                        """);
            }
        } else if (input.startsWith("add")) {
            try {
                Task task = Task.createFromInput(input.substring(4));
                taskList.addTask(task);
                return "test string";
            } catch (MortisException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                return ("""
                        Please provide the necessary details for the task.
                        Command format:
                        Todo: "add todo, <description>"
                        Deadline: "add deadline, <description>, <deadline>"
                        Event: "add event, <description>, <start>, <end>"
                        """);
            }
        } else if (input.startsWith("find")) {
            try {
                String toFind = input.substring(5);
                System.out.println("Finding tasks:");
                taskList.findTasks(toFind);
                return "test string";
            } catch (StringIndexOutOfBoundsException e) {
                return ui.findError();
            }
        } else {
            return ui.unknownCommandMessage();
        }
    }

}
