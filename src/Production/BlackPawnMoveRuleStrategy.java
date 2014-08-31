package Production;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BlackPawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        Color movingPieceColor = pieceMap.get(from).getColor();

        // check that no piece occupies the next vertical position
        BoardPosition nextPos = BoardPosition.south(from);
        if (pieceMap.get(nextPos) == null) {
            validPositions.add(BoardPosition.south(from));
        }

        // add diagonal positions if an attack is possible
        nextPos = BoardPosition.southEast(from);
        Piece targetPiece = pieceMap.get(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }
        nextPos = BoardPosition.southWest(from);
        targetPiece = pieceMap.get(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }

        // if in initial position, allow to move two steps forward
        if (from.getIndex() >= 48
                && pieceMap.get(BoardPosition.south(BoardPosition.south(from))) == null) {
            validPositions.add(BoardPosition.south(BoardPosition.south(from)));
        }

        return validPositions.iterator();
    }
}
