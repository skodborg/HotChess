package Production;

import java.util.Iterator;

public interface PieceMoveRuleStrategy {
    /*
    returns an iterator over the valid positions of the piece
     */
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game);
}
