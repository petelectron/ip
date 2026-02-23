package mortis.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * A Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;
    protected DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd HH:mm, yyyy");

    public Deadline(String description, String deadline) throws DateTimeException {
        super(description);
        this.after = "";
        if (deadline.contains("yyyy")) {
            deadline = deadline.replace("yyyy", "uuuu");
        }
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    public Deadline(String description, String after, String deadline) {
        super(description, after);
        if (deadline.contains("yyyy")) {
            deadline = deadline.replace("yyyy", "uuuu");
        }
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toDataString() {
        return "D, " + (isDone ? "1" : "0") + ", " + description + ", "
            + (after.isEmpty() ? "" : after + ", ") + deadline.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(outputFormatter) + ")";
    }
}
