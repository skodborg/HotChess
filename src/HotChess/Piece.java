package HotChess;

/**
 * Created by marc on 07/12/13.
 */
public interface Piece {

    /*
    returns either Color.BLACK or Color.WHITE
     */
    public HotChess.Color getColor();

    /*
    returns the type of the chess piece as a string from GameConstants
     */
    public String getType();
}
