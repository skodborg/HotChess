package Production;

import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public interface Game extends Observable {

    /*
    returns the color of the winning player, or Color.NONE
    if no winner has been found yet
     */
    public Color getWinner();

    /*
    returns the color of the player currently in turn
     */
    public Color getPlayerInTurn();

    /*
    returns the Piece at the given position, or null if no pieces is
    at the given position
     */
    public Piece getPieceAtPosition(BoardPosition p);

    /*
    returns a shallow copy of the mapping of BoardPositions to Pieces currently in use
     */
    public Map<BoardPosition, Piece> getPieceMap();

    /*
    returns true and moves the piece if the move is valid,
    returns false otherwise
     */
    public boolean movePiece(BoardPosition from, BoardPosition to);

    /*
    returns true if the move is valid according to the current game rules,
    returns false otherwise
     */
    public boolean isMoveValid(BoardPosition from, BoardPosition to);

    /*
    returns true if one of the players is in check
     */
    public boolean isCheck();

    /*
    returns true if one of the players is check mate and the game is over, false otherwise
     */
    public boolean isWhiteInMate();
}
