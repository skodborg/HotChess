import org.junit.Test;

import static junit.framework.Assert.*;

public class TestBoardPositionTools {

    @Test
    public void shouldReturnA3NorthOfA2() {
        assertEquals(BoardPosition.A3, BoardPosition.north(BoardPosition.A2));
    }
}
