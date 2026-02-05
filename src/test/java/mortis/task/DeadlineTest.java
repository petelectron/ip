package mortis.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void toStringConvention() {
        assertEquals("[D][ ] hi (by: Oct 10 18:00, 2020)", new Deadline("hi", "2020-10-10 1800").toString());
    }

    @Test
    public void toDataStringConvention() {
        assertEquals("D, 0, hi, 2020-10-10 1800", new Deadline("hi", "2020-10-10 1800").toDataString());
    }

}
