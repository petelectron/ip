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

/** 
 * Handles loading and saving of task data to a file. 
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from local data.
     *
     * @return output task list loaded from data.
     * @throws IOException if data is corrupted.
     * @throws MortisException if Task class cannot create from data.
     */
    public List<Task> load() throws IOException, MortisException {
        List<Task> outputList = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fileReader);
        String nextLine = br.readLine();
        while (nextLine != null) {
            outputList.add(Task.createFromData(nextLine));
            nextLine = br.readLine();
        }
        return outputList;
    }

    /**
     * Saves task list into local data.
     *
     * @throws IOException if filePath is invalid.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.toDataString());
        fw.close();

        System.out.println("Your data has been saved to "
                + filePath + ".");
    }
}
