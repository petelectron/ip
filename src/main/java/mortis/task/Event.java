package mortis.task;

import mortis.MortisException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * An Event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd HH:mm, yyyy");

    public Event(String description, String from, String to) {
        super(description);
        this.after = "";
        if (from.contains("yyyy")) {
            from = from.replace("yyyy", "uuuu");
        }
        if (to.contains("yyyy")) {
            to = to.replace("yyyy", "uuuu");
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public Event(String description, String after, String from, String to) {
        super(description, after);
        if (from.contains("yyyy")) {
            from = from.replace("yyyy", "uuuu");
        }
        if (to.contains("yyyy")) {
            to = to.replace("yyyy", "uuuu");
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toDataString() {
        return "E, " + (isDone ? "1" : "0") + ", " + description
            + ", " + (after.isEmpty() ? "" : after + ", ")
            + from.format(formatter) + ", " + to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (from: " + from.format(outputFormatter)
            + " to: " + to.format(outputFormatter) + ")";
    }
}