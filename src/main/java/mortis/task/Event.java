package mortis.task;
import mortis.MortisException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
     * Event object representing an event task
     * with a description, start date, and end date.
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     * @param from Start date of the event.
     * @param to End date of the event.
     *
     * @throws MortisException if input string is invalid.
     */ 

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd HH:mm, yyyy");


    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toDataString() {
        return "E, " + (isDone ? "1" : "0") + ", " + description 
            + ", " + from.format(formatter) + ", " + to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + 
            " (from: " + from.format(outputFormatter) 
            + " to: " + to.format(outputFormatter) + ")";
    }
}