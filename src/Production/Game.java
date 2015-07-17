package Production;

import Production.Utility.BoardPosition;
import Production.Utility.Color;

import java.util.Map;

public interface Game extends Observable {

    /*
    returns the color of the winning player, or Color.NONE
    if no winner has been found yet
     */
    Color getWinner();

    /*
    returns the color of the player currently in turn
     */
    Color getPlayerInTurn();

    /*
    returns the Piece at the given position, or null if no pieces is
    at the given position
     */
    Piece getPieceAtPosition(BoardPosition p);

    /*
    returns the BoardPosition currently housing the given Piece, or null
    if no such Piece is found
     */
    BoardPosition getPositionOfPiece(Piece p);

    /*
    returns a shallow copy of the mapping of BoardPositions to Pieces currently in use
     */
    Map<BoardPosition, Piece> getPieceMap();

    /*
    returns true and moves the piece if the move is valid,
    returns false otherwise
     */
    boolean movePiece(BoardPosition from, BoardPosition to);

    /*
    returns true if the move is valid according to the current game rules,
    returns false otherwise
     */
    boolean isMoveValid(BoardPosition from, BoardPosition to);

    /*
    returns the color of the checked player, or Color.NONE if no player is currently checked
     */
    Color isCheck();

    /*
    returns the color of the player currently in check mate if one exists, otherwise returns Color.NONE
     */
    Color isPlayerInCheckMate();
}
