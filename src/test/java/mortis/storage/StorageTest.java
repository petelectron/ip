package mortis.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import mortis.storage.Storage;
import mortis.task.Task;
import mortis.task.Todo;
import mortis.MortisException;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests the Storage class.
 */
public class StorageTest {
    private String filePath = "StorageTestFile.txt";
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Tests the load() method.
     */
    @Test
    public void testLoading() {
        try {
            taskList.add(new Todo("hi"));
            assertEquals(taskList.get(0), new Storage(filePath).load().get(0));
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (MortisException e) {
            System.out.println("Mortis exception");
        }
    }

    /**
     * Tests the save() method.
     */
    public void testSaving() {
        //write this later
    }
}