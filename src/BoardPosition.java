import java.util.*;

/**
 * Created by marc on 07/12/13.
 */
public enum BoardPosition {

    A8(56), B8(57), C8(58), D8(59), E8(60), F8(61), G8(62), H8(63),
    A7(48), B7(49), C7(50), D7(51), E7(52), F7(53), G7(54), H7(55),
    A6(40), B6(41), C6(42), D6(43), E6(44), F6(45), G6(46), H6(47),
    A5(32), B5(33), C5(34), D5(35), E5(36), F5(37), G5(38), H5(39),
    A4(24), B4(25), C4(26), D4(27), E4(28), F4(29), G4(30), H4(31),
    A3(16), B3(17), C3(18), D3(19), E3(20), F3(21), G3(22), H3(23),
    A2(8), B2(9), C2(10), D2(11), E2(12), F2(13), G2(14), H2(15),
    A1(0), B1(1), C1(2), D1(3), E1(4), F1(5), G1(6), H1(7);

    public static final int MAX_POS_INDEX = 63;

    // index of the position on the board, for easier relative position calculations
    private final int _index;

    public static final BoardPosition[] indexToEnum = {
            A1, B1, C1, D1, E1, F1, G1, H1,
            A2, B2, C2, D2, E2, F2, G2, H2,
            A3, B3, C3, D3, E3, F3, G3, H3,
            A4, B4, C4, D4, E4, F4, G4, H4,
            A5, B5, C5, D5, E5, F5, G5, H5,
            A6, B6, C6, D6, E6, F6, G6, H6,
            A7, B7, C7, D7, E7, F7, G7, H7,
            A8, B8, C8, D8, E8, F8, G8, H8
    };

    private BoardPosition(int index) {
        _index = index;
    }

    /*
    returns the index of the position, for boundary checking
     */
    public int getIndex() {
        return _index;
    }

    /*
    return the relative position north of the given position
    returns null if no position north of the given position exist
     */
    public static BoardPosition north(BoardPosition from) {
        // for the nested calls in the northWest, northEast methods
        if (from == null) {
            return null;
        }

        int index = from._index + 8;

        // if the index when incremented with 8 is out of the board,
        // no position north of the given exists and null is returned
        if (index > MAX_POS_INDEX) {
            return null;
        }

        return indexToEnum[index];
    }

    public static BoardPosition south(BoardPosition from) {
        // for the nested calls in the southWest, southEast methods
        if (from == null) {
            return null;
        }

        int index = from._index - 8;

        if (index < 0) {
            return null;
        }

        return indexToEnum[index];
    }

    public static BoardPosition east(BoardPosition from) {
        // for the nested calls in the northEast, southEast methods
        if (from == null) {
            return null;
        }

        int index = from._index + 1;

        if (index % 8 == 0) {
            return null;
        }

        return indexToEnum[index];
    }

    public static BoardPosition west(BoardPosition from) {
        // for the nested calls in the northWest, southWest methods
        if (from == null) {
            return null;
        }

        int index = from._index - 1;

        if ((index + 1) % 8 == 0) {
            return null;
        }

        return indexToEnum[index];
    }

    public static BoardPosition northEast(BoardPosition from) {
        return north(east(from));
    }

    public static BoardPosition southEast(BoardPosition from) {
        return south(east(from));
    }

    public static BoardPosition southWest(BoardPosition from) {
        return south(west(from));
    }

    public static BoardPosition northWest(BoardPosition from) {
        return north(west(from));
    }

    /*
    returns a list with all the positions in the vertical column which the parameter position is in
     */
    public static Set<BoardPosition> getVerticalPositions(BoardPosition source) {
        Set<BoardPosition> positionSet = new TreeSet<BoardPosition>();

        // adding the vertical positions in the upward direction
        for (int i = source._index; i <= MAX_POS_INDEX; i += 8) {
            positionSet.add(indexToEnum[i]);
        }

        // adding the vertical positions in the downward direction
        for (int i = source._index; i >= 0; i -= 8) {
            positionSet.add(indexToEnum[i]);
        }

        return positionSet;
    }

    /*
    returns a list with all the positions in the horizontal row which the parameter position is in
     */
    public static Set<BoardPosition> getHorizontalPositions(BoardPosition source) {
        Set<BoardPosition> positionSet = new TreeSet<BoardPosition>();

        int i = source._index;

        // add the positions to the right of the source position
        while ((i + 1) % 8 != 0) {
            i++;
            positionSet.add(indexToEnum[i]);
        }

        // add the positions to the left of the source position
        while (i % 8 != 0) {
            i--;
            positionSet.add(indexToEnum[i]);
        }

        return positionSet;
    }

    /*
    returns a list with all the positions in the diagonal direction from bottom left to top right: " / "
     */
    public static Set<BoardPosition> getRightDiagonalPositions(BoardPosition source) {
        Set<BoardPosition> positionSet = new TreeSet<BoardPosition>();

        int i = source._index;

        // find positions from source towards lower left corner
        while (i % 8 != 0 && i > 7) {
            i -= 9;
            positionSet.add(indexToEnum[i]);
        }

        // find positions from source towards upper right corner
        while ((i + 1) % 8 != 0 && i < 56) {
            i += 9;
            positionSet.add(indexToEnum[i]);
        }

        return positionSet;
    }

    /*
    returns a list with all the positions in the diagonal direction from bottom right to top left: " \ "
     */
    public static Set<BoardPosition> getLeftDiagonalPositions(BoardPosition source) {
        Set<BoardPosition> positionSet = new TreeSet<BoardPosition>();

        int i = source._index;

        // find positions from source towards lower right corner
        while ((i + 1) % 8 != 0 && i > 7) {
            i -= 7;
            positionSet.add(indexToEnum[i]);
        }

        // find positions from source towards upper left corner
        while (i % 8 != 0 && i < 56) {
            i += 7;
            positionSet.add(indexToEnum[i]);
        }

        return positionSet;
    }
}