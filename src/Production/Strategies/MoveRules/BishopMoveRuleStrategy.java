package Production.Strategies.MoveRules;

import Production.Utility.BoardPosition;
import Production.Game;
import Production.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BishopMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        validPositions.addAll(BoardPosition.getRightDiagonalPositions(from));
        validPositions.addAll(BoardPosition.getLeftDiagonalPositions(from));
        return validPositions.iterator();
    }
}
