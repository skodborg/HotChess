import java.awt.*;
import java.util.Iterator;

/**
 * Created by marc on 07/12/13.
 */
public interface Piece {

    /*
    returns either Color.BLACK or Color.WHITE
     */
    public Color getColor();

    /*
    returns the type of the chess piece as a string from GameConstants
     */
    public String getType();

    /*
    returns a string representation of the piece
     */
    public String toString();

    /*
    returns an iterator of all the possible positions a piece can be moved to,
    according to the movement rules defined for the piece.
    Game state is not considered here, but is a responsibility of the Game itself
     */
    public Iterator<BoardPosition> possibleMovingPositions(BoardPosition from, Game game);
}
