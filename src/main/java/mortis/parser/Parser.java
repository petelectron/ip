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
                return ui.markSuccessfulMessage(taskList.getTasks().get(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ui.invalidTaskMark();
            }
        } else if (input.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.unmarkTask(index);
                return ui.unmarkSuccessfulMessage(taskList.getTasks().get(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ui.invalidTaskUnmark();
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task deletedTask = taskList.getTasks().get(index);
                taskList.deleteTask(index);
                return ui.deleteSuccessfulMessage(deletedTask);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return ui.invalidTaskDelete();
            }
        } else if (input.startsWith("add")) {
            try {
                Task task = Task.createFromInput(input.substring(4));
                taskList.addTask(task);
                return ui.addSuccessfulMessage(task);
            } catch (MortisException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                return ui.invalidTaskDetails();
            }
        } else if (input.startsWith("find")) {
            try {
                String toFind = input.substring(5);
                return taskList.findTasks(toFind);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.findError();
            }
        } else {
            return ui.unknownCommandMessage();
        }
    }
}