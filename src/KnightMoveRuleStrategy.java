import java.util.ArrayList;
import java.util.Iterator;

public class KnightMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from) {
        ArrayList<BoardPosition> validPositions = new ArrayList<BoardPosition>();
        BoardPosition currPos;
        if ((currPos = BoardPosition.north(BoardPosition.north(BoardPosition.east(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.east(BoardPosition.east(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.east(BoardPosition.east(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.south(BoardPosition.east(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.south(BoardPosition.west(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.south(BoardPosition.west(BoardPosition.west(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.west(BoardPosition.west(from)))) != null) {
            validPositions.add(currPos);
        }
        if ((currPos = BoardPosition.north(BoardPosition.north(BoardPosition.west(from)))) != null) {
            validPositions.add(currPos);
        }

        return validPositions.iterator();
    }
}
