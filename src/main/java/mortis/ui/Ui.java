package mortis.ui;

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

    private final String LINEBREAK = "____________________________";

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
}
