package mortis.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the Todo class.
 */
public class TodoTest {
    /**
     * Tests correctness of toString() output.
     */
    @Test
    public void toStringConvention() {
        assertEquals("[T][ ] hi", new Todo("hi").toString());
    }

    /**
     * Tests correctness of toDataString() output.
     */
    @Test
    public void toDataStringConvention() {
        assertEquals("T, 0, hi", new Todo("hi").toDataString());
    }

}
