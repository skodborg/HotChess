import org.junit.Test;

import static junit.framework.Assert.*;

public class TestBoardPosition {

    @Test
    public void shouldReturnA3NorthOfA2() {
        assertEquals(BoardPosition.A3, BoardPosition.north(BoardPosition.A2));
    }
}
