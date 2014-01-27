import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class KingMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game) {
        Set<BoardPosition> validPositions = new TreeSet<BoardPosition>();
        BoardPosition currentPos;
        if ((currentPos = BoardPosition.north(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northEast(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.east(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southEast(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.south(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southWest(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(from)) != null) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northWest(from)) != null) {
            validPositions.add(currentPos);
        }

        return validPositions.iterator();
    }
}
