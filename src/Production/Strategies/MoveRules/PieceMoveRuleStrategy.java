package Production.Strategies.MoveRules;

import Production.Utility.BoardPosition;
import Production.Game;
import Production.Piece;

import java.util.Iterator;
import java.util.Map;

public interface PieceMoveRuleStrategy {
    /*
    returns an iterator over the valid positions of the piece
     */
    Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap);
}
