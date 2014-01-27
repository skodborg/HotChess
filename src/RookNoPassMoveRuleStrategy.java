import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class RookNoPassMoveRuleStrategy implements PieceMoveRuleStrategy {

    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        Set<BoardPosition> resultSet = new TreeSet<BoardPosition>();

        // valid positions in the north direction
        BoardPosition current = BoardPosition.north(from);
        while (current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.north(current);
        }

        // valid positions in the east direction
        current = BoardPosition.east(from);
        while (current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.east(current);
        }

        // valid positions in the south direction
        current = BoardPosition.south(from);
        while (current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.south(current);
        }

        // valid positions in the west direction
        current = BoardPosition.west(from);
        while (current != null &&
                game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.west(current);
        }

        return resultSet.iterator();
    }
}
