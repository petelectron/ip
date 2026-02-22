package mortis.task;

import mortis.MortisException;

/**
 * A task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String after;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor that adds "do-after" description.
     *
     * @param description description of the task.
     * @param after description of what it comes after.
     */
    public Task(String description, String after) {
        this.description = description;
        this.after = after;
        this.isDone = false;
    }

    /**
     * Returns new task created from user input string.
     *
     * @param str Input string from user.
     * @return new Task created from input string.
     * @throws MortisException if input string is invalid.
     */
    public static Task createFromInput(String str) throws MortisException {
        String[] userInput = str.split(", ");
        String description = userInput[1];
        Task task= null;
        if (userInput[0].equals("todo")) {
            task = (userInput[2] != null && userInput[2].contains("after"))
                ? new Todo(description, userInput[2]) //todo after
                : new Todo(description); //todo
        } else if (userInput[0].equals("deadline")) {
            task = (userInput[2] != null && userInput[2].contains("after"))
                ? new Deadline(description, userInput[2], userInput[3]) //deadline after
                : new Deadline(description, userInput[2]); //deadline
        } else if (userInput[0].equals("event")) {
            task = (userInput[2] != null && userInput[2].contains("after"))
                ? new Event(description, userInput[2], userInput[3], userInput[4]) //event after
                : new Event(description, userInput[2], userInput[3]); //event
        } else {
            throw new MortisException("Unknown task type in input.");
        }
        return task;
    }

    /**
     * Returns new task created from line of stored data.
     *
     * @param str A line of stored data.
     * @return new Task created from data string.
     * @throws MortisException if data string is invalid.
     */
    public static Task createFromData(String str) throws MortisException {
        String[] arr = str.split(", ");
        Task task = null;
        if (arr[0].equals("T")) {
            task = (arr[3] != null)
                ? new Todo(arr[2], arr[3]) //todo after
                : new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = (arr[3].startsWith("after"))
                ? new Deadline(arr[2], arr[3], arr[4]) //deadline after
                : new Deadline(arr[2], arr[3]);
        } else if (arr[0].equals("E")) {
            task = (arr[3].startsWith("after"))
                ? new Event(arr[2], arr[3], arr[4], arr[5])
                : new Event(arr[2], arr[3], arr[4]);
        } else {
            throw new MortisException("Unknown task type in data.");
        }
        if (arr[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public boolean isMatch(String toFind) {
        return description.contains(toFind);
    }


    /**
     * Returns a string representation of the task's status icon.
     *
     * @return String representation of the task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task in data format to be stored.
     *
     * @return String representation of the task in data format.
     */
    public String toDataString() {
        return "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description
            + (after.isEmpty() ? "" : ", " + after);
    }
}
