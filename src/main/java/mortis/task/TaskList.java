package mortis.task;

import java.util.List;
import java.lang.StringBuilder;

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

    /**
     * Finds and prints matching tasks based on string input.
     *
     * @param toFind Search keyword to find in tasks.
     */
    public void findTasks(String toFind) {
        StringBuilder stringbuilder = new StringBuilder("");
        for (Task task : tasks) {
            if (task.isMatch(toFind)) {
                stringbuilder.append(task.toString());
                stringbuilder.append("\n");
            }
        }
        if (stringbuilder.isEmpty()) {
            System.out.println("No tasks of such requirement found...");
        } else {
            System.out.println("Task(s) that match your search:");
            System.out.println(stringbuilder.toString());
            System.out.println("(END OF LIST)");

        }

    }

    /**
     * Adds task to the list.
     *
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added task: " + task.toString());
    }

    /**
     * Deletes task of a certain index from the list.
     *
     * @param index Index of Task object to be deleted.
     */
    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        System.out.println("Removed task: " + task.toString());
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of Task object to be marked.
     */
    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("Marked as done: " + task.toString());
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of Task object to be marked.
     */
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.unmarkAsDone();
        System.out.println("Marked as not done: " + task.toString());
    }

    /**
     * Prints the list of tasks.
     */
    public void displayTasks() {
        StringBuilder stringbuilder = new StringBuilder("");
        stringbuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringbuilder.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        stringbuilder.append("(END OF LIST)");
        System.out.println(stringbuilder.toString());
    }

    /**
     * Returns the list of tasks in data string format.
     *
     * @return data string to save.
     */
    public String toDataString() {
        String dataToSave = "";
        for (Task t : tasks) {
            dataToSave += t.toDataString() + "\n";
        }
        return dataToSave;
    }

}
