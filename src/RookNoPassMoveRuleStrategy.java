import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class RookNoPassMoveRuleStrategy implements PieceMoveRuleStrategy {

    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        Set<BoardPosition> resultSet = new TreeSet<BoardPosition>();
        // valid positions in the north direction
        BoardPosition current = BoardPosition.north(from);
        while (game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.north(current);
        }
        current = BoardPosition.east(from);
        while (game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.east(current);
        }
        current = BoardPosition.south(from);
        while (game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.south(current);
        }
        current = BoardPosition.west(from);
        while (game.getPieceAtPosition(current) == null) {
            resultSet.add(current);
            current = BoardPosition.west(current);
        }
        return resultSet.iterator();
    }
}
