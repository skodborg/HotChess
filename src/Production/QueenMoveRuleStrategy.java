package Production;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class QueenMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.addAll(BoardPosition.getRightDiagonalPositions(from));
        validPositions.addAll(BoardPosition.getLeftDiagonalPositions(from));
        validPositions.addAll(BoardPosition.getVerticalPositions(from));
        validPositions.addAll(BoardPosition.getHorizontalPositions(from));
        return validPositions.iterator();
    }
}
