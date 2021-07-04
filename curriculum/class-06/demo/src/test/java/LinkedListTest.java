import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.LinkedList;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    private LinkedList<String> list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void newLinkedList() {
        assertNull(list.getHead());
    }

    @Test
    public void listSize() {
        list.add("A");
        assertEquals(1, list.getSize());
    }
}
