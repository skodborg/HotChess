package Production;

import java.util.ArrayList;
import java.util.Iterator;

public class BlackPawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        Color movingPieceColor = game.getPieceAtPosition(from).getColor();

        // check that no piece occupies the next vertical position
        BoardPosition nextPos = BoardPosition.south(from);
        if (game.getPieceAtPosition(nextPos) == null) {
            validPositions.add(BoardPosition.south(from));
        }

        // add diagonal positions if an attack is possible
        nextPos = BoardPosition.southEast(from);
        Piece targetPiece = game.getPieceAtPosition(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }
        nextPos = BoardPosition.southWest(from);
        targetPiece = game.getPieceAtPosition(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }

        return validPositions.iterator();
    }
}
