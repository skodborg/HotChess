import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.*;

public class TestBoardPositionTools {

    @Test
    public void shouldReturnA3NorthOfA2() {
        assertEquals(BoardPosition.A3, BoardPosition.north(BoardPosition.A2));
    }

    @Test
    public void shouldReturnCorrectSetOfVerticalPositions() {
        Set<BoardPosition> positionSet = BoardPosition.getVerticalPositions(BoardPosition.C3);
        assertTrue("should contain position C1", positionSet.contains(BoardPosition.C1));
        assertTrue("should contain position C2", positionSet.contains(BoardPosition.C2));
        assertTrue("should contain position C3", positionSet.contains(BoardPosition.C3));
        assertTrue("should contain position C4", positionSet.contains(BoardPosition.C4));
        assertTrue("should contain position C5", positionSet.contains(BoardPosition.C5));
        assertTrue("should contain position C6", positionSet.contains(BoardPosition.C6));
        assertTrue("should contain position C7", positionSet.contains(BoardPosition.C7));
        assertTrue("should contain position C8", positionSet.contains(BoardPosition.C8));

        assertEquals("set size should be the amount of positions in a column, 8",
                8, positionSet.size());
    }

    @Test
    public void shouldReturnCorrectSetOfHorizontalPositions() {
        Set<BoardPosition> positionSet = BoardPosition.getHorizontalPositions(BoardPosition.C2);

        assertEquals("set size should be the amount of positions in a row, 8",
                8, positionSet.size());
        assertTrue("should contain position A2", positionSet.contains(BoardPosition.A2));
        assertTrue("should contain position B2", positionSet.contains(BoardPosition.B2));
        assertTrue("should contain position C2", positionSet.contains(BoardPosition.C2));
        assertTrue("should contain position D2", positionSet.contains(BoardPosition.D2));
        assertTrue("should contain position E2", positionSet.contains(BoardPosition.E2));
        assertTrue("should contain position F2", positionSet.contains(BoardPosition.F2));
        assertTrue("should contain position G2", positionSet.contains(BoardPosition.G2));
        assertTrue("should contain position H2", positionSet.contains(BoardPosition.H2));

        assertEquals("set size should be the amount of positions in a row, 8",
                8, positionSet.size());
    }
}
