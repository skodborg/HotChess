package Production;

import Production.Utility.BoardPosition;
import Production.Utility.Color;

import java.util.Iterator;
import java.util.Map;

public interface Piece extends Cloneable {

    /*
    returns either Color.BLACK or Color.WHITE
     */
    Color getColor();

    /*
    returns the type of the chess piece as a string from GameConstants
     */
    String getType();

    /*
    returns a string representation of the piece
     */
    String toString();

    /*
    returns an iterator of all the possible positions a piece can be moved to,
    according to the movement rules defined for the piece.
    Game state is not considered here, but is a responsibility of the Game itself
     */
    Iterator<BoardPosition> possibleMovingPositions(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap);
}
