import java.util.ArrayList;
import java.util.Iterator;

public class BishopMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.addAll(BoardPosition.getRightDiagonalPositions(from));
        return validPositions.iterator();
    }
}
