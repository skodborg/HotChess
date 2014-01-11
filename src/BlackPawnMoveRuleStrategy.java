import java.util.ArrayList;
import java.util.Iterator;

public class BlackPawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.add(BoardPosition.south(from));
        return validPositions.iterator();
    }
}
