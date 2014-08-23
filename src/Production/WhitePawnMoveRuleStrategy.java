package Production;

import java.util.ArrayList;
import java.util.Iterator;

public class WhitePawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        Color movingPieceColor = game.getPieceAtPosition(from).getColor();

        // check that no piece occupies the next vertical position
        BoardPosition nextPos = BoardPosition.north(from);
        if (game.getPieceAtPosition(nextPos) == null) {
            validPositions.add(BoardPosition.north(from));
        }

        // add diagonal positions if an attack is possible
        nextPos = BoardPosition.northEast(from);
        Piece targetPiece = game.getPieceAtPosition(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }
        nextPos = BoardPosition.northWest(from);
        targetPiece = game.getPieceAtPosition(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }

        // if in initial position, allow to move two steps forward
        if (from.getIndex() <= 15
                && game.getPieceAtPosition(BoardPosition.north(BoardPosition.north(from))) == null) {
            validPositions.add(BoardPosition.north(BoardPosition.north(from)));
        }

        return validPositions.iterator();
    }
}
