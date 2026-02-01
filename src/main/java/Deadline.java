import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
     * Deadline object representing a deadline task
     * with a description and deadline date.
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     * @param ddl Deadline date of the task.
     *
     * @throws MortisException if input string is invalid.
     */ 

public class Deadline extends Task {

    protected LocalDateTime ddl;
    protected DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter outputFormatter = 
        DateTimeFormatter.ofPattern("MMM dd HH:mm, yyyy");

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = LocalDateTime.parse(ddl, formatter); 
    }

    @Override
    public String toDataString() {
        return "D, " + (isDone ? "1" : "0") + ", " + description + ", " + ddl.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl.format(outputFormatter) + ")";
    }
}
