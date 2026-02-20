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
     * @throws IOException if data is corrupted.
     * @throws MortisException if Task class cannot create from data.
     */
    public List<Task> load() throws IOException, MortisException {
        List<Task> loadedTasks = new ArrayList<>();
        FileReader fileReader = new FileReader(FILEPATH);
        BufferedReader br = new BufferedReader(fileReader);
        String nextLine = br.readLine();
        while (nextLine != null) {
            loadedTasks.add(Task.createFromData(nextLine));
            nextLine = br.readLine();
        }
        return loadedTasks;
    }

    /**
     * Saves task list into local data.
     *
     * @throws IOException if filePath is invalid.
     */
    public String save(TaskList taskList) throws IOException {
        assert FILEPATH.equals("data/Mortis.txt");
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write(taskList.toDataString());
        fw.close();

        return ui.saveSuccessful(FILEPATH);
    }
}
