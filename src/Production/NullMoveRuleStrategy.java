package Production;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class NullMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> emptyList = new ArrayList<BoardPosition>();
        return emptyList.iterator();
    }
}
