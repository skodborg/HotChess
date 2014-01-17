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

    @Test
    public void shouldReturnCorrectSetOfRightDiagonalPositions() {

        // testing the i % 8 != 0 part of the if-clause in getRightDiagonalPositions
        Set<BoardPosition> positionSet = BoardPosition.getRightDiagonalPositions(BoardPosition.C4);

        assertTrue("should contain position B3", positionSet.contains(BoardPosition.B3));
        assertTrue("should contain position A2", positionSet.contains(BoardPosition.A2));

        assertTrue("should contain position C4", positionSet.contains(BoardPosition.C4));

        assertTrue("should contain position D5", positionSet.contains(BoardPosition.D5));
        assertTrue("should contain position E6", positionSet.contains(BoardPosition.E6));
        assertTrue("should contain position F7", positionSet.contains(BoardPosition.F7));
        assertTrue("should contain position G8", positionSet.contains(BoardPosition.G8));


        // testing the i > 7 part of the if-clause in getRightDiagonalPositions
        positionSet = BoardPosition.getRightDiagonalPositions(BoardPosition.D3);

        assertTrue("should contain position C2", positionSet.contains(BoardPosition.C2));
        assertTrue("should contain position B1", positionSet.contains(BoardPosition.B1));

        assertTrue("should contain position D3", positionSet.contains(BoardPosition.D3));

        assertTrue("should contain position E4", positionSet.contains(BoardPosition.E4));
        assertTrue("should contain position F5", positionSet.contains(BoardPosition.F5));
        assertTrue("should contain position G6", positionSet.contains(BoardPosition.G6));
        assertTrue("should contain position H7", positionSet.contains(BoardPosition.H7));
    }

    @Test
    public void shouldReturnCorrectSetOfLeftDiagonalPositions() {

        // testing the i % 8 != 0 part of the if-clause in getRightDiagonalPositions
        Set<BoardPosition> positionSet = BoardPosition.getLeftDiagonalPositions(BoardPosition.C4);

        assertTrue("should contain position D3", positionSet.contains(BoardPosition.D3));
        assertTrue("should contain position E2", positionSet.contains(BoardPosition.E2));
        assertTrue("should contain position F1", positionSet.contains(BoardPosition.F1));

        assertTrue("should contain position C4", positionSet.contains(BoardPosition.C4));

        assertTrue("should contain position B5", positionSet.contains(BoardPosition.B5));
        assertTrue("should contain position A6", positionSet.contains(BoardPosition.A6));


        // testing the i > 7 part of the if-clause in getRightDiagonalPositions
        positionSet = BoardPosition.getLeftDiagonalPositions(BoardPosition.E6);

        assertTrue("should contain position F5", positionSet.contains(BoardPosition.F5));
        assertTrue("should contain position G4", positionSet.contains(BoardPosition.G4));
        assertTrue("should contain position H3", positionSet.contains(BoardPosition.H3));

        assertTrue("should contain position E6", positionSet.contains(BoardPosition.E6));

        assertTrue("should contain position D7", positionSet.contains(BoardPosition.D7));
        assertTrue("should contain position C8", positionSet.contains(BoardPosition.C8));
    }

    @Test
    public void shouldReturnCorrectEastPositions() {
        assertEquals("B1 east of A1", BoardPosition.B1, BoardPosition.east(BoardPosition.A1));
        assertEquals("F5 east of E5", BoardPosition.F5, BoardPosition.east(BoardPosition.E5));
        assertNull("null east of right board border position H3", BoardPosition.east(BoardPosition.H3));
        assertNull("null east of right board border position H1", BoardPosition.east(BoardPosition.H1));
    }

    @Test
    public void shouldReturnCorrectWestPositions() {
        assertEquals("A1 west of B1", BoardPosition.A1, BoardPosition.west(BoardPosition.B1));
        assertEquals("E5 west of F5", BoardPosition.E5, BoardPosition.west(BoardPosition.F5));
        assertNull("null west of left board border position A3", BoardPosition.west(BoardPosition.A3));
        assertNull("null west of left board border position A1", BoardPosition.west(BoardPosition.A1));
    }
}
