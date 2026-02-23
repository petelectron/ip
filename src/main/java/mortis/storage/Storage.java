package mortis.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mortis.MortisException;
import mortis.task.Task;
import mortis.task.TaskList;
import mortis.ui.Ui;

/**
 * Handles loading and saving of task data to a file.
 */
public class Storage {
    private final String FILEPATH;
    private Ui ui = new Ui();

    public Storage(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    /**
     * Loads task list from local data.
     *
     * @return output task list loaded from data.
     * @throws MortisException if file does not exist, or existing data cannot be read.
     */
    public List<Task> load() throws MortisException {
        List<Task> loadedTasks = new ArrayList<>();
        java.io.File file = new java.io.File(FILEPATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                throw new MortisException("Existing file does not exist. \nCreated new empty task list.");
            } catch (IOException e) {
                throw new MortisException("Existing file does not exist. \nCould not create new data file at: "
                    + FILEPATH);
            }
        }
        try {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader br = new BufferedReader(fileReader);

            String nextLine = br.readLine();
            while (nextLine != null) {
                loadedTasks.add(Task.createFromData(nextLine));
                nextLine = br.readLine();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | MortisException e) {
            throw new MortisException("Failed to read existing data file: " + e.getMessage()
                + "\nCreated new empty task list.");
        }

        return loadedTasks;
    }

    /**
     * Saves task list into local data.
     *
     * @throws MortisException if failed to save tasks.
     */
    public String save(TaskList taskList) throws MortisException {
        assert FILEPATH.equals("data/Mortis.txt");

        java.io.File file = new java.io.File(FILEPATH);
        file.getParentFile().mkdirs(); // ensure directory exists

        try (FileWriter fw = new FileWriter(FILEPATH)) {
            fw.write(taskList.toDataString());
        } catch (IOException e) {
            throw new MortisException("Failed to save tasks: " + e.getMessage());
        }

        return ui.saveSuccessful(FILEPATH);
    }
}
