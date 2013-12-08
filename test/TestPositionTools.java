import HotChess.Position;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

public class TestPositionTools {

    @Test
    public void shouldLetNeighbourArrayContainCorrectPositions() {
        Position pos = Position.B2;
        Position[] neighbours = Position.getNeighbourPositions(pos);
        assertEquals("first neighbour should be B3",
                Position.B3, neighbours[0]);
        assertEquals("second neighbour should be C3",
                Position.C3, neighbours[1]);
        assertEquals("third neighbour should be C2",
                Position.C2, neighbours[2]);
        assertEquals("fourth neighbour should be C1",
                Position.C1, neighbours[3]);
        assertEquals("fifth neighbour should be B1",
                Position.B1, neighbours[4]);
        assertEquals("sixth neighbour should be A1",
                Position.A1, neighbours[5]);
        assertEquals("seventh neighbour should be A2",
                Position.A2, neighbours[6]);
        assertEquals("eighth neighbour should be A3",
                Position.A3, neighbours[7]);
    }

    @Test
    public void shouldInsertNullWhenPositionIsOutOfTheBoard() {
        Position pos = Position.H7;
        Position[] neighbours = Position.getNeighbourPositions(pos);
        assertEquals("first neighbour should be H8",
                Position.H8, neighbours[0]);
        assertEquals("second neighbour should be null",
                null, neighbours[1]);
        assertEquals("third neighbour should be null",
                null, neighbours[2]);
        assertEquals("fourth neighbour should be null",
                null, neighbours[3]);
        assertEquals("fifth neighbour should be H6",
                Position.H6, neighbours[4]);
        assertEquals("sixth neighbour should be G6",
                Position.G6, neighbours[5]);
        assertEquals("seventh neighbour should be G7",
                Position.G7, neighbours[6]);
        assertEquals("eight neighbour should be G8",
                Position.G8, neighbours[7]);
    }

    @Test
    public void shouldReturnHorizontalPositionsCorrectly() {
        Position pos = Position.B4;
        List<Position> horizontalPositions = Position.getHorizontalPositions(pos);
        assertTrue("should contain Position B3",
                horizontalPositions.contains(Position.A4));
        assertTrue("should contain Position B2",
                horizontalPositions.contains(Position.C4));
        assertTrue("should contain Position B1",
                horizontalPositions.contains(Position.D4));
        assertTrue("should contain Position B5",
                horizontalPositions.contains(Position.E4));
        assertTrue("should contain Position B6",
                horizontalPositions.contains(Position.F4));
        assertTrue("should contain Position B7",
                horizontalPositions.contains(Position.G4));
        assertTrue("should contain Position B8",
                horizontalPositions.contains(Position.H4));
    }

    @Test
    public void shouldReturnVerticalPositionsCorrectly() {
        Position pos = Position.A4;
        List<Position> vertPositions = Position.getVerticalPositions(pos);
        assertTrue("should contain Position B3",
                vertPositions.contains(Position.A3));
        assertTrue("should contain Position B2",
                vertPositions.contains(Position.A2));
        assertTrue("should contain Position B1",
                vertPositions.contains(Position.A1));
        assertTrue("should contain Position B5",
                vertPositions.contains(Position.A5));
        assertTrue("should contain Position B6",
                vertPositions.contains(Position.A6));
        assertTrue("should contain Position B7",
                vertPositions.contains(Position.A7));
        assertTrue("should contain Position B8",
                vertPositions.contains(Position.A8));
    }

}