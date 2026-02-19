package mortis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mortis.task.Task;
import mortis.task.TaskList;
import mortis.storage.Storage;
import mortis.parser.Parser;
import mortis.ui.Ui;

/**
 * Mortis is a simple task management application.
 */
public class Mortis {
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private boolean shouldExit = false;

    /**
     * Constructs a Mortis instance, loading tasks from storage.
     * If loading fails, starts with an empty task list.
     */
    public Mortis() {
        final String FILEPATH = "data/Mortis.txt";
        this.storage = new Storage(FILEPATH);
        this.parser = new Parser();
        this.ui = new Ui();

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            loadedTasks = new TaskList(new ArrayList<>());
        } catch (MortisException e) {
            loadedTasks = new TaskList(new ArrayList<>());
        }
        this.taskList = loadedTasks;
    }

    /**
     * Processes a user command and returns the response to be displayed in the GUI.
     *
     * @param input The command entered by the user.
     * @return The response string (including welcome/goodbye messages or command results).
     */
    public String processCommand(String input) {
        StringBuilder response = new StringBuilder();

        if (input.equalsIgnoreCase("bye")) {
            response.append(ui.goodbyeMessage());
            response.append(ui.lineBreak());
            try {
                response.append(storage.save(taskList));
                shouldExit = true;
            } catch (IOException e) {
                return e.getMessage();
            }
        } else {
            try {
                response.append(parser.parse(input, taskList, ui));
                storage.save(taskList); // Auto-save after each command (optional)
            } catch (Exception e) {
                response.append("An error occurred: ").append(e.getMessage());
            }
        }
        return response.toString();
    }

    /**
     * Gets response based on user input.
     *
     * @param input The user input.
     * @return The response string.
     */
    public String getResponse(String input) {
        return processCommand(input);
    }

    /**
     * Returns a welcome message to the user.
     *
     * @return A welcome message.
     */
    public String getWelcomeMessage() {
        return ui.welcomeMessage();
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }
}