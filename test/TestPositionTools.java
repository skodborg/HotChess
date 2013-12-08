import HotChess.Position;
import org.junit.Test;

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
}
