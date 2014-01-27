import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BishopNoPassMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        Set<BoardPosition> resultSet = new TreeSet<BoardPosition>();

        // adding north-east positions
        BoardPosition current = BoardPosition.northEast(from);
        while(current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.northEast(current);
        }

        // adding north-West positions
        current = BoardPosition.northWest(from);
        while(current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.northWest(current);
        }

        // adding south-west positions
        current = BoardPosition.southWest(from);
        while(current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.southWest(current);
        }

        // adding south-east positions
        current = BoardPosition.southEast(from);
        while(current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.southEast(current);
        }

        return resultSet.iterator();
    }
}
