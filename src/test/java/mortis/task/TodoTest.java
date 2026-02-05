package mortis.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toStringConvention() {
        assertEquals("[T][ ] hi", new Todo("hi").toString());
    }

    @Test
    public void toDataStringConvention() {
        assertEquals("T, 0, hi", new Todo("hi").toDataString());
    }

}
