package mortis.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void toStringConvention() {
        assertEquals("[E][ ] hi (from: Oct 10 18:00, 2020 to: Oct 10 19:00, 2020)",
            new Event("hi", "2020-10-10 1800", "2020-10-10 1900").toString());
    }

    @Test
    public void toDataStringConvention() {
        assertEquals("E, 0, hi, 2020-10-10 1800, 2020-10-10 1900",
            new Event("hi", "2020-10-10 1800", "2020-10-10 1900").toDataString());
    }

}
