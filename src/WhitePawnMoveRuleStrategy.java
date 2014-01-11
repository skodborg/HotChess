import java.util.ArrayList;
import java.util.Iterator;

public class WhitePawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.add(BoardPosition.north(from));
        return validPositions.iterator();
    }
}
