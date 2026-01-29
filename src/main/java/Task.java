public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
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
        String[] arr = str.split(", ");
        String description = arr[1];
        if (arr[0].equals("todo")) {
            Todo todo = new Todo(description);
            return todo;
        } else if (arr[0].equals("deadline")) {
            String ddl = arr[2];
            Deadline deadline = new Deadline(description, ddl);
            return deadline;
        } else if (arr[0].equals("event")) {
            String from = arr[2];
            String to = arr[3];
            Event event = new Event(description, from, to);
            return event;
        } else {
            throw new MortisException("Unknown task type in input");
        }
    }

    /**
     * Returns new task created from line of stored data.
     *
     * @param str A line of stored data.
     * @return new Task created from data string.
     * @throws MortisException if data string is invalid.
     */ 
    public static Task createFromData(String str) throws MortisException {
        String[] arr = str.split(" | ");
        Task task = null;
        if (arr[0].equals("T")) {
            task = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = new Deadline(arr[2], arr[3]);
        } else if (arr[0].equals("E")) {
            task = new Event(arr[2], arr[3], arr[4]);
        } else {
            throw new MortisException("Unknown task type in data.");
        }
        if (arr[1].equals("1")) {
            task.markAsDone();
        }
        return task;
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
        return getStatusIcon() + " " + description;
    }
}
