package Production.Strategies.MoveRules;

import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Game;
import Production.Piece;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RookNoPassMoveRuleStrategy implements PieceMoveRuleStrategy {

    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        Set<BoardPosition> resultSet = new TreeSet<BoardPosition>();

        Color movingPieceColor = pieceMap.get(from).getColor();

        // valid positions in the north direction
        BoardPosition current = BoardPosition.north(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.north(current);
            }
        }

        // valid positions in the east direction
        current = BoardPosition.east(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.east(current);
            }
        }

        // valid positions in the south direction
        current = BoardPosition.south(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.south(current);
            }
        }

        // valid positions in the west direction
        current = BoardPosition.west(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.west(current);
            }
        }

        return resultSet.iterator();
    }
}
