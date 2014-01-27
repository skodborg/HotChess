package Production;

import java.util.ArrayList;
import java.util.Iterator;

public class NullMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        ArrayList<BoardPosition> emptyList = new ArrayList<BoardPosition>();
        return emptyList.iterator();
    }
}
