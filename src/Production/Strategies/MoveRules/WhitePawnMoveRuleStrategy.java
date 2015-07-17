package Production.Strategies.MoveRules;

import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Game;
import Production.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class WhitePawnMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        Color movingPieceColor = pieceMap.get(from).getColor();

        // check that no piece occupies the next vertical position
        BoardPosition nextPos = BoardPosition.north(from);
        if (pieceMap.get(nextPos) == null) {
            validPositions.add(BoardPosition.north(from));
        }

        // add diagonal positions if an attack is possible
        nextPos = BoardPosition.northEast(from);
        Piece targetPiece = pieceMap.get(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }
        nextPos = BoardPosition.northWest(from);
        targetPiece = pieceMap.get(nextPos);
        if (targetPiece != null &&
                targetPiece.getColor() != movingPieceColor) {
            validPositions.add(nextPos);
        }

        // if in initial position, allow to move two steps forward
        if (from.getIndex() <= 15
                && pieceMap.get(BoardPosition.north(from)) == null
                && pieceMap.get(BoardPosition.north(BoardPosition.north(from))) == null) {
            validPositions.add(BoardPosition.north(BoardPosition.north(from)));
        }

        return validPositions.iterator();
    }
}
