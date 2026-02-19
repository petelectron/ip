package mortis.ui;

import mortis.task.Task;

/**
 * Ui class to handle user interactions.
 */
public class Ui {

    private final String WELCOME_MESSAGE = """
        Hello, I'm Mortis. 
        I'm case sensitive, please be nice to me.
        Enter time in the format "yyyy-MM-dd HHmm".
        E.g. 2023-10-15 1800.
        """;
    private final String GOODBYE_MESSAGE = "Goodbye, user.";
    private final String LINEBREAK = "\n";
    private final String UNKNOWN_COMMAND = """
            I don't know that command...
            My understood commands are:
            >list (shows all tasks),
            >mark <num> (mark task at position <num>),
            >unmark <num> (unmark task at position <num>),
            >delete <num> (delete task at position <num>),
            >find <description> (find tasks based on search),
            >add todo, <description> (add a todo task)
            >add deadline, <description>, <deadline> (add a deadline task)
            >add event, <description>, <start>, <end> (add an event task)
            >bye (terminate the program)
            """;
    private final String FIND_ERROR_MESSAGE = "Please provide a valid keyword to find.";
    private final String INVALID_TASK_MARK = """
        Please provide a valid task number to mark.
        E.g. "mark 2"
        """;
    private final String INVALID_TASK_UNMARK = """
        Please provide a valid task number to unmark.
        E.g. "unmark 2"
        """;
    private final String INVALID_TASK_DELETE = """
        Please provide a valid task number to delete.
        E.g. "delete 2"
        Use "list" to check your list of tasks.
        """;
    private final String MISSING_TASK_DETAILS = """
        Please provide the necessary details for the task.
        Command format:
        Todo: "add todo, <description>"
        Deadline: "add deadline, <description>, <deadline>"
        Event: "add event, <description>, <start>, <end>"
        """;

    /**
     * Returns a welcome message for the user.
     */
    public String welcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns a goodbye message for the user.
     */
    public String goodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns a line break.
     */
    public String lineBreak() {
        return LINEBREAK;
    }

    /**
     * Displays a list of commands available.
     */
    public String unknownCommandMessage() {
        return UNKNOWN_COMMAND;
    }

    public String findError() {
        return FIND_ERROR_MESSAGE;
    }

    public String invalidTaskMark() {
        return INVALID_TASK_MARK;
    }

    public String invalidTaskUnmark() {
        return INVALID_TASK_UNMARK;
    }

    public String invalidTaskDelete() {
        return INVALID_TASK_DELETE;
    }

    public String invalidTaskDetails() {
        return MISSING_TASK_DETAILS;
    }

    public String markSuccessfulMessage(Task task) {
        return "Successfully marked the following task as done: " + task.toString();
    }

    public String unmarkSuccessfulMessage(Task task) {
        return "Successfully marked the following task as not done: " + task.toString();
    }

    public String deleteSuccessfulMessage(Task task) {
        return "Successfully deleted the following task: " + task.toString();
    }

    public String addSuccessfulMessage(Task task) {
        return "Successfully added the following task: " + task.toString();
    }

    public String saveSuccessful(String filePath) {
        return "Successfully saved updated task list into " + filePath;
    }
}
