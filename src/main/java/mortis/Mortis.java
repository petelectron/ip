package mortis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import mortis.task.Task;
import mortis.task.TaskList;
import mortis.storage.Storage;
import mortis.parser.Parser;
import mortis.ui.Ui;

/** 
 * Mortis is a simple task management application. 
 */
public class Mortis {
    public static void main(String[] args) throws MortisException, IOException {
        Scanner sc = new Scanner(System.in);
        final String FILEPATH = "data/Mortis.txt";
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage(FILEPATH);
        Parser parser = new Parser();

        try {
            taskList = new TaskList(storage.load());
            System.out.println("Successfully loaded existing data file.");
            taskList.displayTasks();
        } catch (IOException e) {
            System.out.println("No existing data file found.");
            System.out.println("Starting with an empty task list.");
            File file = new File(FILEPATH);
        } catch (MortisException e) {
            System.out.println("Error loading data: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
            File file = new File(FILEPATH);
        }

        ui.lineBreak();
        ui.displayWelcomeMessage();
        ui.lineBreak();

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            parser.parse(userInput, taskList,  ui);
            userInput = sc.nextLine();
        }
        ui.displayGoodbyeMessage();
        storage.save(taskList);
        sc.close();
    }

    public String getResponse(String input) {
        return "hi";
    }
}