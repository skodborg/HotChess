package Production;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class KingMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        Set<BoardPosition> validPositions = new TreeSet<BoardPosition>();
        Piece movingPiece = pieceMap.get(from);
        BoardPosition currentPos;
        if ((currentPos = BoardPosition.north(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northEast(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.east(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southEast(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.south(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southWest(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northWest(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }

        return validPositions.iterator();
    }

    // returns true if the target position is not occupied, or contains a piece of the opposing players color
    private boolean onlyValidAttacks(Map<BoardPosition, Piece> pieceMap,
                                     Piece movingPiece,
                                     BoardPosition targetPosition) {
        Piece targetPiece = pieceMap.get(targetPosition);
        return targetPiece == null || targetPiece.getColor() != movingPiece.getColor();
    }
}
