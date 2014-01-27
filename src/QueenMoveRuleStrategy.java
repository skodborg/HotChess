import java.util.ArrayList;
import java.util.Iterator;

public class QueenMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.addAll(BoardPosition.getRightDiagonalPositions(from));
        validPositions.addAll(BoardPosition.getLeftDiagonalPositions(from));
        validPositions.addAll(BoardPosition.getVerticalPositions(from));
        validPositions.addAll(BoardPosition.getHorizontalPositions(from));
        return validPositions.iterator();
    }
}
