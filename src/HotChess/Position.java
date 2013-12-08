package HotChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 07/12/13.
 */
public enum Position {

    A1(0), A2(1), A3(2), A4(3), A5(4), A6(5), A7(6), A8(7),
    B1(8), B2(9), B3(10), B4(11), B5(12), B6(13), B7(14), B8(15),
    C1(16), C2(17), C3(18), C4(19), C5(20), C6(21), C7(22), C8(23),
    D1(24), D2(25), D3(26), D4(27), D5(28), D6(29), D7(30), D8(31),
    E1(32), E2(33), E3(34), E4(35), E5(36), E6(37), E7(38), E8(39),
    F1(40), F2(41), F3(42), F4(43), F5(44), F6(45), F7(46), F8(47),
    G1(48), G2(49), G3(50), G4(51), G5(52), G6(53), G7(54), G8(55),
    H1(56), H2(57), H3(58), H4(59), H5(60), H6(61), H7(62), H8(63);

    private static final int INDEX_OF_NORTH_FIELD = 1;
    private static final int INDEX_OF_NORTHEAST_FIELD = 9;
    private static final int INDEX_OF_EAST_FIELD = 8;
    private static final int INDEX_OF_SOUTHEAST_FIELD = 7;
    private static final int INDEX_OF_SOUTH_FIELD = -1;
    private static final int INDEX_OF_SOUTHWEST_FIELD = -9;
    private static final int INDEX_OF_WEST_FIELD = -8;
    private static final int INDEX_OF_NORTHWEST_FIELD = -7;

    private final static Position[] indexToEnum = {
            A1, A2, A3, A4, A5, A6, A7, A8,
            B1, B2, B3, B4, B5, B6, B7, B8,
            C1, C2, C3, C4, C5, C6, C7, C8,
            D1, D2, D3, D4, D5, D6, D7, D8,
            E1, E2, E3, E4, E5, E6, E7, E8,
            F1, F2, F3, F4, F5, F6, F7, F8,
            G1, G2, G3, G4, G5, G6, G7, G8,
            H1, H2, H3, H4, H5, H6, H7, H8
    };


    private final int index;

    private Position(int i) {
        index = i;
    }

    /*
    Contains the 8 neighbouring positions of the given position going clockwise,
    starting with the position north of the given position.
    The given list contains null for every neighbour position not on the board, e.g.
     the northern neighbour of the position A8, which does not exist on the chess board
     */
    public static Position[] getNeighbourPositions(Position p) {

        Position[] neighbours = new Position[8];
        Integer[] enumIndexes = new Integer[8];
        enumIndexes[0] = p.index + INDEX_OF_NORTH_FIELD;
        enumIndexes[1] = p.index + INDEX_OF_NORTHEAST_FIELD;
        enumIndexes[2] = p.index + INDEX_OF_EAST_FIELD;
        enumIndexes[3] = p.index + INDEX_OF_SOUTHEAST_FIELD;
        enumIndexes[4] = p.index + INDEX_OF_SOUTH_FIELD;
        enumIndexes[5] = p.index + INDEX_OF_SOUTHWEST_FIELD;
        enumIndexes[6] = p.index + INDEX_OF_WEST_FIELD;
        enumIndexes[7] = p.index + INDEX_OF_NORTHWEST_FIELD;

        for (int i = 0; i < neighbours.length; i++) {
            if (enumIndexes[i] >= 0 && enumIndexes[i] < indexToEnum.length) {
                neighbours[i] = indexToEnum[enumIndexes[i]];
            } else {
                neighbours[i] = null;
            }
        }
        return neighbours;
    }

    /*
    returns a list containing the positions on the horizontal line
    across the board containing the position p
     */

    public static List<Position> getHorizontalPositions(Position p) {
        ArrayList<Position> horizontalPositions = new ArrayList<Position>();
        int i = p.index;
        while (i > 7) {
            i -= 8;
        }
        for (int j = 0; j < 8; j++) {
            horizontalPositions.add(indexToEnum[i]);
            i += 8;
        }
        return horizontalPositions;
    }

    public static List<Position> getVerticalPositions(Position p) {
        ArrayList<Position> verticalPositions = new ArrayList<Position>();
        int i = p.index;
        while (!(i % 8 == 0)) {
            i--;
        }
        for (int j = 0; j < 8; j++) {
            verticalPositions.add(indexToEnum[i]);
            i++;
        }
        return verticalPositions;
    }
}
