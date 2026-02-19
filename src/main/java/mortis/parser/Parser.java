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
            return handleMark(input, taskList, ui);
        } else if (input.startsWith("unmark")) {
            return handleUnmark(input, taskList, ui);
        } else if (input.startsWith("delete")) {
            return handleDelete(input, taskList, ui);
        } else if (input.startsWith("add")) {
            return handleAdd(input, taskList, ui);
        } else if (input.startsWith("find")) {
            return handleFind(input, taskList, ui);
        } else {
            return ui.unknownCommandMessage();
        }
    }

    /**
     * Helper method to handle "mark" commands.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    private String handleMark(String input, TaskList taskList, Ui ui) {
        String[] userInputArray = input.split(" ");
        try {
            int index = Integer.parseInt(userInputArray[1]) - 1;
            taskList.markTask(index);
            return ui.markSuccessfulMessage(taskList.getTasks().get(index));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidTaskMark();
        }
    }

    /**
     * Helper method to handle "unmark" commands.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    private String handleUnmark(String input, TaskList taskList, Ui ui) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.unmarkTask(index);
            return ui.unmarkSuccessfulMessage(taskList.getTasks().get(index));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidTaskUnmark();
        }
    }

    /**
     * Helper method to handle "delete" commands.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    private String handleDelete(String input, TaskList taskList, Ui ui) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return taskList.deleteTask(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidTaskDelete();
        }
    }

    /**
     * Helper method to handle "add" commands.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    private String handleAdd(String input, TaskList taskList, Ui ui) {
        try {
            Task task = Task.createFromInput(input.substring(4));
            taskList.addTask(task);
            return ui.addSuccessfulMessage(task);
        } catch (MortisException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return ui.invalidTaskDetails();
        }
    }

    /**
     * Helper method to handle "find" commands.
     *
     * @param input User's input into chatbot.
     * @param taskList TaskList to update and pass back.
     * @param ui Ui to display messages to user with.
     */
    private String handleFind(String input, TaskList taskList, Ui ui) {
        try {
            String toFind = input.substring(5);
            return taskList.findTasks(toFind);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.findError();
        }
    }
}