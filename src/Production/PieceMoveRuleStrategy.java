package Production;

import java.util.Iterator;
import java.util.Map;

public interface PieceMoveRuleStrategy {
    /*
    returns an iterator over the valid positions of the piece
     */
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap);
}
