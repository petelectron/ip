package mortis.task;
import java.util.List;

/** 
 * A list of tasks.
 */

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added task: " + task.toString());
    }

    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        System.out.println("Removed task: " + task.toString());
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("Marked as done: " + task.toString());
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.unmarkAsDone();
        System.out.println("Marked as not done: " + task.toString());
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("(END OF LIST)");
    }

    public String toDataString() {
        String dataToSave = "";
        for (Task t : tasks) {
            dataToSave += t.toDataString() + "\n";
        }
        return dataToSave;
    }

}
