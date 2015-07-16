package Production.Strategies.MoveRules;

import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Game;
import Production.Piece;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BishopNoPassMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        Set<BoardPosition> resultSet = new TreeSet<BoardPosition>();
        Color movingPieceColor = pieceMap.get(from).getColor();

        // valid positions in the northEast direction
        BoardPosition current = BoardPosition.northEast(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.northEast(current);
            }
        }

        // valid positions in the northWest direction
        current = BoardPosition.northWest(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.northWest(current);
            }
        }

        // valid positions in the southWest direction
        current = BoardPosition.southWest(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.southWest(current);
            }
        }

        // valid positions in the southEast direction
        current = BoardPosition.southEast(from);
        while (current != null &&
                (pieceMap.get(current) == null ||
                        pieceMap.get(current).getColor() != movingPieceColor)) {
            resultSet.add(current);

            // if we identify a possible attack, stop looking further in this direction
            if (pieceMap.get(current) != null) {
                current = null;
            } else {
                current = BoardPosition.southEast(current);
            }
        }

        return resultSet.iterator();
    }
}
