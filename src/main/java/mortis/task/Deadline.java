package mortis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd HH:mm, yyyy");

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toDataString() {
        return "D, " + (isDone ? "1" : "0") + ", " + description + ", " + deadline.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(outputFormatter) + ")";
    }
}
