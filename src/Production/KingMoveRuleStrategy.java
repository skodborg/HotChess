package Production;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class KingMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        Set<BoardPosition> validPositions = new TreeSet<BoardPosition>();
        Piece movingPiece = game.getPieceAtPosition(from);
        BoardPosition currentPos;
        if ((currentPos = BoardPosition.north(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northEast(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.east(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southEast(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.south(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southWest(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northWest(from)) != null
                && onlyValidAttacks(game, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }

        return validPositions.iterator();
    }

    // returns true if the target position is not occupied, or contains a piece of the opposing players color
    private boolean onlyValidAttacks(Game game, Piece movingPiece, BoardPosition targetPosition) {
        Piece targetPiece = game.getPieceAtPosition(targetPosition);
        return targetPiece == null || targetPiece.getColor() != movingPiece.getColor();
    }
}
