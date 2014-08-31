package Production;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class KnightMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        Piece movingPiece = pieceMap.get(from);
        BoardPosition currPos;
        if ((currPos = BoardPosition.north(BoardPosition.north(BoardPosition.east(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.east(BoardPosition.east(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.east(BoardPosition.east(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.south(BoardPosition.east(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.south(BoardPosition.west(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.west(BoardPosition.west(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.west(BoardPosition.west(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.north(BoardPosition.west(from)))) != null
                && onlyValidAttacks(pieceMap, movingPiece, currPos)) {
            validPositions.add(currPos);
        }

        return validPositions.iterator();
    }

    // returns true if the target position is not occupied, or contains a piece of the opposing players color
    private boolean onlyValidAttacks(Map<BoardPosition, Piece> pieceMap, Piece movingPiece, BoardPosition targetPosition) {
        Piece targetPiece = pieceMap.get(targetPosition);
        return targetPiece == null || targetPiece.getColor() != movingPiece.getColor();
    }
}
