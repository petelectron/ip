package mortis.task;

/**
 * A Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.after = "";
    }

    public Todo(String description, String after) {
        super(description, after);
    }


    @Override
    public String toDataString() {
        return "T, " + (isDone ? "1" : "0") + ", " + description
            + (after.isEmpty() ? "" : ", " + after);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}